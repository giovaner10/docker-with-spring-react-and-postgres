package br.com.cwi.racha.service.amizade;

import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.mapper.UsuarioMapper;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class BuscarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarValidarUsuarioService buscarValidarUsuarioService;


    public List<UsuarioResponse> buscarDisponiveis(Long idUsuario, String texto) {

        Usuario usuario = buscarValidarUsuarioService.porId(idUsuario);

       return usuarioRepository.findUsuariosDisponiveis(usuario.getId(), texto, texto)
                .stream()
                .filter(u -> !usuario.getAmigos().contains(u))
                .map(UsuarioMapper::toResponse).collect(toList());
    }

}
