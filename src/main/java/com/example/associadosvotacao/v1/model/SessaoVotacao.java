package com.example.associadosvotacao.v1.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="sessao_votacao")
@Data
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "votos")
    private Integer totalVotos;

    @Column(name = "descricao")
    private String descricao;

    @JsonIgnore
    @OneToMany(mappedBy = "sessaoVotacao", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

    @NotNull
    private Long associadoId;

    @Column(name = "inicio")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime inicio;

    @Column(name = "termino")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime termino;

    public SessaoVotacao() {
    }
}
