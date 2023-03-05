package br.com.cwi.racha.controller.response.usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = "id")
public class UsuarioResponse {

    private Long id;
    private String nomeCompleto;
    private String email;
    private String foto;
    private Long idade;
    private String apelido;
}
