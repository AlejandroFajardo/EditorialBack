package com.uptc.edu.backendTemplate.controller;

import com.uptc.edu.backendTemplate.dto.PrestamosDTO;
import com.uptc.edu.backendTemplate.model.PrestamosModel;
import com.uptc.edu.backendTemplate.service.PrestamosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/prestamos")

public class PrestamosController {
    @Autowired
    private PrestamosService prestamosService;

    @GetMapping
    public List<PrestamosDTO> getAllPrestamos() {
        return prestamosService.getAllPrestamos();
    }

    @GetMapping("/{id}")
    public PrestamosDTO getPrestamoById(@PathVariable Long id) {
        return prestamosService.getPrestamoById(id);
    }

    @PostMapping
    public PrestamosDTO createPrestamo(@RequestBody PrestamosDTO prestamo) {
        return prestamosService.savePrestamo(prestamo);
    }

    @PutMapping("/{id}")
    public PrestamosDTO updatePrestamo(@PathVariable Long id, @RequestBody PrestamosDTO prestamo) {
        PrestamosDTO existingPrestamo = prestamosService.getPrestamoById(id);
        if (existingPrestamo != null) {
            return prestamosService.savePrestamo(prestamo);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePrestamo(@PathVariable Long id) {
        prestamosService.deletePrestamo(id);
    }
}
