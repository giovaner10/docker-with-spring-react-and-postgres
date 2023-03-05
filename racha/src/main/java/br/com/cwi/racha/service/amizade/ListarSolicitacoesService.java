package br.com.cwi.racha.service.amizade;

import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.mapper.UsuarioMapper;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class ListarSolicitacoesService {

    @Autowired
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    public List<UsuarioResponse> listar(Long idUsuario) {

        Usuario usuario = buscarValidarUsuarioService.porId(idUsuario);

        return usuario.getSolicitacoes()
                .stream()
                .map(UsuarioMapper::toResponse).collect(toList());
    }

}
