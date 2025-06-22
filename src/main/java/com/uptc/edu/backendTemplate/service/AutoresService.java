package com.uptc.edu.backendTemplate.service;

import com.uptc.edu.backendTemplate.dto.AutoresDTO;
import com.uptc.edu.backendTemplate.dto.UsuariosDTO;
import com.uptc.edu.backendTemplate.repository.AutoresRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoresService {
    @Autowired
    AutoresRepository autoresRepository;

    @Transactional
    public List<AutoresDTO> getAllAutores() {
        return AutoresDTO.fromModelList(this.autoresRepository.findAll());
    }
}
