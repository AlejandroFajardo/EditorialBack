package com.uptc.edu.backendTemplate.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="LIBROS")
@RequiredArgsConstructor
@AllArgsConstructor

public class LibrosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIBROS_SEQ")
    @SequenceGenerator(name = "LIBROS_SEQ", sequenceName = "LIBROS_SEQ", allocationSize = 1)
    @Column(name = "ID_LIBRO")
    private Long idLibro;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "ANIO")
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "ID_AUTOR", nullable = false)
    private AutoresModel autor;

}
