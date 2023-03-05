package br.com.cwi.racha.factories;


import br.com.cwi.racha.controller.request.usuario.UsuarioAtualizarRequest;
import br.com.cwi.racha.controller.request.usuario.UsuarioRequest;
import br.com.cwi.racha.domain.Usuario;

import java.time.LocalDate;

public class UsuarioFactory {

    public static Usuario getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(SimpleFactory.getRandomLong());
        usuario.setNomeCompleto("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setApelido("apelido");
        usuario.setDataNasimento(LocalDate.of(2000, 6, 26));
        return usuario;
    }

    public static UsuarioRequest getUsuarioRequest() {
        UsuarioRequest usuario = new UsuarioRequest();
        usuario.setNomeCompleto("Usuário de teste");
        usuario.setEmail("teste@cwi.com.br");
        usuario.setApelido("apelido");
        usuario.setSenha("minha senha");
        usuario.setDataNasimento(LocalDate.of(2000, 6, 26));
        return usuario;
    }

    public static UsuarioAtualizarRequest getUsuarioAtualizarRequest() {
        UsuarioAtualizarRequest usuario = new UsuarioAtualizarRequest();
        usuario.setNomeCompleto("Usuário de teste");
        usuario.setApelido("apelido");
        usuario.setFoto("minha foto");
        return usuario;
    }

}
