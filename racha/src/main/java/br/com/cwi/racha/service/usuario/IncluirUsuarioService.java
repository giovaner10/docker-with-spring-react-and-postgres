package br.com.cwi.racha.service.usuario;

import br.com.cwi.racha.controller.request.usuario.UsuarioRequest;
import br.com.cwi.racha.controller.response.usuario.UsuarioResponse;
import br.com.cwi.racha.domain.Usuario;
import br.com.cwi.racha.repository.UsuarioRepository;
import br.com.cwi.racha.service.validator.ValidarEmailUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.com.cwi.racha.mapper.UsuarioMapper.toEntity;
import static br.com.cwi.racha.mapper.UsuarioMapper.toResponse;


@Service
public class IncluirUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ValidarEmailUsuarioService validarEmailUsuarioService;


    @Transactional
    public UsuarioResponse incluir(UsuarioRequest request) {

        validarEmailUsuarioService.validarEmail(request.getEmail());

        Usuario usuario = toEntity(request);
        usuario.setSenha(getSenhaCriptografada(request.getSenha()));
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);

        return toResponse(usuario);
    }

    private String getSenhaCriptografada(String senhaAberta) {
        return passwordEncoder.encode(senhaAberta);
    }

}
