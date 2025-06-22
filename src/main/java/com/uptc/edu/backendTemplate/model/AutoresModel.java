package com.uptc.edu.backendTemplate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name="AUTORES")
@RequiredArgsConstructor
@AllArgsConstructor

public class AutoresModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTORES_SEQ")
    @SequenceGenerator(name = "AUTORES_SEQ", sequenceName = "AUTORES_SEQ", allocationSize = 1)
    @Column(name = "ID_AUTOR")
    private Long idAutor;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;

    @Column(name = "FECHA_MUERTE")
    private Date fechaMuerte;

}
