package br.com.cwi.racha.service.postagem;

import br.com.cwi.racha.controller.response.postagem.PostagemResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.cwi.racha.factories.PostagemFactory.getPostagem;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarPostagensServiceTest {

    @InjectMocks
    private ListarPostagensService validator;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;


    Usuario usuario = null;
    @BeforeEach
    void setUp() {
        usuario = UsuarioFactory.getUsuario();
    }


    @Test
    @DisplayName("Deve  encontrar as postagens listadas")
    void deveEncontarAsPostagensAdicionadas() {

        usuario.getPostagens().add(getPostagem());
        usuario.getPostagens().add(getPostagem());
        usuario.getPostagens().add(getPostagem());

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        List<PostagemResponse> list = validator.listarMinhas(usuario.getId());

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Deve econtrar lista vazia quando o usuario nao tiver nada postado")
    void naoDeveEncontarAsListagen() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        List<PostagemResponse> list = validator.listarMinhas(usuario.getId());

        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Nao deve econtrar o usuario")
    void deveLacarExcessaoAoTentarListaUsuarioInexistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.listarMinhas(usuario.getId()));
    }

    @Test
    @DisplayName("Deve econtrar as postagens da rede")
    void deveEncontarAsListagensAdicionadas() {

        usuario.getPostagens().add(getPostagem());
        usuario.getPostagens().add(getPostagem());
        usuario.getPostagens().add(getPostagem());

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        List<PostagemResponse> list = validator.listarRede(usuario.getId());

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Nao deve econtrar o usuario")
    void deveLacarExcessaoAoTentarListaUsuarioInexistenteRede() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.listarMinhas(usuario.getId()));
    }

}