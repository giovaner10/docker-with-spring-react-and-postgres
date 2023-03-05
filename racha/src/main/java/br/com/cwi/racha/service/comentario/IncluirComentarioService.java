package br.com.cwi.racha.service.comentario;

import br.com.cwi.racha.controller.request.comentario.ComentarioRequest;
import br.com.cwi.racha.domain.Comentario;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.repository.ComentarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.racha.mapper.ComentarioMapper.toEntity;


@Service
public class IncluirComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private BuscarValidarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarValidarPostagemService buscarValidarPostagemService;


    @Transactional
    public void comentar(Long idUsuario, Long idPostagem, ComentarioRequest request) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);
        Postagem postagem = buscarValidarPostagemService.porId(idPostagem);

        Comentario comentario = toEntity(request);

        comentario.setUsuario(usuario);
        comentario.setPostagem(postagem);

        comentarioRepository.save(comentario);
    }

}
