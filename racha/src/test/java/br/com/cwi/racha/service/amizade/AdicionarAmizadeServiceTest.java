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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdicionarAmizadeServiceTest {

    @InjectMocks
    private AdicionarAmizadeService validator;

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
    @DisplayName("Confirmacao deve ocorrer com sucesso")
    void confirmar() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarUsuarioService.porIdSolicitante(solicitante.getId())).thenReturn(solicitante);

        validator.confirmar(usuario.getId(), solicitante.getId());

        verify(usuarioRepository).save(usuario);
        assertFalse(usuario.getAmigos().isEmpty());
        assertFalse(solicitante.getAmigos().isEmpty());
    }

    @Test
    @DisplayName("Confirmacao mesmo id nao deve ocorrer erro")
    void solicitarComMesmoId() {

        assertThrows(NegocioException.class, () -> validator.confirmar(usuario.getId(), usuario.getId()));
        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    @DisplayName("Confirmacao ja sendo amigos nao deve ocorrer erro")
    void solicitarSendoAmigos() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarUsuarioService.porIdSolicitante(solicitante.getId())).thenReturn(solicitante);

        doThrow(NegocioException.class).when(seSaoAmigosValidator).validarAmizade(usuario.getAmigos(), solicitante);

        assertThrows(NegocioException.class, () -> validator.confirmar(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    @DisplayName("Confirmacao com solicitacao ja enviada nao deve ocorrer erro")
    void solicitarComSolicitacaJaEnviada() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarUsuarioService.porIdSolicitante(solicitante.getId())).thenReturn(solicitante);

        doThrow(NegocioException.class).when(seSaoAmigosValidator).validarSolicitacaoExistente(usuario.getAmigos(), solicitante);

        assertThrows(NegocioException.class, () -> validator.confirmar(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    @DisplayName("Confirmacao com usuario inexistente nao deve ocorrer erro")
    void solicitarComUsuarioInexistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.confirmar(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

    @Test
    @DisplayName("Confirmacao com solicitante inexistente nao deve ocorrer erro")
    void solicitarComSolicitanteInexistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.confirmar(usuario.getId(), solicitante.getId()));

        verify(usuarioRepository, never()).save(usuario);
    }

}