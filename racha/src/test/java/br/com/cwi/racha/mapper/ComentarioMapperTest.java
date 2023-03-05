package br.com.cwi.racha.mapper;

import br.com.cwi.racha.controller.request.comentario.ComentarioRequest;
import br.com.cwi.racha.controller.response.comentario.ComentarioResponse;
import br.com.cwi.racha.domain.Comentario;
import br.com.cwi.racha.factories.ComentarioFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ComentarioMapperTest {

    ComentarioRequest request = null;
    Comentario comentario = null;
    @BeforeEach
    void setUp() {
        request = ComentarioFactory.getComentarioRequest();
        comentario = ComentarioFactory.getComentario();
    }

    @Test
    @DisplayName("Deve criar uma entidade valida")
    void toEntity() {

        Comentario comentarioEntity = ComentarioMapper.toEntity(request);

        assertEquals(request.getComentario(), comentarioEntity.getComentario());
    }

    @Test
    @DisplayName("Deve retornar NullPointerException quando a request for nula")
    void toEntityComentarioNull() {

        assertThrows(NullPointerException.class, ()-> ComentarioMapper.toEntity(null));
    }

    @Test
    @DisplayName("Deve criar uma response valida")
    void toResponse() {

        ComentarioResponse comentarioResponse = ComentarioMapper.toResponse(comentario);

        assertEquals(comentario.getComentario(), comentarioResponse.getComentario());
        assertEquals(comentario.getId(), comentarioResponse.getId());
        assertEquals(comentario.getUsuario().getFoto(), comentarioResponse.getFotoUsuario());
        assertEquals(comentario.getUsuario().getNomeCompleto(), comentarioResponse.getNomeUsuario());
        assertEquals(comentario.getUsuario().getId(), comentarioResponse.getIdUsuario());
        assertEquals(comentario.getPostagem().getId(), comentarioResponse.getIdPostagem());
    }

    @Test
    @DisplayName("Deve retornar NullPointerException quando a entity for nula")
    void toResponseComentarioNull() {

        assertThrows(NullPointerException.class, ()-> ComentarioMapper.toResponse(null));
    }

}