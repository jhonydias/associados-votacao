package com.example.associadosvotacao.v1.controller;

import com.example.associadosvotacao.config.SwaggerConfig;
import com.example.associadosvotacao.exception.PautaNotFoundException;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefault;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultCreated;
import com.example.associadosvotacao.v1.dtos.SessaoVotacaoDTO;
import com.example.associadosvotacao.v1.model.Pauta;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.repository.PautaRepository;
import com.example.associadosvotacao.v1.repository.SessaoVotacaoRepository;
import com.example.associadosvotacao.v1.service.SessaoVotacaoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessao-votacao")
@Api(value = "Sessão de Votação", tags = {SwaggerConfig.SESSAO_VOTACAO_TAG})
public class SessaoVotacaoController {
    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private SessaoVotacaoService sessaoVotacaoService;

    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @PostMapping("/sessoes-votacao")
    @ApiOperation(value = "Cadastra uma Sessão")
    @ApiResponsesDefaultCreated
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestBody SessaoVotacaoDTO sessaoVotacaoDTO) {
        Optional<Pauta> pauta = pautaRepository.findById(sessaoVotacaoDTO.getPauta().getId());
        if (!pauta.isPresent()){
            new PautaNotFoundException(sessaoVotacaoDTO.getPauta().getId());
        }

        return ResponseEntity.ok(sessaoVotacaoService.save(sessaoVotacaoDTO.convertToModel()));
    }

    @ApiOperation(value = "Busca uma sessão")
    @ApiResponsesDefault
    @GetMapping("/{id}")
    public ResponseEntity<SessaoVotacao> buscarPorId(@PathVariable Long id) {
        Optional<SessaoVotacao> sessaoVotacao = sessaoVotacaoRepository.findById(id);
        return sessaoVotacao.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as sessões")
    @ApiResponsesDefault
    public List<SessaoVotacao> listar() {
        return sessaoVotacaoRepository.findAll();
    }

}
