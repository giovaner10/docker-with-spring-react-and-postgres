package br.com.cwi.racha.service.validator;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarUsuarioServiceTest {

    @InjectMocks
    private BuscarValidarUsuarioService validator;

    @Mock
    private UsuarioRepository usuarioRepository;


    Usuario usuario = null;
    @BeforeEach
    void setUp() {
        usuario = UsuarioFactory.getUsuario();
    }


    @Test
    @DisplayName("Deve devolver usuario valido")
    void deveDevolverUsuarioValido() {
        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Usuario usuarioBuscado = validator.porId(usuario.getId());

        assertEquals(usuario, usuarioBuscado);
    }

    @Test
    @DisplayName("deve lancar excessao ao buscar usuario indisponivel")
    void deveLancarExcessaoAoNaoEcontrarUsuario() {

        doThrow(NegocioException.class).when(usuarioRepository).findById(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.porId(usuario.getId()));
    }

    @Test
    @DisplayName("Deve devolver usuario valido - solicitacao")
    void deveDevolverUsuarioValidoSolicitante() {

        when(usuarioRepository.findById(usuario.getId())).thenReturn(Optional.of(usuario));

        Usuario usuarioBuscado = validator.porIdSolicitante(usuario.getId());

        assertEquals(usuario, usuarioBuscado);
    }

    @Test
    @DisplayName("deve lancar excessao ao buscar usuario indisponivel - solicitacao")
    void deveLancarExcessaoAoNaoEcontrarUsuarioSolicitante() {

        doThrow(NegocioException.class).when(usuarioRepository).findById(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.porIdSolicitante(usuario.getId()));
    }

}