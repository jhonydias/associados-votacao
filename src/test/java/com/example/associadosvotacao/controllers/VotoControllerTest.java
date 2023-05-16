package com.example.associadosvotacao.controllers;

import com.example.associadosvotacao.v1.controller.VotoController;
import com.example.associadosvotacao.v1.dtos.VotoDTO;
import com.example.associadosvotacao.v1.model.Associado;
import com.example.associadosvotacao.v1.model.SessaoVotacao;
import com.example.associadosvotacao.v1.model.Voto;
import com.example.associadosvotacao.v1.model.enums.OpcaoVotoEnum;
import com.example.associadosvotacao.v1.response.ResultadoVotacaoResponse;
import com.example.associadosvotacao.v1.service.VotoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@SpringBootTest
class VotoControllerTest {

    @Mock
    private VotoService votoService;
    @InjectMocks
    private VotoController votoController;

    @Test
    void listarVotosComPaginacao_deveRetornarListaDeVotosComPaginacao() {
        // Mock dos objetos necessários
        VotoController controller = new VotoController();
        Pageable pageable = Pageable.ofSize(10);
        Page<Voto> votos = new PageImpl<>(Collections.emptyList(), pageable, 0);
        ResponseEntity<Page<Voto>> expectedResponse = ResponseEntity.ok(votos);

        // Configuração do mock
        when(votoService.listarVotos(pageable)).thenReturn(votos);

        // Chamada do método a ser testado
        ResponseEntity<Page<Voto>> response = controller.listarVotosComPaginacao(0, 10);

        // Verificação dos resultados
        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());

        // Verificação do serviço chamado corretamente
        verify(votoService, times(1)).listarVotos(pageable);
        verifyNoMoreInteractions(votoService);
    }
    @Test
    public void testListarVotos() {
        // Mock dos dados de retorno do serviço
        ResultadoVotacaoResponse resultadoVotacaoResponse = new ResultadoVotacaoResponse();
        resultadoVotacaoResponse.setSimVotes(10L);
        resultadoVotacaoResponse.setNaoVotes(5L);

        // Configuração do comportamento do serviço mockado
        Mockito.when(votoService.countVotos(10L)).thenReturn(resultadoVotacaoResponse);

        // Chamada ao endpoint
        ResponseEntity<ResultadoVotacaoResponse> response = votoController.listarVotos(10L);

        // Verificação do status code da resposta
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificação dos dados da resposta
        ResultadoVotacaoResponse responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(10, responseBody.getSimVotes());
        assertEquals(5, responseBody.getNaoVotes());

        // Verificação do serviço chamado corretamente
        verify(votoService, times(1)).countVotos(10L);
        verifyNoMoreInteractions(votoService);
    }

    @Test
    public void testVotar() {
        // Mock dos dados de entrada
        VotoDTO votoDTO = new VotoDTO();
        // Defina os valores necessários para o teste no objeto votoDTO
        votoDTO.setVoto(OpcaoVotoEnum.NAO);  // Defina o ID da sessão de votação apropriado
        votoDTO.setAssociado(new Associado());      // Defina o ID do associado apropriado
        votoDTO.setSessaoVotacao(new SessaoVotacao());      // Defina o ID do associado apropriado
        // Chamada ao endpoint
        ResponseEntity<String> response = votoController.votar(votoDTO);

        // Verificação do status code da resposta
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Verificação da mensagem de sucesso na resposta
        assertEquals("Voto registrado com sucesso!", response.getBody());

        // Verificação do serviço chamado corretamente
        verify(votoService, times(1)).save(any(Voto.class));
        verifyNoMoreInteractions(votoService);
    }


}
