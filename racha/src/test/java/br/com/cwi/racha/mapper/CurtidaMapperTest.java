package br.com.cwi.racha.mapper;

import br.com.cwi.racha.controller.response.curtida.CurtidaResponse;
import br.com.cwi.racha.domain.Curtida;
import br.com.cwi.racha.factories.CurtidaFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class CurtidaMapperTest {

    Curtida curtida = null;
    @BeforeEach
    void setUp() {
        curtida = CurtidaFactory.getCurtida();
    }

    @Test
    @DisplayName("Deve criar uma response valida")
    void toResponse() {

        CurtidaResponse curtidaResponse = CurtidaMapper.toResponse(curtida);

        assertEquals(curtida.getId(), curtidaResponse.getId());
        assertEquals(curtida.getUsuario().getFoto(), curtidaResponse.getFotoUsuario());
        assertEquals(curtida.getUsuario().getNomeCompleto(), curtidaResponse.getNomeUsuario());
        assertEquals(curtida.getUsuario().getId(), curtidaResponse.getIdUsuario());
        assertEquals(curtida.getPostagem().getId(), curtidaResponse.getIdPostagem());

    }

    @Test
    @DisplayName("Deve retornar NullPointerException quando a entity for nula")
    void toResponseCurtidaNull() {

        assertThrows(NullPointerException.class, ()-> CurtidaMapper.toResponse(null));
    }

}