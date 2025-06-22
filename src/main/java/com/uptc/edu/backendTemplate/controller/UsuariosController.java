package com.uptc.edu.backendTemplate.controller;

import com.uptc.edu.backendTemplate.dto.UsuariosDTO;
import com.uptc.edu.backendTemplate.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List; //Use this if you need to separate your logic layer from persistence layer if not, use List from hibernate

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")

public class UsuariosController {
    @Autowired
    UsuariosService usuariosService;

    @GetMapping
    public List<UsuariosDTO> getAllUsers(){
        return this.usuariosService.getAllUsers();
    }


}
