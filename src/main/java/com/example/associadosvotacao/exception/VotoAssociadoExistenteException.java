package com.example.associadosvotacao.exception;

public class VotoAssociadoExistenteException extends RuntimeException {
    private Long associadoId;
    private Long sessaoVotacaoId;
    public VotoAssociadoExistenteException(Long idAssociado, Long idSessao) {
        super("Associado de Id: " +idAssociado+
                " já votou na sessão de Id: "+ idSessao);
        this.associadoId = idAssociado;
        this.sessaoVotacaoId = idSessao;
    }
    public Long getAssociadoId() {
        return associadoId;
    }

    public Long getSessaoVotacaoId() {
        return sessaoVotacaoId;
    }
}
