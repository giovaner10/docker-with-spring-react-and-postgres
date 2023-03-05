package br.com.cwi.racha.validator;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class VerificarSeJaCurtiuValidator {

    public void validarCurtida(List<Usuario> usuarios, Usuario solicitante) {
        if (usuarios.contains(solicitante)) {
            throw new NegocioException(HttpStatus.UNPROCESSABLE_ENTITY, "Publicacao já curtida");
        }
    }

    public void invalidarCurtida(List<Usuario> usuarios, Usuario solicitante) {
        if (!usuarios.contains(solicitante)) {
            throw new NegocioException(HttpStatus.UNPROCESSABLE_ENTITY, "Impossivel remover, publicacao nao curtida");
        }
    }

}
