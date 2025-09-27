package com.architecturelab.hexagonal.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration de Swagger / OpenAPI pour générer automatiquement
 * la documentation REST de l'application.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Hexagonal Architecture API",
                version = "1.0",
                description = "Documentation de l'API exposée par l'application hexagonale (Ports & Adapters)"
        )
)
public class SwaggerConfig {
}
