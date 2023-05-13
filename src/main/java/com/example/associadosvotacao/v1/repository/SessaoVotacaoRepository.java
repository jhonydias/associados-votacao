package com.example.associadosvotacao.v1.repository;

import com.example.associadosvotacao.v1.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {

}
