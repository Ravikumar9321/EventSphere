package com.eventmanagement.Configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenAPIConfig {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Event API")
                .version("v1.0")
                .description("Event Management System related API's with JWT Security"))
            .components(new Components().addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
    }
	 @Bean
	    public OpenApiCustomizer securityOpenApiCustomizer() {
	        return openApi -> {
	            openApi.getPaths().forEach((path, pathItem) -> {
	                pathItem.readOperations().forEach(operation -> {
	                    if (!path.startsWith("/api/auth")) {
	                        operation.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
	                    }
	                });
	            });
	        };
	    }
}
