package com.uptc.edu.backendTemplate.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String centerEmail;
    private String authorName;
    private String certificationType;
    private String bookTitle;
    private String isbnCode;
    private Integer publicationYear;
    private String publicationLocation;
    private String publisher;
    private String otherPublisher;
    private String publisherWebsiteUrl;
    private String chapterTitle;
    private String externalCertificateUrl;
    private String fundingType;
    private String internalFundingDocUrl;
    private String projectName;
    private String projectSgiCode;
    private String internalCallName;
    private Integer internalCallYear;
    private String researchGroups;
    private String projectAgreementDocUrl;
    private String manuscriptUrl;
    private String certificationFormUrl;

    @ElementCollection
    private List<String> extraDocumentUrls = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @Lob
    private String adminComments;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Application() {
        this.createdAt = LocalDateTime.now();
        this.status = ApplicationStatus.NEW;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Getters y setters (auto-generados por tu IDE)

    public Long getId() {
        return id;
    }

    public String getCenterEmail() {
        return centerEmail;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getCertificationType() {
        return certificationType;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public String getPublicationLocation() {
        return publicationLocation;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getOtherPublisher() {
        return otherPublisher;
    }

    public String getPublisherWebsiteUrl() {
        return publisherWebsiteUrl;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public String getExternalCertificateUrl() {
        return externalCertificateUrl;
    }

    public String getFundingType() {
        return fundingType;
    }

    public String getInternalFundingDocUrl() {
        return internalFundingDocUrl;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectSgiCode() {
        return projectSgiCode;
    }

    public String getInternalCallName() {
        return internalCallName;
    }

    public Integer getInternalCallYear() {
        return internalCallYear;
    }

    public String getResearchGroups() {
        return researchGroups;
    }

    public String getProjectAgreementDocUrl() {
        return projectAgreementDocUrl;
    }

    public String getManuscriptUrl() {
        return manuscriptUrl;
    }

    public String getCertificationFormUrl() {
        return certificationFormUrl;
    }

    public List<String> getExtraDocumentUrls() {
        return extraDocumentUrls;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public String getAdminComments() {
        return adminComments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCenterEmail(String centerEmail) {
        this.centerEmail = centerEmail;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setCertificationType(String certificationType) {
        this.certificationType = certificationType;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setPublicationLocation(String publicationLocation) {
        this.publicationLocation = publicationLocation;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setOtherPublisher(String otherPublisher) {
        this.otherPublisher = otherPublisher;
    }

    public void setPublisherWebsiteUrl(String publisherWebsiteUrl) {
        this.publisherWebsiteUrl = publisherWebsiteUrl;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public void setExternalCertificateUrl(String externalCertificateUrl) {
        this.externalCertificateUrl = externalCertificateUrl;
    }

    public void setFundingType(String fundingType) {
        this.fundingType = fundingType;
    }

    public void setInternalFundingDocUrl(String internalFundingDocUrl) {
        this.internalFundingDocUrl = internalFundingDocUrl;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setProjectSgiCode(String projectSgiCode) {
        this.projectSgiCode = projectSgiCode;
    }

    public void setInternalCallName(String internalCallName) {
        this.internalCallName = internalCallName;
    }

    public void setInternalCallYear(Integer internalCallYear) {
        this.internalCallYear = internalCallYear;
    }

    public void setResearchGroups(String researchGroups) {
        this.researchGroups = researchGroups;
    }

    public void setProjectAgreementDocUrl(String projectAgreementDocUrl) {
        this.projectAgreementDocUrl = projectAgreementDocUrl;
    }

    public void setManuscriptUrl(String manuscriptUrl) {
        this.manuscriptUrl = manuscriptUrl;
    }

    public void setCertificationFormUrl(String certificationFormUrl) {
        this.certificationFormUrl = certificationFormUrl;
    }

    public void setExtraDocumentUrls(List<String> extraDocumentUrls) {
        this.extraDocumentUrls = extraDocumentUrls;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setAdminComments(String adminComments) {
        this.adminComments = adminComments;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
