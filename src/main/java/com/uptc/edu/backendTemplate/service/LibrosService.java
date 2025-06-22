package com.uptc.edu.backendTemplate.service;

import com.uptc.edu.backendTemplate.model.LibrosModel;
import com.uptc.edu.backendTemplate.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibrosService {

    @Autowired
    private LibrosRepository libroRepository;

    public List<LibrosModel> getAllLibros() {
        return libroRepository.findAll();
    }

    public LibrosModel getLibroById(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    public LibrosModel saveLibro(LibrosModel libro) {
        return libroRepository.save(libro);
    }

    public void deleteLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
