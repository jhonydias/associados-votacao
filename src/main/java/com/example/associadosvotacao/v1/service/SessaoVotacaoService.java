package com.example.associadosvotacao.v1.service;

import com.example.associadosvotacao.exception.SessaoVotacaoInvalidaException;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.repository.SessaoVotacaoRepository;
import com.example.associadosvotacao.v1.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SessaoVotacaoService {
    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @Autowired
    private VotoRepository votoRepository;

    public SessaoVotacao save(SessaoVotacao sessaoVotacao) {
        LocalDateTime agora = LocalDateTime.now();

        if (sessaoVotacao.getInicio().equals(null) && sessaoVotacao.getTermino().equals(null)){
            sessaoVotacao.setInicio(agora);
            sessaoVotacao.setTermino(agora.plusSeconds(60));
        }
        if (sessaoVotacao.getInicio().equals(null) && !sessaoVotacao.getTermino().equals(null)){
            sessaoVotacao.setInicio(agora);
        }
        if (sessaoVotacao.getInicio().isAfter(agora) || sessaoVotacao.getTermino().isAfter(agora)) {
            throw new SessaoVotacaoInvalidaException("Início/Termino antes da data/hora/minuto do momento atual");
        }
        if (sessaoVotacao.getInicio().isBefore(sessaoVotacao.getTermino())) {
            throw new SessaoVotacaoInvalidaException("Início depois do fim");
        }
        if (sessaoVotacao.getTermino().isAfter(sessaoVotacao.getInicio())) {
            throw new SessaoVotacaoInvalidaException("Termino antes do início");
        }
        return sessaoVotacaoRepository.save(sessaoVotacao);
    }

    public void countVotos(SessaoVotacao sessaoVotacao) {
        int totalVotos = votoRepository.countBySessaoVotacao(sessaoVotacao);
        Optional<SessaoVotacao> sessao = sessaoVotacaoRepository.findById(sessaoVotacao.getId());
        sessao.get().setTotalVotos(totalVotos);
        sessaoVotacaoRepository.save(sessao.get());
    }

}
