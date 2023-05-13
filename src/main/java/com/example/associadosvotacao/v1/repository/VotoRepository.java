package com.example.associadosvotacao.v1.repository;

import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
