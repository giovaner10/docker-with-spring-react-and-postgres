package br.com.cwi.racha.service.usuario;

import br.com.cwi.racha.controller.request.usuario.UsuarioAtualizarRequest;
import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.racha.mapper.UsuarioMapper.toResponse;


@Service
public class AtualizarUsuarioService {

    @Autowired
    private BuscarValidarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public UsuarioResponse atualizar(Long idUsuario, UsuarioAtualizarRequest request) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);

        usuario.setFoto(request.getFoto());
        usuario.setApelido(request.getApelido());
        usuario.setNomeCompleto(request.getNomeCompleto());

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

}
