package br.com.cwi.racha.controller;

import br.com.cwi.racha.controller.request.postagem.PostagemRequest;
import br.com.cwi.racha.controller.response.postagem.PostagemResponse;
import br.com.cwi.racha.service.postagem.IncluirPostagemService;
import br.com.cwi.racha.service.postagem.ListarPostagensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

    @Autowired
    private IncluirPostagemService incluirPostagemService;

    @Autowired
    private ListarPostagensService listarPostagensService;


    @PostMapping("/{usuarioId}")
    public void incluir(@PathVariable Long usuarioId, @Valid @RequestBody PostagemRequest request) {
        incluirPostagemService.postar(usuarioId, request);
    }

    @GetMapping("/{usuarioId}")
    public List<PostagemResponse> listar(@PathVariable Long usuarioId) {
        return listarPostagensService.listarMinhas(usuarioId);
    }

    @GetMapping("/{usuarioId}/rede")
    public List<PostagemResponse> listarRede(@PathVariable Long usuarioId) {
        return listarPostagensService.listarRede(usuarioId);
    }

}
