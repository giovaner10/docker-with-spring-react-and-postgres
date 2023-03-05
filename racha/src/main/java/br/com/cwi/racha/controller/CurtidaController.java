package br.com.cwi.racha.controller;

import br.com.cwi.racha.controller.response.curtida.CurtidaResponse;
import br.com.cwi.racha.service.curtida.IncluirCurtidaService;
import br.com.cwi.racha.service.curtida.ListarCurtidasService;
import br.com.cwi.racha.service.curtida.RemoverCurtidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curtidas")
public class CurtidaController {

    @Autowired
    private IncluirCurtidaService incluirCurtidaService;

    @Autowired
    private RemoverCurtidaService removerCurtidaService;

    @Autowired
    private ListarCurtidasService listarCurtidasService;


    @PutMapping("/{usuarioId}/{postagemId}")
    public void curtirPostagem(@PathVariable Long usuarioId, @PathVariable Long postagemId) {
        incluirCurtidaService.curtir(usuarioId, postagemId);
    }

    @GetMapping("/{postagemId}")
    public List<CurtidaResponse> listarPostagemCurtidas(@PathVariable Long postagemId) {
        return listarCurtidasService.listar(postagemId);
    }

    @PutMapping("/{usuarioId}/{postagemId}/remover")
    public void removerCurtida(@PathVariable Long usuarioId, @PathVariable Long postagemId){// @PathVariable Long curtidaId) {
        removerCurtidaService.remover(usuarioId, postagemId);
    }

}
