package br.com.cwi.racha.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Postagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false,  length = 512)
    private String legenda;

    @Column(nullable = false)
    private LocalDateTime dataPostagem;

    @Column(nullable = false)
    private boolean privada;

    @Column(nullable = false, length = 512)
    private String foto;

    @OneToMany(mappedBy = "postagem")
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "postagem")
    private List<Curtida> curtidas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="Usuario_id", nullable=false)
    private Usuario usuario;

}
