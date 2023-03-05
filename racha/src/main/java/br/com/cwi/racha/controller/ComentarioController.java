package br.com.cwi.racha.controller;

import br.com.cwi.racha.controller.request.comentario.ComentarioRequest;
import br.com.cwi.racha.controller.response.comentario.ComentarioResponse;
import br.com.cwi.racha.service.comentario.IncluirComentarioService;
import br.com.cwi.racha.service.comentario.ListarComentariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private IncluirComentarioService incluirComentarioService;

    @Autowired
    private ListarComentariosService listarComentariosService;


    @PutMapping("/{usuarioId}/{postagemId}")
    public void comentarPostagem(@PathVariable Long usuarioId, @PathVariable Long postagemId, @Valid @RequestBody ComentarioRequest request) {
         incluirComentarioService.comentar(usuarioId, postagemId, request);
    }

    @GetMapping("/{postagemId}")
    public List<ComentarioResponse> listarPostagemComentarios(@PathVariable Long postagemId) {
        return listarComentariosService.listar(postagemId);
    }

}
