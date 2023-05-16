package com.example.associadosvotacao.exception;

public class PautaNotFoundException extends RuntimeException {
    public PautaNotFoundException(Long id) {
        super("Pauta não encontrada com o id: " + id);
    }
}
