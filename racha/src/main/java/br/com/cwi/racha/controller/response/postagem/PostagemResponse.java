package br.com.cwi.racha.controller.response.postagem;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostagemResponse {

    private Long id;

    private String foto;

    private String legenda;

    private boolean privada;

    private String fotoUsuario;

    private String nomeUsuario;

    private Long idUsuario;

    private LocalDateTime data;
}
