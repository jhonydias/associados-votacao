package com.example.associadosvotacao.v1.controller;

import com.example.associadosvotacao.config.SwaggerConfig;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefault;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultCreated;
import com.example.associadosvotacao.v1.annotation.ApiResponsesDefaultNoContent;
import com.example.associadosvotacao.v1.dtos.AssociadoDTO;
import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.model.Pauta;
import com.example.associadosvotacao.v1.repository.PautaRepository;
import com.example.associadosvotacao.v1.service.AssociadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/associados")
@Api(value = "Associados", tags = {SwaggerConfig.ASSOCIADO_TAG})
public class AssociadoController {
    @Autowired
    private AssociadoService associadoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Cadastra um novo associado")
    @ApiResponsesDefaultCreated
    public ResponseEntity<Associado> criarAssociado(@RequestBody AssociadoDTO associado) {
        Associado associadoCriado = associadoService.criarAssociado(associado.convertToModel());
        return ResponseEntity.status(HttpStatus.CREATED).body(associadoCriado);
    }
}
