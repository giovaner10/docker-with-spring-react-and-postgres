package br.com.cwi.racha.service.curtida;

import br.com.cwi.racha.domain.Curtida;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.PostagemFactory;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.repository.CurtidaRepository;
import br.com.cwi.racha.service.validator.BuscarValidarPostagemService;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import br.com.cwi.racha.validator.VerificarSeJaCurtiuValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoverCurtidaServiceTest {


    @InjectMocks
    private RemoverCurtidaService validator;

    @Mock
    private CurtidaRepository curtidaRepository;

    @Mock
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    @Mock
    private BuscarValidarPostagemService buscarValidarPostagemService;

    @Mock
    private VerificarSeJaCurtiuValidator verificarSeJaCurtiuValidator;

    @Captor
    private ArgumentCaptor<Curtida> curtidaCaptor;


    Usuario usuario = null;
    Postagem postagem = null;
    Curtida curtida = new Curtida();

    @BeforeEach
    void setUp() {
        usuario = UsuarioFactory.getUsuario();
        postagem = PostagemFactory.getPostagem();
        curtida.setUsuario(usuario);
        curtida.setPostagem(postagem);
        postagem.getCurtidas().add(curtida);
    }


    @Test
    @DisplayName("nao deve remover  com usuario inexistente")
    void lacarExcessaoUsuarioExistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.remover(usuario.getId(), postagem.getId()));

        verify(curtidaRepository, never()).save(null);
    }

    @Test
    @DisplayName("nao deve remover com postagem inexistente")
    void lacarExcessaoPostagemExistente() {

        doThrow(NegocioException.class).when(buscarValidarPostagemService).porId(postagem.getId());

        assertThrows(NegocioException.class, () -> validator.remover(usuario.getId(), postagem.getId()));

        verify(curtidaRepository, never()).save(null);
    }

    @Test
    @DisplayName("nao deve retirar com postagem nao curtida pelo usuario")
    void lacarExcessaoPostagemNaoCurtida() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarPostagemService.porId(postagem.getId())).thenReturn(postagem);

        doThrow(NegocioException.class).when(verificarSeJaCurtiuValidator).invalidarCurtida(Arrays.asList(usuario), usuario);

        assertThrows(NegocioException.class, () -> validator.remover(usuario.getId(), postagem.getId()));

        verify(curtidaRepository, never()).deleteAll(null);
    }

}