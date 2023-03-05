package br.com.cwi.racha.controller;

import br.com.cwi.racha.controller.request.usuario.UsuarioAtualizarRequest;
import br.com.cwi.racha.controller.request.usuario.UsuarioRequest;
import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.service.usuario.AtualizarUsuarioService;
import br.com.cwi.racha.service.usuario.IncluirUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IncluirUsuarioService incluirUsuarioService;

    @Autowired
    private AtualizarUsuarioService atualizarUsuarioService;


    @PostMapping
    public UsuarioResponse incluir(@Valid @RequestBody UsuarioRequest request) {
        return incluirUsuarioService.incluir(request);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioResponse atualizar(@PathVariable Long usuarioId,@Valid @RequestBody UsuarioAtualizarRequest request) {
        return atualizarUsuarioService.atualizar(usuarioId, request);
    }

}
