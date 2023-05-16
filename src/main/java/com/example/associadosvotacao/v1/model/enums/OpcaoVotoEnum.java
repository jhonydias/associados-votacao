package com.example.associadosvotacao.v1.model.enums;

public enum OpcaoVotoEnum {

    SIM(1L, "Sim"),

    NAO(2L, "Não"),
    ABSTENCAO(3L, "Abstenção");

    public Long id;
    public String descricao;


    OpcaoVotoEnum(Long id, String descricao) {
        this.id= id;
        this.descricao = descricao;
    }

}
