package com.example.associadosvotacao.v1.model;

import com.example.associadosvotacao.v1.model.enums.OpcaoVotoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "votos")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sessao_votacao")
    private SessaoVotacao sessaoVotacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associado_id")
    private Associado associado;

    @Column(name = "data_hora")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime data;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "opcao_voto")
    private OpcaoVotoEnum voto;

}
