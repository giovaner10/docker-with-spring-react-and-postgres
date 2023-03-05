package br.com.cwi.racha.controller.request.usuario;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UsuarioAtualizarRequest {

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String apelido;

    @NotBlank
    @URL
    private String foto;
}
