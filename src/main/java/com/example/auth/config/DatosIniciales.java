package com.example.auth.config;

import com.example.auth.model.Usuario;
import com.example.auth.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatosIniciales {
  @Bean CommandLineRunner usuarioInicial(UsuarioRepository repo,
      PasswordEncoder encoder) {
    return args -> {
      if (repo.findByCorreoIgnoreCase("admin@conectacultura.cl").isEmpty()) {
        Usuario u = new Usuario();
        u.setCorreo("admin@conectacultura.cl");
        u.setPassword(encoder.encode("Cambiar123!"));
        u.setRol("ADMIN");
        repo.save(u);
      }
    };
  }
}
