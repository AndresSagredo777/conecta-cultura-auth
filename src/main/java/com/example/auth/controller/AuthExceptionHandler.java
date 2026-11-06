package com.example.auth.controller;

import java.util.Map;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;


@RestControllerAdvice
public class AuthExceptionHandler {
  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<Map<String, String>> credencialesInvalidas() {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
      .body(Map.of("mensaje", "Credenciales inválidas"));
  }
}
