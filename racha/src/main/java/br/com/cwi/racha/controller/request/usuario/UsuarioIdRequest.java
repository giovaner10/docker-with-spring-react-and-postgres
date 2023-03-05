package br.com.cwi.racha.controller.request.usuario;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UsuarioIdRequest {

    @NotNull
    private Long id;

}
