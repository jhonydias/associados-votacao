package com.example.associadosvotacao.v1.repository;

import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.model.Voto;
import com.example.associadosvotacao.v1.model.enums.OpcaoVotoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    boolean existsByAssociadoAndSessaoVotacao(Associado associado, SessaoVotacao sessaoVotacao);
    int countBySessaoVotacao(SessaoVotacao sessaoVotacao);
    @Query("SELECT COUNT(v) FROM Voto v WHERE v.voto = :opcaoVotoEnum and v.sessaoVotacao.id =:idSessao")
    Long countByOpcaoVotoAndSessaoVotacao(OpcaoVotoEnum opcaoVotoEnum, Long idSessao);
}
