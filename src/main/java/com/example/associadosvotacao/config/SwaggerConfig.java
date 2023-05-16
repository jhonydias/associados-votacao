package com.example.associadosvotacao.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public static final String
            ASSOCIADO_TAG = "Associado",
            SESSAO_VOTACAO_TAG = "Sessão Votação",
            VOTO_TAG = "Voto",
            PAUTA_TAG = "Pauta";

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(metaData())
                //.securityContexts(Collections.singletonList(securityContext()))
                //.securitySchemes(Collections.singletonList(apiKey()))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .tags(new Tag(ASSOCIADO_TAG, "Realizar operações com os associados"),
                        new Tag(SESSAO_VOTACAO_TAG, "Realizar operações com as sessões de votação"),
                        new Tag(VOTO_TAG, "Realizar operações com os votos"),
                        new Tag(PAUTA_TAG, "Realizar operações com as pautas"));
    }


    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Votação Associados API REST")
                .description("API REST de Votação de Pautas.")
                .version("1.0.0")
                .contact(new Contact("Jhony Dias",
                        "https://github.com/jhonydias/associados-votacao",
                        "jhonydias.work@gmail.com"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }
}
