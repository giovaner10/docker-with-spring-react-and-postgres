package br.com.cwi.racha.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(length = 50)
    private String apelido;

    @Column(nullable = false)
    private LocalDate dataNasimento;

    @Column(nullable = false, length = 128)
    private String senha;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false, length = 512)
    private String foto;

    @OneToMany(mappedBy = "usuario")
    private List<Postagem> postagens = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="amizade",
            joinColumns={@JoinColumn(name="usuario_id")},
            inverseJoinColumns={@JoinColumn(name="amigo_id")})
    private Set<Usuario> amigos = new HashSet<>();


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="solicitacoes",
            joinColumns={@JoinColumn(name="usuario_id")},
            inverseJoinColumns={@JoinColumn(name="usuario_solicitacao_id")})
    private Set<Usuario> solicitacoes = new HashSet<>();


   public void adicionarSolicitacao(Usuario solicitante){
       solicitacoes.add(solicitante);
   }

    public void adicionarAmizade(Usuario solicitante){
        amigos.add(solicitante);
        solicitante.getAmigos().add(this);
        solicitacoes.remove(solicitante);
    }

    public void desfazerAmizade(Usuario solicitante){
        amigos.remove(solicitante);
        solicitante.getAmigos().remove(this);
    }

}
