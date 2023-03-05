package br.com.cwi.racha.service.comentario;

import br.com.cwi.racha.controller.response.comentario.ComentarioResponse;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.PostagemFactory;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.cwi.racha.factories.ComentarioFactory.getComentario;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListarComentariosServiceTest {

    @InjectMocks
    private ListarComentariosService validator;

    @Mock
    private BuscarValidarPostagemService buscarValidarPostagemService;


    Postagem postagem = null;
    @BeforeEach
    void setUp() {
        postagem = PostagemFactory.getPostagem();
    }

    @Test
    @DisplayName("Deve econtrar o encontrar as postagens listadas")
    void deveEncontarAsListagensAdicionadas() {

        postagem.getComentarios().add(getComentario());
        postagem.getComentarios().add(getComentario());
        postagem.getComentarios().add(getComentario());

        when(buscarValidarPostagemService.porId(postagem.getId())).thenReturn(postagem);

        List<ComentarioResponse> list = validator.listar(postagem.getId());

        assertFalse(list.isEmpty());
        assertEquals(3, list.size());
    }

    @Test
    @DisplayName("Deve econtrar lista vazia quando nao tiver nada postado comentarios")
    void naoDeveEncontarAsListagen() {

        when(buscarValidarPostagemService.porId(postagem.getId())).thenReturn(postagem);

        List<ComentarioResponse> list = validator.listar(postagem.getId());

        assertTrue(list.isEmpty());
    }

    @Test
    @DisplayName("Nao deve econtrar o usuario")
    void deveLacarExcessaoAoTentarListaUsuarioInexistente() {

        doThrow(NegocioException.class).when(buscarValidarPostagemService).porId(postagem.getId());

        assertThrows(NegocioException.class, () -> validator.listar(postagem.getId()));
    }

}