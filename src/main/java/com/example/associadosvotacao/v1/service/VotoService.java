package com.example.associadosvotacao.v1.service;

import com.example.associadosvotacao.exception.CpfInvalidoException;
import com.example.associadosvotacao.exception.SessaoFechadaException;
import com.example.associadosvotacao.exception.VotoAssociadoExistenteException;
import com.example.associadosvotacao.util.ValidaCpfUtil;
import com.example.associadosvotacao.v1.model.Voto;
import com.example.associadosvotacao.v1.model.enums.OpcaoVotoEnum;
import com.example.associadosvotacao.v1.repository.VotoRepository;
import com.example.associadosvotacao.v1.response.ResultadoVotacaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService {
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    public Voto save(Voto voto) {
        LocalDateTime agora = LocalDateTime.now();
        Boolean sessaoAberta = agora.isAfter(voto.getSessaoVotacao().getInicio())
                && agora.isBefore(voto.getSessaoVotacao().getTermino());
        if (!sessaoAberta) {
            throw new SessaoFechadaException();
        }
        if (!ValidaCpfUtil.validaCPF(voto.getAssociado().getCpf())) {
            throw new CpfInvalidoException(voto.getAssociado().getId());
        }
        if (votoRepository.existsByAssociadoAndSessaoVotacao(voto.getAssociado(), voto.getSessaoVotacao())) {
            throw new VotoAssociadoExistenteException(voto.getAssociado().getId(), voto.getSessaoVotacao().getId());
        }
        Voto savedVoto = votoRepository.save(voto);
        sessaoVotacaoService.countVotos(voto.getSessaoVotacao());
        return savedVoto;
    }
    public ResultadoVotacaoResponse countVotos(Long idSessao) {
        Long simVotes = votoRepository.countByOpcaoVotoAndSessaoVotacao(OpcaoVotoEnum.SIM, idSessao);
        Long naoVotes = votoRepository.countByOpcaoVotoAndSessaoVotacao(OpcaoVotoEnum.NAO, idSessao);

        return new ResultadoVotacaoResponse(simVotes, naoVotes);
    }

    public Page<Voto> listarVotos(Pageable pageable) {
        return votoRepository.findAll(pageable);
    }
}
