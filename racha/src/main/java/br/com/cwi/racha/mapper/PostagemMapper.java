package br.com.cwi.racha.mapper;


import br.com.cwi.racha.controller.request.postagem.PostagemRequest;
import br.com.cwi.racha.controller.response.postagem.PostagemResponse;
import br.com.cwi.racha.domain.Postagem;

public class PostagemMapper {

    public static Postagem toEntity(PostagemRequest request) {

        Postagem entity = new Postagem();

        entity.setLegenda(request.getLegenda());
        entity.setPrivada(request.isPrivada());
        entity.setFoto(request.getFoto());

        return entity;
    }

    public static PostagemResponse toResponse(Postagem entity) {

        PostagemResponse response = new PostagemResponse();

        response.setId(entity.getId());
        response.setFoto(entity.getFoto());
        response.setLegenda(entity.getLegenda());
        response.setPrivada(entity.isPrivada());
        response.setNomeUsuario(entity.getUsuario().getNomeCompleto());
        response.setIdUsuario(entity.getUsuario().getId());
        response.setFotoUsuario(entity.getUsuario().getFoto());
        response.setData(entity.getDataPostagem());

        return response;
    }
}
