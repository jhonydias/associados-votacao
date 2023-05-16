package com.example.associadosvotacao.exception;

public class SessaoFechadaException extends RuntimeException {
    public SessaoFechadaException() {
        super("Sessão de votação está fechada");
    }
}
