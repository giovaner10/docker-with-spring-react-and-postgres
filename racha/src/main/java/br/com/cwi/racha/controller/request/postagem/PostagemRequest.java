package br.com.cwi.racha.controller.request.postagem;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostagemRequest {

    @NotBlank
    @URL
    private String foto;

    private String legenda;

    @NotNull
    private boolean privada;
}
