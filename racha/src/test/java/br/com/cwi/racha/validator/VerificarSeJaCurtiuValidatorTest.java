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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class VerificarSeJaCurtiuValidatorTest {

    @InjectMocks
    private VerificarSeJaCurtiuValidator validator;

    List<Usuario> usuariosList = null;
    Usuario usuario = null;
    @BeforeEach
    void setUp() {
        usuariosList = UsuarioSetListFactory.listarList();
        usuario = UsuarioFactory.getUsuario();
    }

    @Test
    void validarCurtida() {
        assertDoesNotThrow(()-> validator.validarCurtida(usuariosList, usuario));
    }

    @Test
    void naoValidarCurtida() {
        usuariosList.add(usuario);
        assertThrows(NegocioException.class,()-> validator.validarCurtida(usuariosList, usuario));
    }

    @Test
    void validarCurtidaInexistente() {
        assertThrows(NegocioException.class,()-> validator.invalidarCurtida(usuariosList, usuario));
    }

    @Test
    void naoValidarCurtidaInexistente() {
        usuariosList.add(usuario);
        assertDoesNotThrow(()-> validator.invalidarCurtida(usuariosList, usuario));
    }

}