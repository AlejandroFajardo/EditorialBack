package com.uptc.edu.backendTemplate.service;

import com.uptc.edu.backendTemplate.dto.UsuariosDTO;
import com.uptc.edu.backendTemplate.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class UsuariosService {
    @Autowired
    UsuariosRepository usuariosRepository;

    @Transactional
    public List<UsuariosDTO> getAllUsers() {
        return UsuariosDTO.fromModelList(this.usuariosRepository.findAll());
    }


}
