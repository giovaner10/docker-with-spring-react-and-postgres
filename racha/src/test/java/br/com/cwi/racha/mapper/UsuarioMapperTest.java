package br.com.cwi.racha.mapper;

import br.com.cwi.racha.controller.request.usuario.UsuarioRequest;
import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.factories.UsuarioFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsuarioMapperTest {

    UsuarioRequest request = null;
    Usuario usuario = null;
    @BeforeEach
    void setUp() {
        request = UsuarioFactory.getUsuarioRequest();
        usuario = UsuarioFactory.getUsuario();

    }


    @Test
    @DisplayName("Deve criar uma entidade valida")
    void toEntity() {

        Usuario usuarioEntity = UsuarioMapper.toEntity(request);

        assertEquals(request.getApelido(), usuarioEntity.getApelido());
        assertEquals(request.getFoto(), usuarioEntity.getFoto());
        assertEquals(request.getEmail(), usuarioEntity.getEmail());
        assertEquals(request.getDataNasimento(), usuarioEntity.getDataNasimento());

    }

    @Test
    @DisplayName("Deve retornar NullPointerException quando a request for nula")
    void toEntityUsuarioNull() {

        assertThrows(NullPointerException.class, ()-> UsuarioMapper.toEntity(null));
    }

    @Test
    @DisplayName("Deve criar uma response valida")
    void toResponse() {

        UsuarioResponse usuarioResponse = UsuarioMapper.toResponse(usuario);

        assertEquals(usuario.getApelido(), usuarioResponse.getApelido());
        assertEquals(usuario.getFoto(), usuarioResponse.getFoto());
        assertEquals(usuario.getEmail(), usuarioResponse.getEmail());
        assertEquals(usuario.getApelido(), usuarioResponse.getApelido());
    }

    @Test
    @DisplayName("Deve retornar NullPointerException quando a entity for nula")
    void toResponseUsuarioNull() {

        assertThrows(NullPointerException.class, ()-> UsuarioMapper.toResponse(null));
    }

}