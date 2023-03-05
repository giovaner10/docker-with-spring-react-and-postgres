package br.com.cwi.racha.service.validator;

import br.com.cwi.racha.domain.Postagem;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.factories.PostagemFactory;
import br.com.cwi.racha.repository.PostagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuscarValidarPostagemServiceTest {

    @InjectMocks
    private BuscarValidarPostagemService validator;

    @Mock
    private PostagemRepository postagemRepository;


    Postagem postagem = null;
    @BeforeEach
    void setUp() {
        postagem = PostagemFactory.getPostagem();
    }


    @Test
    @DisplayName("Deve devolver postagem valida")
    void devePostagemValida() {
        when(postagemRepository.findById(postagem.getId())).thenReturn(Optional.of(postagem));

        Postagem postagemBuscada = validator.porId(postagem.getId());

        assertEquals(postagem, postagemBuscada);
    }

    @Test
    @DisplayName("deve lancar excessao ao buscar usuario disponivel")
    void deveLancarExcessaoAoNaoEcontrarUsuario() {

        doThrow(NegocioException.class).when(postagemRepository).findById(postagem.getId());

        assertThrows(NegocioException.class, () -> validator.porId(postagem.getId()));
    }

}