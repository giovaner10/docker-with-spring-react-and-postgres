package br.com.cwi.racha.factories;


import br.com.cwi.racha.controller.request.postagem.PostagemRequest;
import br.com.cwi.racha.controller.response.postagem.PostagemResponse;
import br.com.cwi.racha.domain.Postagem;

import java.time.LocalDateTime;

public class PostagemFactory {

    public static Postagem getPostagem() {
        Postagem postagem = new Postagem();
        postagem.setId(SimpleFactory.getRandomLong());
        postagem.setPrivada(false);
        postagem.setDataPostagem(LocalDateTime.of(2023, 6, 7, 13, 15));
        postagem.setLegenda("Legenda foto");
        postagem.setFoto("Minha fot");
        postagem.setUsuario(UsuarioFactory.getUsuario());
        return postagem;
    }

    public static PostagemRequest getPostagemRequest() {
        PostagemRequest postagem = new PostagemRequest();
        postagem.setPrivada(false);
        postagem.setLegenda("Legenda foto");
        postagem.setFoto("Minha fot");
        return postagem;
    }

    public static PostagemResponse getPostagemResponse() {
        PostagemResponse postagem = new PostagemResponse();
        postagem.setId(SimpleFactory.getRandomLong());
        postagem.setPrivada(false);
        postagem.setLegenda("Legenda foto");
        postagem.setFoto("Minha fot");
        return postagem;
    }
}
