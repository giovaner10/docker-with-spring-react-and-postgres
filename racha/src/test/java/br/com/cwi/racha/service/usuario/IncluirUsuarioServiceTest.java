package br.com.cwi.racha.service.usuario;

import br.com.cwi.racha.controller.request.usuario.UsuarioRequest;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.UsuarioFactory;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.ValidarEmailUsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncluirUsuarioServiceTest {

    @InjectMocks
    private IncluirUsuarioService validator;

    @Mock
    private ValidarEmailUsuarioService validarEmailUsuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<Usuario> usuarioCaptor;


    UsuarioRequest request = null;
    @BeforeEach
    void setUp() {
        request = UsuarioFactory.getUsuarioRequest();
    }


    @Test
    @DisplayName("Deve criar usuario valido")
    void deveDevolverUsuarioValido() {

        when(passwordEncoder.encode(request.getSenha())).thenReturn("senha encriptadata");

        validator.incluir(request);

        verify(usuarioRepository).save(usuarioCaptor.capture());

        Usuario usuario = usuarioCaptor.getValue();

        verify(validarEmailUsuarioService).validarEmail(request.getEmail());

        assertTrue(usuario.isAtivo());

        assertEquals("senha encriptadata", usuario.getSenha());
        assertEquals(request.getEmail(), usuario.getEmail());
        assertEquals(request.getNomeCompleto(), usuario.getNomeCompleto());
        assertEquals(request.getFoto(), usuario.getFoto());
        assertEquals(request.getApelido(), usuario.getApelido());
        assertEquals(request.getDataNasimento(), usuario.getDataNasimento());
    }

    @Test
    @DisplayName("nao deve criar usario com email existente")
    void lacarExcessaoEmailExistente() {

        doThrow(NegocioException.class).when(validarEmailUsuarioService).validarEmail(request.getEmail());

        assertThrows(NegocioException.class, () -> validator.incluir(request));

        verify(usuarioRepository, never()).save(null);
        verify(passwordEncoder, never()).encode(null);
        verify(validarEmailUsuarioService).validarEmail(request.getEmail());
    }

}