package br.com.cwi.racha.service.amizade;

import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.factories.UsuarioSetListFactory;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarUsuariosServiceTest {

    @InjectMocks
    private BuscarUsuariosService validator;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    List<Usuario> usuariosList = null;
    Usuario usuario = null;
    Usuario solicitante = null;
    @BeforeEach
    void setUp() {
        usuariosList = UsuarioSetListFactory.listarList();
        usuario = UsuarioFactory.getUsuario();
        solicitante = UsuarioFactory.getUsuario();
    }


    @Test
    @DisplayName("Deve econtrar o usuario solicitado")
    void solicitar() {

        String nome = "FLoRzINha";
        solicitante.setNomeCompleto(nome);
        usuariosList.add(solicitante);

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(usuarioRepository.findUsuariosDisponiveis(usuario.getId(), nome, nome)).thenReturn(usuariosList);

        List<UsuarioResponse> usuarioResponses = validator.buscarDisponiveis(usuario.getId(), nome);

        assertFalse(usuarioResponses.isEmpty());
        assertTrue(usuarioResponses.get(usuarioResponses.size() -1).getId() == solicitante.getId());
    }

    @Test
    @DisplayName("Nao deve econtrar o usuario solicitado por serem ja amigos")
    void solicitarEncontraAmigo() {
        String nome = "FLoRzINha";
        solicitante.setNomeCompleto(nome);
        usuariosList.add(solicitante);
        usuario.getAmigos().add(solicitante);

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(usuarioRepository.findUsuariosDisponiveis(usuario.getId(), nome, nome)).thenReturn(usuariosList);

        List<UsuarioResponse> usuarioResponses = validator.buscarDisponiveis(usuario.getId(), nome);

        assertFalse(usuarioResponses.isEmpty());
        assertFalse(usuarioResponses.get(usuarioResponses.size() -1).getId() == solicitante.getId());
    }

    @Test
    @DisplayName("Nao deve econtrar o usuario")
    void solicitarEncontraAmigoComUsuarioInvalido() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.buscarDisponiveis(usuario.getId(), usuario.getEmail()));
    }

}