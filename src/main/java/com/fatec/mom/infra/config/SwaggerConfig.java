package com.fatec.mom.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    public static final String TITLE = "Management of Operational Manuals";
    public static final String VERSION = "0.1";
    public static final String DESCRIPTION = "Sistema de customização, controle e revisão de documentos formados" +
            " por fragmentos armazenados em arquivos PDF, usando regras específicas para gerar o documento final.";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title(TITLE)
                .version(VERSION)
                .description(DESCRIPTION)
                .build();
    }
}
