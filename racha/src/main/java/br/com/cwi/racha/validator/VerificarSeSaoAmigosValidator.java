package br.com.cwi.racha.validator;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;


@Component
public class VerificarSeSaoAmigosValidator {

    public void validarAmizade(Set<Usuario> usuarios, Usuario solicitante) {
        if (usuarios.contains(solicitante)) {
            throw new NegocioException(HttpStatus.UNPROCESSABLE_ENTITY, "Já são amigos");
        }
    }

    public void validarAmizadeInexistente(Set<Usuario> usuarios, Usuario solicitante) {
        if (!usuarios.contains(solicitante)) {
            throw new NegocioException(HttpStatus.UNPROCESSABLE_ENTITY, "Voces ainda não são amigos");
        }
    }

    public void validarSolicitacao(Set<Usuario> usuarios, Usuario solicitante) {
        if (usuarios.contains(solicitante)) {
            throw new NegocioException(HttpStatus.UNPROCESSABLE_ENTITY, "Solicitacao de amizade pendente entre os usuarios");
        }
    }

    public void validarSolicitacaoExistente(Set<Usuario> usuarios, Usuario solicitante) {
        if (!usuarios.contains(solicitante)) {
            throw new NegocioException(HttpStatus.UNPROCESSABLE_ENTITY, "Solicitacao de amizade deve ser enviada antes");
        }
    }

}
