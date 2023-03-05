package br.com.cwi.racha.controller.response.comentario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioResponse {

    private Long id;

    private String comentario;

    private String fotoUsuario;

    private String nomeUsuario;

    private Long idUsuario;

    private Long idPostagem;

}
