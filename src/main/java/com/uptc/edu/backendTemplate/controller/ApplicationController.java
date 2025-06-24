package com.uptc.edu.backendTemplate.controller;

import com.uptc.edu.backendTemplate.dto.ApplicationDTO;
import com.uptc.edu.backendTemplate.dto.StatusUpdateRequest;
import com.uptc.edu.backendTemplate.model.ApplicationStatus;
import com.uptc.edu.backendTemplate.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApplicationDTO> create(@RequestBody ApplicationDTO dto) {
        ApplicationDTO saved = service.create(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDTO>> list(
            @RequestParam(value = "status", required = false) ApplicationStatus status) {
        List<ApplicationDTO> results = (status != null)
                ? service.findByStatus(status)
                : service.findAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationDTO> getOne(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam("status") ApplicationStatus newStatus,
            @RequestBody(required = false) String adminComments) {
        ApplicationDTO updated = service.updateStatus(id, newStatus, adminComments);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApplicationDTO> patchStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateRequest req) {
        ApplicationDTO updated = service.updateStatus(id, ApplicationStatus.valueOf(req.getStatus()), req.getAdminComments());
        return ResponseEntity.ok(updated);
    }

}
