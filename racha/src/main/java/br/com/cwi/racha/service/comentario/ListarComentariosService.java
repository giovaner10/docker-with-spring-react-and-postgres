package br.com.cwi.racha.service.comentario;

import br.com.cwi.racha.controller.response.comentario.ComentarioResponse;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.mapper.ComentarioMapper;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ListarComentariosService {

    @Autowired
    private BuscarValidarPostagemService buscarValidarPostagemService;


    @Transactional
    public List<ComentarioResponse> listar(Long idPostagem) {

        Postagem postagem = buscarValidarPostagemService.porId(idPostagem);

        return postagem.getComentarios()
               .stream()
               .map(ComentarioMapper::toResponse)
               .collect(Collectors.toList());
    }

}
