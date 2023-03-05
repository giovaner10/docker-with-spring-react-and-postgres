package br.com.cwi.racha.mapper;


import br.com.cwi.racha.controller.request.usuario.UsuarioRequest;
import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {

        Usuario entity = new Usuario();

        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setEmail(request.getEmail());
        entity.setDataNasimento(request.getDataNasimento());
        entity.setApelido(request.getApelido());
        entity.setFoto(request.getFoto());

        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {

        UsuarioResponse response = new UsuarioResponse();

        response.setId(entity.getId());
        response.setNomeCompleto(entity.getNomeCompleto());
        response.setEmail(entity.getEmail());
        response.setIdade(ChronoUnit.YEARS.between(entity.getDataNasimento(), LocalDate.now()));
        response.setFoto(entity.getFoto());
        response.setApelido(entity.getApelido());

        return response;
    }

}
