package br.com.cwi.racha.mapper;


import br.com.cwi.racha.controller.response.curtida.CurtidaResponse;
import br.com.cwi.racha.domain.Curtida;

public class CurtidaMapper {


    public static CurtidaResponse toResponse(Curtida entity) {

        CurtidaResponse response = new CurtidaResponse();

        response.setId(entity.getId());
        response.setNomeUsuario(entity.getUsuario().getNomeCompleto());
        response.setIdUsuario(entity.getUsuario().getId());
        response.setFotoUsuario(entity.getUsuario().getFoto());
        response.setIdPostagem(entity.getPostagem().getId());

        return response;
    }
}
