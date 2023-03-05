package br.com.cwi.racha.service.curtida;

import br.com.cwi.racha.domain.Curtida;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.repository.CurtidaRepository;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import br.com.cwi.racha.validator.VerificarSeJaCurtiuValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RemoverCurtidaService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private BuscarValidarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarValidarPostagemService buscarValidarPostagemService;

    @Autowired
    private VerificarSeJaCurtiuValidator verificarSeJaCurtiuValidator;


    @Transactional
    public void remover(Long idUsuario, Long idPostagem) {

        Usuario usuario = buscarUsuarioService.porId(idUsuario);
        Postagem postagem = buscarValidarPostagemService.porId(idPostagem);

        verificarSeJaCurtiuValidator.invalidarCurtida(postagem.getCurtidas().stream().map(Curtida::getUsuario).collect(Collectors.toList()), usuario);
        List<Curtida> curtidas = postagem.getCurtidas().stream().filter(curtida -> curtida.getUsuario().getId() == usuario.getId()).collect(Collectors.toList());

        curtidaRepository.deleteAll(curtidas);
    }

}
