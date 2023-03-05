package br.com.cwi.racha.service.amizade;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.BuscarValidarUsuarioService;
import br.com.cwi.racha.validator.VerificarSeSaoAmigosValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DesfazerAmizadeService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BuscarValidarUsuarioService buscarValidarUsuarioService;

    @Autowired
    private VerificarSeSaoAmigosValidator seSaoAmigosValidator;


    @Transactional
    public void desfazer(Long idUsuario, Long idSolicitacaoUsuario) {

        if(idUsuario == idSolicitacaoUsuario){
            throw new NegocioException(HttpStatus.BAD_REQUEST, "Os usuario são iguais");
        }

        Usuario usuario = buscarValidarUsuarioService.porId(idUsuario);
        Usuario solicitante = buscarValidarUsuarioService.porIdSolicitante(idSolicitacaoUsuario);

        seSaoAmigosValidator.validarAmizadeInexistente(usuario.getAmigos(), solicitante);

        usuario.desfazerAmizade(solicitante);

        usuarioRepository.save(usuario);
        usuarioRepository.save(solicitante);
    }

}
