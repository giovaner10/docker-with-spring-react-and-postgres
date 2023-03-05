package br.com.cwi.racha.controller;

import br.com.cwi.racha.controller.request.usuario.UsuarioIdRequest;
import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.service.amizade.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/amizades")
public class AmizadeController {

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private BuscarUsuariosService buscarUsuariosService;

    @Autowired
    private ListarUsuariosAmigosService listarUsuariosAmigosService;

    @Autowired
    private AdicionarAmizadeService adicionarAmizadeService;

    @Autowired
    private DesfazerAmizadeService desfazerAmizadeService;

    @Autowired
    private ListarSolicitacoesService listarSolicitacoesService;


    @PutMapping("/{usuarioId}/solicitar")
    @ResponseStatus(HttpStatus.OK)
    public void solicitar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioIdRequest idRequest) {
        solicitarAmizadeService.solicitar(usuarioId, idRequest.getId());
    }

    @PutMapping("/{usuarioId}/confirmar")
    @ResponseStatus(HttpStatus.OK)
    public void confirmar(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioIdRequest idRequest) {
        adicionarAmizadeService.confirmar(usuarioId, idRequest.getId());
    }

    @PutMapping("/{usuarioId}/desfazer")
    @ResponseStatus(HttpStatus.OK)
    public void desfazer(@PathVariable Long usuarioId, @Valid @RequestBody UsuarioIdRequest idRequest) {
        desfazerAmizadeService.desfazer(usuarioId, idRequest.getId());
    }

    @GetMapping("/{usuarioId}/buscar")
    public List<UsuarioResponse> buscar(@PathVariable Long usuarioId, @RequestParam(defaultValue = "cwi") String texto) {
        return buscarUsuariosService.buscarDisponiveis(usuarioId, texto.toLowerCase());
    }

    @GetMapping("/{usuarioId}/amigos")
    public List<UsuarioResponse> listarAmigos(@PathVariable Long usuarioId,  @RequestParam(defaultValue = "cwi") String texto) {
        return listarUsuariosAmigosService.listar(usuarioId, texto.toLowerCase());
    }

    @GetMapping("/{usuarioId}/solicitacoes")
    public List<UsuarioResponse> solicitacoes(@PathVariable Long usuarioId) {
        return listarSolicitacoesService.listar(usuarioId);
    }

}
