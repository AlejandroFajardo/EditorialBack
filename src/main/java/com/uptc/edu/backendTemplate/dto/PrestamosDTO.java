package com.uptc.edu.backendTemplate.dto;

import com.uptc.edu.backendTemplate.model.LibrosModel;
import com.uptc.edu.backendTemplate.model.PrestamosModel;
import com.uptc.edu.backendTemplate.model.UsuariosModel;
import com.uptc.edu.backendTemplate.repository.LibrosRepository;
import com.uptc.edu.backendTemplate.repository.UsuariosRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor

public class PrestamosDTO {
    private Long idPrestamo;
    private LibrosModel libro;
    private UsuariosModel dniUsuario;
    private Date fechaPrestamo;
    private Date fechaDevolucion;


    public static PrestamosModel toModel(PrestamosDTO dto) {
        PrestamosModel prestamo = new PrestamosModel();
        prestamo.setIdPrestamo(dto.getIdPrestamo());
        prestamo.setLibro(dto.getLibro());
        prestamo.setDniUsuario(dto.getDniUsuario());
        prestamo.setFechaPrestamo(dto.getFechaPrestamo());
        prestamo.setFechaDevolucion(dto.getFechaDevolucion());
        return prestamo;
    }

    public static PrestamosDTO fromModel(PrestamosModel prestamo) {
        PrestamosDTO dto = new PrestamosDTO();
        dto.setIdPrestamo(prestamo.getIdPrestamo());
        dto.setLibro(prestamo.getLibro());
        dto.setDniUsuario(prestamo.getDniUsuario());
        dto.setFechaPrestamo(prestamo.getFechaPrestamo());
        dto.setFechaDevolucion(prestamo.getFechaDevolucion());
        return dto;
    }

    public static List<PrestamosModel> toModelList(List<PrestamosDTO> dtos) {
        return dtos.stream().map(PrestamosDTO::toModel).collect(Collectors.toList());
    }

    public static List<PrestamosDTO> fromModelList(List<PrestamosModel> prestamos) {
        return prestamos.stream().map(PrestamosDTO::fromModel).collect(Collectors.toList());
    }

}
