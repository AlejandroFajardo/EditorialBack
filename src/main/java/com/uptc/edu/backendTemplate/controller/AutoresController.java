package com.uptc.edu.backendTemplate.controller;

import com.uptc.edu.backendTemplate.dto.AutoresDTO;
import com.uptc.edu.backendTemplate.dto.UsuariosDTO;
import com.uptc.edu.backendTemplate.service.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/autores")
public class AutoresController {
    @Autowired
    AutoresService autoresService;

    @GetMapping
    public List<AutoresDTO> getAllUsers(){
        return this.autoresService.getAllAutores();
    }
}
