package br.com.cwi.racha.service.amizade;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.factories.UsuarioSetListFactory;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import br.com.cwi.racha.validator.VerificarSeSaoAmigosValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DesfazerAmizadeServiceTest {

    @InjectMocks
    private DesfazerAmizadeService validator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    @Mock
    private VerificarSeSaoAmigosValidator seSaoAmigosValidator;

    Set<Usuario> usuariosList = null;
    Usuario usuario = null;
    Usuario solicitante = null;

    @BeforeEach
    void setUp() {
        usuariosList = UsuarioSetListFactory.listar();
        usuario = UsuarioFactory.getUsuario();
        solicitante = UsuarioFactory.getUsuario();
    }


    @Test
    @DisplayName("remocao deve ocorrer com sucesso")
    void remocao() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarUsuarioService.porIdSolicitante(solicitante.getId())).thenReturn(solicitante);
        
        validator.desfazer(usuario.getId(), solicitante.getId());

        verify(usuarioRepository).save(usuario);
        assertTrue(usuario.getAmigos().isEmpty());
        assertTrue(solicitante.getAmigos().isEmpty());
    }

    @Test
    @DisplayName("remocao mesmo id nao deve ocorrer erro")
    void remocaoComMesmoId() {

        assertThrows(NegocioException.class, () -> validator.desfazer(usuario.getId(), usuario.getId()));
        verify(usuarioRepository, never()).save(usuario);
    }


    @Test
    @DisplayName("remocao nao sendo amigos nao deve ocorrer erro")
    void remocaoSendoAmigos() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarUsuarioService.porIdSolicitante(solicitante.getId())).thenReturn(solicitante);

        doThrow(NegocioException.class).when(seSaoAmigosValidator).validarAmizadeInexistente(usuario.getAmigos(), solicitante);

        assertThrows(NegocioException.class, () -> validator.desfazer(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    @DisplayName("remocao com usuario inexistente nao deve ocorrer erro")
    void remocaoComUsuarioInexistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.desfazer(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    @DisplayName("remocao com solicitante inexistente nao deve ocorrer erro")
    void remocaoComSolicitanteInexistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.desfazer(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

}