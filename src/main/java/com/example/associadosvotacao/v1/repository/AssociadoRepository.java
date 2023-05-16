package com.example.associadosvotacao.v1.repository;

import com.example.associadosvotacao.v1.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    Associado findByLogin(String login);

    Associado findByCpf(String cpf);
}
