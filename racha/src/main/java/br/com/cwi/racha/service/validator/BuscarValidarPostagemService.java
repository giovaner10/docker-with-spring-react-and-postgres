package br.com.cwi.racha.service.validator;

import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class BuscarValidarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem porId(Long idPostagem) {

        return postagemRepository.findById(idPostagem).orElseThrow(() -> new NegocioException(HttpStatus.NOT_FOUND, "Postagem não encontrado"));
    }

}
