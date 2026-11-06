package com.example.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
  title = "Conecta Cultura Auth API",
  version = "1.0",
  description = "Autenticación y emisión de JWT"
))
public class OpenApiConfig {}
