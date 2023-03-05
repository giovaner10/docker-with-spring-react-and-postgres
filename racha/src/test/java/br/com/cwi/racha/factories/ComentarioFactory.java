package br.com.cwi.racha.factories;


import br.com.cwi.racha.controller.request.comentario.ComentarioRequest;
import br.com.cwi.racha.domain.Comentario;

public class ComentarioFactory {

    public static Comentario getComentario() {
        Comentario comentario = new Comentario();
        comentario.setId(SimpleFactory.getRandomLong());
        comentario.setComentario("Meu comentario");
        comentario.setUsuario(UsuarioFactory.getUsuario());
        comentario.setPostagem(PostagemFactory.getPostagem());
        return comentario;
    }

    public static ComentarioRequest getComentarioRequest() {
        ComentarioRequest comentario = new ComentarioRequest();
        comentario.setComentario("Meu comentario");
        return comentario;
    }

}
