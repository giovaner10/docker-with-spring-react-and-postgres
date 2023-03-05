package br.com.cwi.racha.validator;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.factories.UsuarioSetListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class VerificarSeSaoAmigosValidatorTest {

    @InjectMocks
    private VerificarSeSaoAmigosValidator validator;

    Set<Usuario> usuariosList = null;
    Usuario usuario = null;
    @BeforeEach
    void setUp() {
        usuariosList = UsuarioSetListFactory.listar();
        usuario = UsuarioFactory.getUsuario();
    }

    @Test
    void validarAmizade() {
        assertDoesNotThrow(()-> validator.validarAmizade(usuariosList, usuario));
    }

    @Test
    void naoValidarAmizade() {
        usuariosList.add(usuario);
        assertThrows(NegocioException.class,()-> validator.validarAmizade(usuariosList, usuario));
    }

    @Test
    void validarAmizadeInexistente() {
        assertThrows(NegocioException.class,()-> validator.validarAmizadeInexistente(usuariosList, usuario));
    }

    @Test
    void naoValidarAmizadeInexistente() {
        usuariosList.add(usuario);
        assertDoesNotThrow(()-> validator.validarAmizadeInexistente(usuariosList, usuario));
    }

    @Test
    void validarSolicitacao() {
        assertDoesNotThrow(()-> validator.validarSolicitacao(usuariosList, usuario));
    }

    @Test
    void naoValidarSolicitacao() {
        usuariosList.add(usuario);
        assertThrows(NegocioException.class,()-> validator.validarSolicitacao(usuariosList, usuario));
    }

    @Test
    void validarSolicitacaoExistente() {
        assertThrows(NegocioException.class,()-> validator.validarSolicitacaoExistente(usuariosList, usuario));
    }

    @Test
    void naoValidarSolicitacaoExistente() {
        usuariosList.add(usuario);
        assertDoesNotThrow(()-> validator.validarSolicitacaoExistente(usuariosList, usuario));
    }

}