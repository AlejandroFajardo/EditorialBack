package com.uptc.edu.backendTemplate.repository;

import com.uptc.edu.backendTemplate.model.UsuariosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsuariosRepository extends JpaRepository<UsuariosModel, Long>, JpaSpecificationExecutor<UsuariosModel> {
}
