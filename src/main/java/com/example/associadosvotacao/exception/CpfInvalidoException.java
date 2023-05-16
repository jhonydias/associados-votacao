package com.example.associadosvotacao.exception;

public class CpfInvalidoException extends RuntimeException {
    public CpfInvalidoException(Long id) {
        super("CPF inválido do Associado com o id: "+ id);
    }
}
