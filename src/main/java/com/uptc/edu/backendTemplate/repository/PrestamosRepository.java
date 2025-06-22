package com.uptc.edu.backendTemplate.repository;

import com.uptc.edu.backendTemplate.model.PrestamosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrestamosRepository extends JpaRepository<PrestamosModel, Long>, JpaSpecificationExecutor<PrestamosModel> {
}
