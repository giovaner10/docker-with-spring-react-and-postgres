package br.com.cwi.racha.factories;


import br.com.cwi.racha.controller.response.comentario.ComentarioResponse;
import br.com.cwi.racha.domain.Curtida;

public class CurtidaFactory {

    public static Curtida getCurtida() {
        Curtida curtida = new Curtida();
        curtida.setPostagem(PostagemFactory.getPostagem());
        curtida.setUsuario(UsuarioFactory.getUsuario());

        return curtida;
    }

}
