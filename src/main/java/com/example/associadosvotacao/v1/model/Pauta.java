package com.example.associadosvotacao.v1.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pauta")
@Data
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@EqualsAndHashCode(exclude = {"processoInstrumento", "enquadramentos", "testemunhas"})
public class Pauta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assunto")
    private String assunto;

    @JsonIgnore
    @OneToMany(mappedBy = "pauta", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessaoVotacao> sessoes;

    @Column(name = "flg_ativo")
    private Boolean ativo;

    @Column(name = "dat_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private LocalDateTime dataCadastro;
}
