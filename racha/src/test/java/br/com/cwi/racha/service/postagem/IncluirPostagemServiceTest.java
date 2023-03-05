package br.com.cwi.racha.service.postagem;

import br.com.cwi.racha.controller.request.postagem.PostagemRequest;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.PostagemFactory;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.repository.PostagemRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncluirPostagemServiceTest {


    @InjectMocks
    private IncluirPostagemService validator;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    @Mock
    private PostagemRepository postagemRepository;

    @Captor
    private ArgumentCaptor<Postagem> postagemCaptor;


    Usuario usuario = null;
    PostagemRequest postagem = null;
    @BeforeEach
    void setUp() {
        usuario = UsuarioFactory.getUsuario();
        postagem = PostagemFactory.getPostagemRequest();
    }


    @Test
    @DisplayName("Deve criar postagem valida")
    void deveCriarPostagemValida() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);

        validator.postar(usuario.getId(), postagem);

        verify(postagemRepository).save(postagemCaptor.capture());

        Postagem postagemCaptor = this.postagemCaptor.getValue();

        assertEquals(usuario.getId(), postagemCaptor.getUsuario().getId());
        assertEquals(postagem.getLegenda(), postagemCaptor.getLegenda());

    }

    @Test
    @DisplayName("nao deve criar  com usuario inexistente")
    void lacarExcessaoUsuarioExistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.postar(usuario.getId(), postagem));

        verify(postagemRepository, never()).save(null);
    }

}