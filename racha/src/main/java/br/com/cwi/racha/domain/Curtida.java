package br.com.cwi.racha.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor
public class Curtida {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id", nullable=false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="postagem_id", nullable=false)
    private Postagem postagem;

}
