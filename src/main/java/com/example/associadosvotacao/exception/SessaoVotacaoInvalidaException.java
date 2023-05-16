package com.example.associadosvotacao.exception;

public class SessaoVotacaoInvalidaException extends RuntimeException {
    public SessaoVotacaoInvalidaException(String msg) {
        super(msg);
    }
}
