package com.example.associadosvotacao.v1.controller;

import com.example.associadosvotacao.config.SwaggerConfig;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefault;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultCreated;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultNoContent;
import com.example.associadosvotacao.v1.dtos.SessaoVotacaoDTO;
import com.example.associadosvotacao.v1.dtos.VotoDTO;
import com.example.associadosvotacao.v1.model.Pauta;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.model.Voto;
import com.example.associadosvotacao.v1.repository.PautaRepository;
import com.example.associadosvotacao.v1.repository.SessaoVotacaoRepository;
import com.example.associadosvotacao.v1.repository.VotoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessao-votacao")
@Api(value = "Pautas", tags = {SwaggerConfig.SESSAO_VOTACAO_TAG})
public class SessaoVotacaoController {
    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private SessaoVotacaoRepository sessaoVotacaoRepository;
    @PostMapping("/sessoes-votacao")
    @ApiOperation(value = "Cadastra uma Sessão")
    @ApiResponsesDefaultCreated
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<SessaoVotacao> abrirSessaoVotacao(@RequestBody SessaoVotacaoDTO sessaoVotacaoDTO) {
        // buscar a pauta no banco de dados
        Optional<Pauta> pauta = pautaRepository.findById(sessaoVotacaoDTO.getPauta().getId());
        if (!pauta.isPresent()){
            new Exception("Pauta não encontrada com o id: " + sessaoVotacaoDTO.getPauta().getId());
        }

        return ResponseEntity.ok(sessaoVotacaoRepository.save(sessaoVotacaoDTO.convertToModel()));
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


    @ApiOperation(value = "Operação de Voto")
    @ApiResponsesDefaultCreated
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/votar")
    public ResponseEntity<String> votar(@RequestBody VotoDTO voto) {
        // Lógica para verificar se a sessão de votação está aberta e se o associado pode votar

        // Se a sessão estiver aberta e o associado puder votar, registra o voto no banco de dados
        votoRepository.save(voto.convertToModel());

        return ResponseEntity.ok("Voto registrado com sucesso!");
    }
}
