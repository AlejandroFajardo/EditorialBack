package com.uptc.edu.backendTemplate.service;

import com.uptc.edu.backendTemplate.dto.PrestamosDTO;
import com.uptc.edu.backendTemplate.model.LibrosModel;
import com.uptc.edu.backendTemplate.model.PrestamosModel;
import com.uptc.edu.backendTemplate.model.UsuariosModel;
import com.uptc.edu.backendTemplate.repository.LibrosRepository;
import com.uptc.edu.backendTemplate.repository.PrestamosRepository;
import com.uptc.edu.backendTemplate.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamosService {

    @Autowired
    private PrestamosRepository prestamosRepository;

    @Autowired
    private LibrosRepository librosRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;


    public List<PrestamosDTO> getAllPrestamos() {
        return PrestamosDTO.fromModelList(prestamosRepository.findAll());
    }

    public PrestamosDTO getPrestamoById(Long id) {
        return prestamosRepository.findById(id)
                .map(PrestamosDTO::fromModel)
                .orElse(null);
    }

    public PrestamosDTO savePrestamo(PrestamosDTO dto) {
        PrestamosModel prestamo = PrestamosDTO.toModel(dto);
        return PrestamosDTO.fromModel(prestamosRepository.save(prestamo));
    }

    public void deletePrestamo(Long id) {
        prestamosRepository.deleteById(id);
    }
}
