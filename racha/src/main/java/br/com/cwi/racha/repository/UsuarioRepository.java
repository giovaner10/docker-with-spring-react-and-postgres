package br.com.cwi.racha.repository;

import br.com.cwi.racha.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);


    @Query(value = "SELECT t FROM Usuario t WHERE t.id NOT IN(:idUser) AND (LOWER(t.nomeCompleto) LIKE %:nomeCompleto% OR LOWER(t.email) LIKE %:email%)")
    List<Usuario> findUsuariosDisponiveis(Long idUser, String nomeCompleto, String email);


}
