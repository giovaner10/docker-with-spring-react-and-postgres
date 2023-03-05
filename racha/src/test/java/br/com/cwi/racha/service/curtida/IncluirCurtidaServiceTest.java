package br.com.cwi.racha.service.curtida;

import br.com.cwi.racha.domain.Curtida;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.CurtidaFactory;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncluirCurtidaServiceTest {

    @InjectMocks
    private IncluirCurtidaService validator;

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
    Curtida curtida = null;
    @BeforeEach
    void setUp() {
        usuario = UsuarioFactory.getUsuario();
        postagem = PostagemFactory.getPostagem();
        curtida = CurtidaFactory.getCurtida();
    }


    @Test
    @DisplayName("Deve criar curtida valida")
    void deveCriarCurtidaValida() {

        when(buscarValidarUsuarioService.porId(usuario.getId())).thenReturn(usuario);
        when(buscarValidarPostagemService.porId(postagem.getId())).thenReturn(postagem);

        validator.curtir(usuario.getId(), postagem.getId());

        verify(curtidaRepository).save(curtidaCaptor.capture());

        Curtida curtidaCaptorValue = curtidaCaptor.getValue();

        assertEquals(usuario.getId(), curtidaCaptorValue.getUsuario().getId());
        assertEquals(postagem.getId(), curtidaCaptorValue.getPostagem().getId());
    }

    @Test
    @DisplayName("nao deve criar  com usuario inexistente")
    void lacarExcessaoUsuarioExistente() {

        doThrow(NegocioException.class).when(buscarValidarUsuarioService).porId(usuario.getId());

        assertThrows(NegocioException.class, () -> validator.curtir(usuario.getId(), postagem.getId()));

        verify(curtidaRepository, never()).save(null);
    }

    @Test
    @DisplayName("nao deve criar com postagem inexistente")
    void lacarExcessaoPostagemExistente() {

        doThrow(NegocioException.class).when(buscarValidarPostagemService).porId(postagem.getId());

        assertThrows(NegocioException.class, () -> validator.curtir(usuario.getId(), postagem.getId()));

        verify(curtidaRepository, never()).save(null);
    }

    @Test
    @DisplayName("nao deve criar com postagem ja curtida pelo usuario")
    void lacarExcessaoPostagemJaCurtida() {

        when(buscarValidarPostagemService.porId(postagem.getId())).thenReturn(postagem);

        doThrow(NegocioException.class).when(verificarSeJaCurtiuValidator).validarCurtida(new ArrayList<>(), null);

        assertThrows(NegocioException.class, () -> validator.curtir(usuario.getId(), postagem.getId()));

        verify(curtidaRepository, never()).save(null);
    }

}