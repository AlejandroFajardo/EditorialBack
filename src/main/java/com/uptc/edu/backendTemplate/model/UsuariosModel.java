package com.uptc.edu.backendTemplate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@Table(name="USUARIOS")
@RequiredArgsConstructor
@AllArgsConstructor

public class UsuariosModel {

    @Id
    @Column(name = "DNI")
    private Long dni;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "EDAD")
    private Integer edad;

}
