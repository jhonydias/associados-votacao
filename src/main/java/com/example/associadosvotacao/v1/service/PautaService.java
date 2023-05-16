package com.example.associadosvotacao.v1.service;

import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PautaService {
    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado criarAssociado(Associado associado) {
        if (associadoRepository.findByLogin(associado.getLogin()) != null) {
            throw new RuntimeException("Login já cadastrado");
        }
        if (associadoRepository.findByCpf(associado.getCpf()) != null) {
            throw new RuntimeException("CPF já cadastrado");
        }
        Associado associadoCriado = associadoRepository.save(associado);
        return associadoCriado;
    }
}
