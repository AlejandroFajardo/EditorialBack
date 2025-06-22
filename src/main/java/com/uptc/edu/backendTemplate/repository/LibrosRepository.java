package com.uptc.edu.backendTemplate.repository;

import com.uptc.edu.backendTemplate.model.LibrosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LibrosRepository extends JpaRepository<LibrosModel, Long>, JpaSpecificationExecutor<LibrosModel> {
}
