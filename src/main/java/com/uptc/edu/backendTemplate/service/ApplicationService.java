package com.uptc.edu.backendTemplate.service;

import com.uptc.edu.backendTemplate.dto.ApplicationDTO;
import com.uptc.edu.backendTemplate.model.Application;
import com.uptc.edu.backendTemplate.model.ApplicationStatus;
import com.uptc.edu.backendTemplate.repository.ApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import jakarta.mail.MessagingException;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;
    private final EmailService emailService;

    public ApplicationService(ApplicationRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    @Transactional
    public ApplicationDTO create(ApplicationDTO dto) {
        Application app = ApplicationMapper.fromDTO(dto);
        app.setStatus(ApplicationStatus.NEW);
        app.setCreatedAt(LocalDateTime.now());
        Application saved = repo.save(app);
        ApplicationDTO result = ApplicationMapper.toDTO(saved);
        try {
            emailService.sendNewApplicationEmail(
                    saved.getCenterEmail(),
                    saved.getId(),
                    saved.getAuthorName(),
                    saved.getCertificationType()
            );
        } catch (MessagingException e) {
            // Log error si falla el envío, pero no interrumpe la transacción
            System.err.println("Error enviando correo nueva aplicación: " + e.getMessage());
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<ApplicationDTO> findAll() {
        return repo.findAll().stream()
                .peek(app -> app.getExtraDocumentUrls().size()) // fuerza carga
                .map(ApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ApplicationDTO> findByStatus(ApplicationStatus status) {
        return repo.findByStatus(status).stream()
                .peek(app -> app.getExtraDocumentUrls().size())
                .map(ApplicationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<ApplicationDTO> findById(Long id) {
        return repo.findById(id)
                .map(app -> {
                    app.getExtraDocumentUrls().size();
                    return ApplicationMapper.toDTO(app);
                });
    }

    @Transactional
    public ApplicationDTO updateStatus(Long id, ApplicationStatus newStatus, String adminComments) {
        Application app = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found: " + id));
        app.setStatus(newStatus);
        app.setAdminComments(adminComments);
        app.setUpdatedAt(LocalDateTime.now());
        app.getExtraDocumentUrls().size();

        Application saved = repo.save(app);
        ApplicationDTO dto = ApplicationMapper.toDTO(saved);

        try {
            emailService.sendStatusUpdateEmail(
                    saved.getCenterEmail(),
                    saved.getId(),
                    newStatus.name(),
                    adminComments
            );
        } catch (MessagingException e) {
            // Registrarlo sin interrumpir flujo
            System.err.println("Error enviando correo actualización estado: " + e.getMessage());
        }

        return dto;
    }
}
