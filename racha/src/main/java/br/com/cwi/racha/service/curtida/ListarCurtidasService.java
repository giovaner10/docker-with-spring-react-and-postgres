package br.com.cwi.racha.service.curtida;

import br.com.cwi.racha.controller.response.curtida.CurtidaResponse;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.mapper.CurtidaMapper;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ListarCurtidasService {

    @Autowired
    private BuscarValidarPostagemService buscarValidarPostagemService;


    @Transactional
    public List<CurtidaResponse> listar(Long idPostagem) {

        Postagem postagem = buscarValidarPostagemService.porId(idPostagem);

        return postagem.getCurtidas()
               .stream()
               .map(CurtidaMapper::toResponse)
               .collect(Collectors.toList());
    }

}
