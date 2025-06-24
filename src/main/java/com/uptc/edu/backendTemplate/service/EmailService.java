package com.uptc.edu.backendTemplate.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromAddress;

    @Value("${editorial.notification.email}")
    private String editorialEmail; // agregar en properties: didier.fajardo@uptc.edu.co

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendNewApplicationEmail(String to, Long applicationId, String authorName, String certType)
            throws MessagingException {
        String subject = "📚 New Application Received – ID " + applicationId;
        String content = """
            <html>
              <body>
                <h2 style="color:#2E86C1;">New Certification Request</h2>
                <p><strong>Author:</strong> %s</p>
                <p><strong>Certification type:</strong> %s</p>
                <p>Your application ID is <strong>#%d</strong>. We will review it shortly.</p>
                <br/>
                <p>—
                Editorial UPTC</p>
              </body>
            </html>
            """.formatted(authorName, certType, applicationId);

        sendHtmlEmail(to, subject, content);
        sendHtmlEmail(editorialEmail, "📌 Admin notified of new application #" + applicationId, content);
    }

    public void sendStatusUpdateEmail(String to, Long applicationId, String newStatus, String adminComments)
            throws MessagingException {
        String subject = "🔔 Status Update – Application #" + applicationId;
        String commentSection = (adminComments != null && !adminComments.isEmpty())
                ? "<p><strong>Comments:</strong> " + adminComments + "</p>"
                : "";
        String content = """
            <html>
              <body>
                <h2 style="color:#1D8348;">Status Updated: %s</h2>
                <p>Your application ID <strong>#%d</strong> has changed status to <strong>%s</strong>.</p>
                %s
                <br/>
                <p>Thanks,<br/>Editorial UPTC Team</p>
              </body>
            </html>
            """.formatted(newStatus, applicationId, newStatus, commentSection);

        sendHtmlEmail(to, subject, content);
        sendHtmlEmail(editorialEmail, "✅ Admin updated application #" + applicationId + " to " + newStatus, content);
    }

    private void sendHtmlEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage mime = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mime, "utf-8");
        helper.setTo(to);
        helper.setFrom(fromAddress);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailSender.send(mime);
    }
}
