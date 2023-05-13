package com.example.associadosvotacao.v1.annotation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses({
        @ApiResponse(code = 204, message = "Finalizado com sucesso, sem retorno de dados"),
        @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
        @ApiResponse(code = 404, message = "Endpoint ou Recurso não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
})
public @interface ApiResponsesDefaultNoContent {
}
