package br.com.cwi.racha.service.validator;

import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.excpetions.NegocioException;
import br.com.cwi.racha.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class BuscarValidarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long idUsuario) {

        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new NegocioException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

    public Usuario porIdSolicitante(Long idUsuario) {

        return usuarioRepository.findById(idUsuario).orElseThrow(() -> new NegocioException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));
    }

}
