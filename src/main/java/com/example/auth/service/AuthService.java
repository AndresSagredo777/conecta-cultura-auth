package com.example.auth.service;

import com.example.auth.dto.*;
import com.example.auth.model.Usuario;
import com.example.auth.repository.UsuarioRepository;
import com.example.auth.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UsuarioRepository usuarios;
  private final PasswordEncoder encoder;
  private final JwtService jwt;

  public AuthService(UsuarioRepository usuarios,
      PasswordEncoder encoder, JwtService jwt) {
    this.usuarios = usuarios;
    this.encoder = encoder;
    this.jwt = jwt;
  }

  public LoginResponse login(LoginRequest request) {
    Usuario usuario = usuarios.findByCorreoIgnoreCase(request.correo())
      .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));
    if (!encoder.matches(request.password(), usuario.getPassword())) {
      throw new BadCredentialsException("Credenciales inválidas");
    }
    return new LoginResponse(jwt.generar(usuario),
      usuario.getCorreo(), usuario.getRol());
  }
}
