package com.uptc.edu.backendTemplate.repository;

import com.uptc.edu.backendTemplate.model.Application;
import com.uptc.edu.backendTemplate.model.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(ApplicationStatus status);
}
