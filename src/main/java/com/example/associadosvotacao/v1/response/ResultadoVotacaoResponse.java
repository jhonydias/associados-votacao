package com.example.associadosvotacao.v1.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResultadoVotacaoResponse {
    private Long simVotes;
    private Long naoVotes;

    public ResultadoVotacaoResponse(Long simVotes, Long naoVotes) {
        this.simVotes = simVotes;
        this.naoVotes = naoVotes;
    }

}
