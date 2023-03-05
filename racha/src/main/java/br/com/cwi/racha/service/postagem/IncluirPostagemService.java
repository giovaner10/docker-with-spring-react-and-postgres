package br.com.cwi.racha.service.postagem;

import br.com.cwi.racha.controller.request.postagem.PostagemRequest;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.repository.PostagemRepository;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static br.com.cwi.racha.mapper.PostagemMapper.toEntity;


@Service
public class IncluirPostagemService {

    @Autowired
    private BuscarValidarUsuarioService buscarUsuarioService;

    @Autowired
    private PostagemRepository postagemRepository;


    @Transactional
    public void postar(Long idUsuario, PostagemRequest request) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);

        Postagem postagem = toEntity(request);
        postagem.setDataPostagem(LocalDateTime.now());
        postagem.setUsuario(usuario);
        postagemRepository.save(postagem);
    }

}
