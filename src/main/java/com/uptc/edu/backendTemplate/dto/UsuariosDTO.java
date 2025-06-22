package com.uptc.edu.backendTemplate.dto;

import com.uptc.edu.backendTemplate.model.UsuariosModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {

    Long dni; String nombre; String apellido; int edad;

    public static UsuariosDTO fromModel(UsuariosModel usuarios) {
        if(usuarios == null) {
            return null;
        }
        UsuariosDTO usuariosdto = new UsuariosDTO(
                usuarios.getDni(), usuarios.getNombre(),usuarios.getApellido(),
                usuarios.getEdad());

        return usuariosdto;
    }
    public static List<UsuariosDTO> fromModelList(List<UsuariosModel> usuariosList) {
        if (usuariosList == null) {
            return null;
        }
        List<UsuariosDTO> usuariosDTOList = new ArrayList<>();
        for (UsuariosModel usuarios : usuariosList) {
            UsuariosDTO usuariosDTO = new UsuariosDTO(
                    usuarios.getDni(), usuarios.getNombre(), usuarios.getApellido(), usuarios.getEdad());
            usuariosDTOList.add(usuariosDTO);
        }
        return usuariosDTOList;
    }
}
