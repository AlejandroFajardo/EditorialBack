package com.uptc.edu.backendTemplate.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ApplicationDTO {
    public Long id;
    public String centerEmail;
    public String authorName;
    public String certificationType;
    public String bookTitle;
    public String isbnCode;
    public Integer publicationYear;
    public String publicationLocation;
    public String publisher;
    public String otherPublisher;
    public String publisherWebsiteUrl;
    public String chapterTitle;
    public String externalCertificateUrl;
    public String fundingType;
    public String internalFundingDocUrl;
    public String projectName;
    public String projectSgiCode;
    public String internalCallName;
    public Integer internalCallYear;
    public String researchGroups;
    public String projectAgreementDocUrl;
    public String manuscriptUrl;
    public String certificationFormUrl;
    public List<String> extraDocumentUrls;
    public String status;
    public String adminComments;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
