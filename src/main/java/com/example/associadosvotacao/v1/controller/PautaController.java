package com.example.associadosvotacao.v1.controller;

import com.example.associadosvotacao.config.SwaggerConfig;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefault;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultCreated;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultNoContent;
import com.example.associadosvotacao.v1.model.Pauta;
import com.example.associadosvotacao.v1.repository.PautaRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pautas")
@Api(value = "Pautas", tags = {SwaggerConfig.PAUTA_TAG})
public class PautaController {
    @Autowired
    private PautaRepository pautaRepository;

    @GetMapping
    @ApiOperation(value = "Lista todas as pautas")
    @ApiResponsesDefault
    public List<Pauta> listar() {
        return pautaRepository.findAll();
    }

    @ApiOperation(value = "Busca somente uma pauta")
    @ApiResponsesDefault
    @GetMapping("/{id}")
    public ResponseEntity<Pauta> buscarPorId(@PathVariable Long id) {
        Optional<Pauta> pauta = pautaRepository.findById(id);
        return pauta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra uma pauta nova")
    @ApiResponsesDefaultCreated
    public Pauta criar(@RequestBody Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @ApiOperation(value = "Edita uma pauta")
    @ApiResponsesDefaultCreated
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ResponseEntity<Pauta> atualizar(@PathVariable Long id, @RequestBody Pauta pauta) {
        if (!pautaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pauta.setId(id);
        pauta = pautaRepository.save(pauta);
        return ResponseEntity.ok(pauta);
    }
    @ApiOperation(value = "Edita uma pauta")
    @ApiResponsesDefaultNoContent
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!pautaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pautaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
