package com.example.associadosvotacao.v1.controller;

import com.example.associadosvotacao.config.SwaggerConfig;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefault;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultCreated;
import com.example.associadosvotacao.v1.dtos.VotoDTO;
import com.example.associadosvotacao.v1.model.Voto;
import com.example.associadosvotacao.v1.response.ResultadoVotacaoResponse;
import com.example.associadosvotacao.v1.service.VotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/votos")
@Api(value = "Votos", tags = {SwaggerConfig.VOTO_TAG})
public class VotoController {

    @Autowired
    private VotoService votoService;

    @GetMapping
    @ApiOperation(value = "Lista todas as sessões")
    @ApiResponsesDefault
    public ResponseEntity<Page<Voto>> listarVotosComPaginacao(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(votoService.listarVotos(pageable));
    }
    @GetMapping("/resultado-votacao/{idSessao}")
    @ApiOperation(value = "Conta todos os votos")
    @ApiResponsesDefault
    public ResponseEntity<ResultadoVotacaoResponse> listarVotos(@RequestParam Long idSessao) {
        return ResponseEntity.ok(votoService.countVotos(idSessao));
    }


    @ApiOperation(value = "Operação de Voto")
    @ApiResponsesDefaultCreated
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/votar")
    public ResponseEntity<String> votar(@RequestBody VotoDTO voto) {
        votoService.save(voto.convertToModel());
        return ResponseEntity.ok("Voto registrado com sucesso!");

    }
}
