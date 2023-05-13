package com.example.associadosvotacao.v1.repository;

import com.example.associadosvotacao.v1.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
