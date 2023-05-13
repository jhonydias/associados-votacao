package com.example.associadosvotacao.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "associado")
@Data
public class Associado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="login")
    private String login;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha")
    @ApiModelProperty(hidden = true)
    private String senha;

    @Column(name="nome")
    private String nome;

    @Column(name="cpf")
    private String cpf;

}
