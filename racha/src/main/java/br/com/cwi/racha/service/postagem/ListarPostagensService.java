package br.com.cwi.racha.service.postagem;

import br.com.cwi.racha.controller.response.postagem.PostagemResponse;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.mapper.PostagemMapper;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ListarPostagensService {

    @Autowired
    private BuscarValidarUsuarioService buscarUsuarioService;


    public List<PostagemResponse> listarMinhas(Long idUsuario) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);

        return usuario.getPostagens()
                .stream()
                .map(PostagemMapper::toResponse)
                .collect(Collectors.toList());
    }

    public List<PostagemResponse> listarRede(Long idUsuario) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);

        List<Postagem> todasPostagens = new ArrayList<>();

        todasPostagens.addAll(usuario.getPostagens());

        usuario.getAmigos()
                .stream()
                .map(Usuario::getPostagens)
                .map(todasPostagens::addAll);

        return todasPostagens
                .stream()
                .map(PostagemMapper::toResponse)
                .collect(Collectors.toList());
    }

}
