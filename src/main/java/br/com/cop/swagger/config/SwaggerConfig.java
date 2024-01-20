package br.com.cop.swagger.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class SwaggerConfig {

    private final String openApiPath = "api-docs.json";

    @Bean
    public OpenAPI openAPI() throws IOException {
        InputStream openApiJSON = getClass().getClassLoader().getResourceAsStream(openApiPath);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(openApiJSON, OpenAPI.class);
    }
}
