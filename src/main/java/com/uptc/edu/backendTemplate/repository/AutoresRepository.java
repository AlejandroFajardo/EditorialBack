package com.uptc.edu.backendTemplate.repository;

import com.uptc.edu.backendTemplate.model.AutoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AutoresRepository extends JpaRepository<AutoresModel, Long>, JpaSpecificationExecutor<AutoresModel> {
}
