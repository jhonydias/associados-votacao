package com.example.associadosvotacao.v1.service;

import com.example.associadosvotacao.exception.CpfInvalidoException;
import com.example.associadosvotacao.util.ValidaCpfUtil;
import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {
    @Autowired
    private AssociadoRepository associadoRepository;

    public Associado criarAssociado(Associado associado) {
        if (associadoRepository.findByLogin(associado.getLogin()) != null) {
            throw new RuntimeException("Login já cadastrado");
        }
        if (associadoRepository.findByCpf(associado.getCpf()) != null) {
            throw new RuntimeException("CPF já cadastrado");
        }
        if (!ValidaCpfUtil.validaCPF(associado.getCpf())) {
            throw new CpfInvalidoException(associado.getId());
        }

        return associadoRepository.save(associado);
    }
}
