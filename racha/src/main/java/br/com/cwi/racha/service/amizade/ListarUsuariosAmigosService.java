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
public class ListarUsuariosAmigosService {


    @Autowired
    private BuscarValidarUsuarioService buscarValidarUsuarioService;


    public List<UsuarioResponse> listar(Long idUsuario, String text) {

        Usuario usuario = buscarValidarUsuarioService.porId(idUsuario);

        return usuario.getAmigos()
                .stream()
                .filter(u -> u.getNomeCompleto().toLowerCase().contains(text.toLowerCase()) || u.getEmail().toLowerCase().contains(text.toLowerCase()) )
                .map(UsuarioMapper::toResponse).collect(toList());
    }

}
