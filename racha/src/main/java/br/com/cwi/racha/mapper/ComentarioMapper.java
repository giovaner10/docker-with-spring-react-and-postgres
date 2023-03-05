package br.com.cwi.racha.mapper;


import br.com.cwi.racha.controller.request.comentario.ComentarioRequest;
import br.com.cwi.racha.controller.response.comentario.ComentarioResponse;
import br.com.cwi.racha.domain.Comentario;

public class ComentarioMapper {

    public static Comentario toEntity(ComentarioRequest request) {

        Comentario entity = new Comentario();

        entity.setComentario(request.getComentario());

        return entity;
    }

    public static ComentarioResponse toResponse(Comentario entity) {

        ComentarioResponse response = new ComentarioResponse();

        response.setId(entity.getId());
        response.setComentario(entity.getComentario());
        response.setNomeUsuario(entity.getUsuario().getNomeCompleto());
        response.setIdUsuario(entity.getUsuario().getId());
        response.setFotoUsuario(entity.getUsuario().getFoto());
        response.setIdPostagem(entity.getPostagem().getId());

        return response;
    }
}
