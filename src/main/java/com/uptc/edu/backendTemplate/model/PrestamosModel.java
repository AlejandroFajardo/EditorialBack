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
@Table(name="PRESTAMOS")
@RequiredArgsConstructor
@AllArgsConstructor

public class PrestamosModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRESTAMO")
    private Long idPrestamo;

    @ManyToOne
    @JoinColumn(name = "ID_LIBRO", nullable = false)
    private LibrosModel libro;

    @ManyToOne
    @JoinColumn(name = "DNI")
    private UsuariosModel dniUsuario;

    @Column(name = "FECHA_PRESTAMO")
    private Date fechaPrestamo;

    @Column(name = "FECHA_DEVOLUCION")
    private Date fechaDevolucion;

}