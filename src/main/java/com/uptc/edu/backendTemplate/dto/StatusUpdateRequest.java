package com.uptc.edu.backendTemplate.dto;

public class StatusUpdateRequest {
    private String status;
    private String adminComments;

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getAdminComments() { return adminComments; }
    public void setAdminComments(String adminComments) { this.adminComments = adminComments; }
}
