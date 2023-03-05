package br.com.cwi.racha.service.comentario;

import br.com.cwi.racha.controller.request.comentario.ComentarioRequest;
import br.com.cwi.racha.domain.Comentario;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.ComentarioFactory;
import br.com.cwi.racha.factories.PostagemFactory;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.repository.ComentarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncluirComentarioServiceTest {


    @InjectMocks
    private IncluirComentarioService validator;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    @Mock
    private BuscarValidarPostagemService buscarValidarPostagemService;

    @Captor
    private ArgumentCaptor<Comentario> comentarioCaptor;


    Usuario usuario = null;
    Postagem postagem = null;
    ComentarioRequest comentario = null;
    @BeforeEach
    void setUp() {
        usuario = UsuarioFactory.getUsuario();
        postagem = PostagemFactory.getPostagem();
        comentario = ComentarioFactory.getComentarioRequest();
    }


    @Test
    @DisplayName("Deve criar postagem valida")
    void deveCriarPostagemValida() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        when(buscarValidarPostagemService.porId(postagem.getId())).thenReturn(postagem);

        validator.comentar(usuario.getId(), postagem.getId(), comentario);

        verify(comentarioRepository).save(comentarioCaptor.capture());

        Comentario comentarioSaved = comentarioCaptor.getValue();

        assertEquals(usuario.getId(), comentarioSaved.getUsuario().getId());
        assertEquals(postagem.getId(), comentarioSaved.getPostagem().getId());

    }

    @Test
    @DisplayName("nao deve criar  com usuario inexistente")
    void lacarExcessaoUsuarioExistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () ->  validator.comentar(usuario.getId(), postagem.getId(), comentario));

        verify(comentarioRepository, never()).save(null);
    }

    @Test
    @DisplayName("nao deve criar com postagem inexistente")
    void lacarExcessaoPostagemExistente() {

        doThrow(NegocioException.class).when(buscarValidarPostagemService).porId(postagem.getId());

        assertThrows(NegocioException.class, () ->  validator.comentar(usuario.getId(), postagem.getId(), comentario));

        verify(comentarioRepository, never()).save(null);
    }

}