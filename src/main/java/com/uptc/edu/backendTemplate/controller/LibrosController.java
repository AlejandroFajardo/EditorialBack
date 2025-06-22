package com.uptc.edu.backendTemplate.controller;

import com.uptc.edu.backendTemplate.model.LibrosModel;
import com.uptc.edu.backendTemplate.service.LibrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/libros")
public class LibrosController {

    @Autowired
    private LibrosService libroService;

    @GetMapping
    public List<LibrosModel> getAllLibros() {
        return libroService.getAllLibros();
    }

    @GetMapping("/{id}")
    public LibrosModel getLibroById(@PathVariable Long id) {
        return libroService.getLibroById(id);
    }

    @PostMapping
    public LibrosModel createLibro(@RequestBody LibrosModel libro) {
        return libroService.saveLibro(libro);
    }

    @PutMapping("/{id}")
    public LibrosModel updateLibro(@PathVariable Long id, @RequestBody LibrosModel libro) {
        LibrosModel existingLibro = libroService.getLibroById(id);
        if (existingLibro != null) {
            existingLibro.setTitulo(libro.getTitulo());
            existingLibro.setAutor(libro.getAutor());
            return libroService.saveLibro(existingLibro);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteLibro(@PathVariable Long id) {
        libroService.deleteLibro(id);
    }
}
