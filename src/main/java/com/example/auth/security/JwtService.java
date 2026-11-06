package com.example.auth.security;

import com.example.auth.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
  @Value("${app.jwt.secret}") private String secret;
  @Value("${app.jwt.expiration-ms}") private long expirationMs;

  private SecretKey clave() {
    return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
  }

  public String generar(Usuario usuario) {
    Date ahora = new Date();
    return Jwts.builder()
      .issuer("conecta-cultura-auth")
      .subject(usuario.getCorreo())
      .claim("rol", usuario.getRol())
      .issuedAt(ahora)
      .expiration(new Date(ahora.getTime() + expirationMs))
      .signWith(clave())
      .compact();
  }
}

