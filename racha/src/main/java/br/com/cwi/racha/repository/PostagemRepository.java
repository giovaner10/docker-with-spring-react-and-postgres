package br.com.cwi.racha.repository;

import br.com.cwi.racha.domain.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {


}
