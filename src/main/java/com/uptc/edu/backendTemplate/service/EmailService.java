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
    private String editorialEmail; // Configura en application.properties, ej: editorial@uptc.edu.co

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Envía un correo al autor cuando su solicitud ha sido radicada exitosamente.
     * @param to Correo del destinatario (autor)
     * @param authorName Nombre del autor
     * @param bookTitle Título de la obra
     * @param center Centro o facultad
     * @param isbn ISBN de la obra
     * @param certType Tipo de certificación solicitada
     */
    public void sendNewApplicationEmail(String to, String authorName, String bookTitle, String center, String isbn, String certType)
            throws MessagingException {
        String subject = "📚 Confirmación de radicación de solicitud editorial";
        String content = """
            <html>
              <body style="font-family: Arial, sans-serif; color: #222;">
                <div style="border:1px solid #e0e0e0; border-radius:10px; padding:24px; max-width:600px; margin:auto; background: #f9f9fb;">
                  <h2 style="color:#154360; margin-top:0;">Radicación Exitosa</h2>
                  <p>Estimado(a) %s,</p>
                  <p>
                    Nos permitimos informarle que la solicitud editorial ha sido <strong>radicada exitosamente</strong> en el sistema de la Editorial UPTC.<br>
                    A continuación, se presentan los datos principales de la postulación:
                  </p>
                  <table style="width:100%%; border-collapse:collapse; margin:20px 0;">
                    <tr>
                      <td style="padding:8px; font-weight:bold;">Título:</td>
                      <td style="padding:8px;">%s</td>
                    </tr>
                    <tr>
                      <td style="padding:8px; font-weight:bold;">Autor:</td>
                      <td style="padding:8px;">%s</td>
                    </tr>
                    <tr>
                      <td style="padding:8px; font-weight:bold;">Centro:</td>
                      <td style="padding:8px;">%s</td>
                    </tr>
                    <tr>
                      <td style="padding:8px; font-weight:bold;">ISBN:</td>
                      <td style="padding:8px;">%s</td>
                    </tr>
                    <tr>
                      <td style="padding:8px; font-weight:bold;">Tipo de certificación:</td>
                      <td style="padding:8px;">%s</td>
                    </tr>
                  </table>
                  <p>
                    El equipo de la Editorial UPTC revisará la documentación presentada y se comunicará con usted para informarle el resultado del proceso.
                  </p>
                  <p style="margin-top:32px;">Cordialmente,<br>Editorial UPTC</p>
                </div>
              </body>
            </html>
            """.formatted(authorName, bookTitle, authorName, center, isbn, certType);

        sendHtmlEmail(to, subject, content);
        // Notifica a la editorial
        sendHtmlEmail(editorialEmail, "📌 Nueva solicitud editorial radicada", content);
    }

    /**
     * Envía un correo SOLO cuando la solicitud es aceptada o rechazada.
     * @param to Correo del destinatario (autor)
     * @param newStatus Nuevo estado ("Aceptado" o "Rechazado")
     * @param adminComments Comentarios del equipo editorial (puede ser vacío)
     * @param authorName Nombre del autor
     * @param bookTitle Título de la obra
     */
    public void sendStatusUpdateEmail(String to, String newStatus, String adminComments, String authorName, String bookTitle)
            throws MessagingException {
        // Solo enviar si es aceptado o rechazado
        if (!"APPROVED".equalsIgnoreCase(newStatus) && !"REJECTED".equalsIgnoreCase(newStatus)) {
            return;
        }

        String subject = "🔔 Notificación de resultado de postulación editorial";
        String statusMessage = "APPROVED".equalsIgnoreCase(newStatus)
                ? "<p style='color:#229954;font-weight:bold;'>Nos complace informarle que su postulación ha sido <strong>APROBADA</strong>.</p>"
                : "<p style='color:#C0392B;font-weight:bold;'>Lamentamos informarle que su postulación ha sido <strong>RECHAZADA</strong>.</p>";

        String commentSection = (adminComments != null && !adminComments.isEmpty())
                ? "<p><strong>Observaciones del equipo editorial:</strong><br>" + adminComments + "</p>"
                : "";

        String content = """
            <html>
              <body style="font-family: Arial, sans-serif; color: #222;">
                <div style="border:1px solid #e0e0e0; border-radius:10px; padding:24px; max-width:600px; margin:auto; background: #f9f9fb;">
                  <h2 style="color:#154360; margin-top:0;">Resultado del proceso editorial</h2>
                  <p>Estimado(a) %s,</p>
                  <p>
                    En relación con su postulación titulada <strong>%s</strong>, le comunicamos lo siguiente:
                  </p>
                  %s
                  %s
                  <p style="margin-top:32px;">Cordialmente,<br>Editorial UPTC</p>
                </div>
              </body>
            </html>
            """.formatted(authorName, bookTitle, statusMessage, commentSection);

        sendHtmlEmail(to, subject, content);
        // Notifica a la editorial también
        sendHtmlEmail(editorialEmail, "✅ Cambio de estado: postulación editorial " + newStatus, content);
    }

    /**
     * Método interno para enviar correos HTML.
     */
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
