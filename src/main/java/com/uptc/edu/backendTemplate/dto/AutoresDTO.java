package com.uptc.edu.backendTemplate.dto;

import com.uptc.edu.backendTemplate.model.AutoresModel;
import com.uptc.edu.backendTemplate.model.UsuariosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class AutoresDTO {

    Long idAutor; String nombre; String apellido; Date fechaNacimiento; Date fechaMuerte;

    public static AutoresDTO fromModel(AutoresModel autores) {
        if(autores == null) {
            return null;
        }
        AutoresDTO autoresdto = new AutoresDTO(
                autores.getIdAutor(), autores.getNombre(),autores.getApellido(),
                autores.getFechaNacimiento(),autores.getFechaMuerte());

        return autoresdto;
    }
    public static List<AutoresDTO> fromModelList(List<AutoresModel> autoresList) {
        if (autoresList == null) {
            return null;
        }
        List<AutoresDTO> autoresDTOList = new ArrayList<>();
        for (AutoresModel autores : autoresList) {
            AutoresDTO autoresDTO = new AutoresDTO(
                    autores.getIdAutor(), autores.getNombre(), autores.getApellido(), autores.getFechaNacimiento(),autores.getFechaMuerte());
            autoresDTOList.add(autoresDTO);
        }
        return autoresDTOList;
    }

}
