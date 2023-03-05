package br.com.cwi.racha.mapper;

import br.com.cwi.racha.controller.request.postagem.PostagemRequest;
import br.com.cwi.racha.controller.response.postagem.PostagemResponse;
import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.factories.PostagemFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PostagemMapperTest {


    PostagemRequest request = null;
    Postagem postagem = null;
    @BeforeEach
    void setUp() {
        request = PostagemFactory.getPostagemRequest();
        postagem = PostagemFactory.getPostagem();

    }

    @Test
    @DisplayName("Deve criar uma entidade valida")
    void toEntity() {

        Postagem postagemEntity = PostagemMapper.toEntity(request);

        assertEquals(request.getFoto(), postagemEntity.getFoto());
        assertEquals(request.getLegenda(), postagemEntity.getLegenda());
        assertEquals(request.isPrivada(), postagemEntity.isPrivada());

    }

    @Test
    @DisplayName("Deve retornar NullPointerException quando a request for nula")
    void toEntityPostagemNull() {

        assertThrows(NullPointerException.class, ()-> PostagemMapper.toEntity(null));
    }

    @Test
    @DisplayName("Deve criar uma response valida")
    void toResponse() {

        PostagemResponse comentarioResponse = PostagemMapper.toResponse(postagem);

        assertEquals(postagem.getId(), comentarioResponse.getId());
        assertEquals(postagem.getFoto(), comentarioResponse.getFoto());
        assertEquals(postagem.isPrivada(), comentarioResponse.isPrivada());
        assertEquals(postagem.getLegenda(), comentarioResponse.getLegenda());
        assertEquals(postagem.getUsuario().getFoto(), comentarioResponse.getFotoUsuario());
        assertEquals(postagem.getUsuario().getNomeCompleto(), comentarioResponse.getNomeUsuario());
        assertEquals(postagem.getUsuario().getId(), comentarioResponse.getIdUsuario());

    }


    @Test
    @DisplayName("Deve retornar NullPointerException quando a entity for nula")
    void toResponsePostagemNull() {

        assertThrows(NullPointerException.class, ()-> PostagemMapper.toResponse(null));
    }

}