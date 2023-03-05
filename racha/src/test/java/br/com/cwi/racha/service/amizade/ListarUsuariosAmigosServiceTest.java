package br.com.cwi.racha.service.amizade;

import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.factories.UsuarioSetListFactory;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarUsuariosAmigosServiceTest {

    @InjectMocks
    private ListarUsuariosAmigosService validator;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;


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
    @DisplayName("Deve econtrar o amigo solicitado")
    void deveEncontrarOAmigo() {

        String nome = "FLoRzINha";
        solicitante.setNomeCompleto(nome);

        usuariosList.add(solicitante);
        usuario.setAmigos(usuariosList);

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        List<UsuarioResponse> usuarioResponses = validator.listar(usuario.getId(), nome);

        assertFalse(usuarioResponses.isEmpty());
        assertTrue(usuarioResponses.get(usuarioResponses.size() -1).getId() == solicitante.getId());
    }

    @Test
    @DisplayName("Nao deve econtrar o amigo solicitado, pois nao existe")
    void naoDeveEncontrarAmigoInexistente() {

        String nome = "FLoRzINha";
        solicitante.setNomeCompleto(nome);
        usuario.setAmigos(usuariosList);

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        List<UsuarioResponse> usuarioResponses = validator.listar(usuario.getId(), nome);

        assertTrue(usuarioResponses.isEmpty());
    }

    @Test
    @DisplayName("Nao deve econtrar o usuario")
    void solicitarEncontraAmigoComUsuarioInvalido() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.listar(usuario.getId(), usuario.getEmail()));
    }

}