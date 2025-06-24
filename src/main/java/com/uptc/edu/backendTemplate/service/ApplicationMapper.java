package com.uptc.edu.backendTemplate.service;

import com.uptc.edu.backendTemplate.dto.ApplicationDTO;
import com.uptc.edu.backendTemplate.model.Application;

public class ApplicationMapper {

    public static ApplicationDTO toDTO(Application app) {
        ApplicationDTO dto = new ApplicationDTO();
        dto.id = app.getId();
        dto.centerEmail = app.getCenterEmail();
        dto.authorName = app.getAuthorName();
        dto.certificationType = app.getCertificationType();
        dto.bookTitle = app.getBookTitle();
        dto.isbnCode = app.getIsbnCode();
        dto.publicationYear = app.getPublicationYear();
        dto.publicationLocation = app.getPublicationLocation();
        dto.publisher = app.getPublisher();
        dto.otherPublisher = app.getOtherPublisher();
        dto.publisherWebsiteUrl = app.getPublisherWebsiteUrl();
        dto.chapterTitle = app.getChapterTitle();
        dto.externalCertificateUrl = app.getExternalCertificateUrl();
        dto.fundingType = app.getFundingType();
        dto.internalFundingDocUrl = app.getInternalFundingDocUrl();
        dto.projectName = app.getProjectName();
        dto.projectSgiCode = app.getProjectSgiCode();
        dto.internalCallName = app.getInternalCallName();
        dto.internalCallYear = app.getInternalCallYear();
        dto.researchGroups = app.getResearchGroups();
        dto.projectAgreementDocUrl = app.getProjectAgreementDocUrl();
        dto.manuscriptUrl = app.getManuscriptUrl();
        dto.certificationFormUrl = app.getCertificationFormUrl();
        dto.extraDocumentUrls = app.getExtraDocumentUrls();
        dto.status = app.getStatus().name();
        dto.adminComments = app.getAdminComments();
        dto.createdAt = app.getCreatedAt();
        dto.updatedAt = app.getUpdatedAt();
        return dto;
    }

    public static Application fromDTO(ApplicationDTO dto) {
        Application app = new Application();
        app.setCenterEmail(dto.centerEmail);
        app.setAuthorName(dto.authorName);
        app.setCertificationType(dto.certificationType);
        app.setBookTitle(dto.bookTitle);
        app.setIsbnCode(dto.isbnCode);
        app.setPublicationYear(dto.publicationYear);
        app.setPublicationLocation(dto.publicationLocation);
        app.setPublisher(dto.publisher);
        app.setOtherPublisher(dto.otherPublisher);
        app.setPublisherWebsiteUrl(dto.publisherWebsiteUrl);
        app.setChapterTitle(dto.chapterTitle);
        app.setExternalCertificateUrl(dto.externalCertificateUrl);
        app.setFundingType(dto.fundingType);
        app.setInternalFundingDocUrl(dto.internalFundingDocUrl);
        app.setProjectName(dto.projectName);
        app.setProjectSgiCode(dto.projectSgiCode);
        app.setInternalCallName(dto.internalCallName);
        app.setInternalCallYear(dto.internalCallYear);
        app.setResearchGroups(dto.researchGroups);
        app.setProjectAgreementDocUrl(dto.projectAgreementDocUrl);
        app.setManuscriptUrl(dto.manuscriptUrl);
        app.setCertificationFormUrl(dto.certificationFormUrl);
        app.setExtraDocumentUrls(dto.extraDocumentUrls);
        return app;
    }
}
