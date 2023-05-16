package com.example.associadosvotacao.config.handler;

import com.example.associadosvotacao.exception.VotoAssociadoExistenteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(VotoAssociadoExistenteException.class)
    public ResponseEntity<String> handleVotoAssociadoExistenteException(VotoAssociadoExistenteException ex) {
        String errorMessage = "Erro ao registrar voto: já existe um voto registrado para o associado nesta sessão de votação";
        log.warn("Falha ao realizar requisição: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String errorMessage = "Ocorreu um erro durante a execução da operação.";
        log.warn("Falha ao realizar requisição: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
