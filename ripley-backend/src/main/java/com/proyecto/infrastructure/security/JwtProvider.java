package com.proyecto.infrastructure.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * CAPA: INFRASTRUCTURE — Security
 * Genera y valida tokens JWT firmados con HS256
 * Expiración: 8 horas
 */
@ApplicationScoped
public class JwtProvider {

    private static final String SECRET =
            "RipleyInventarioSistema2024SecretKeyHS256MustBe32CharsMin!!";
    private static final long EXPIRATION_MS = 8 * 60 * 60 * 1000L; // 8 horas

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    /** Genera un token JWT con idUsuario, rol y nombre */
    public String generarToken(Integer idUsuario, String rol, String nombre) {
        return Jwts.builder()
                .subject(String.valueOf(idUsuario))
                .claim("rol",    rol)
                .claim("nombre", nombre)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(getKey())
                .compact();
    }

    /** Retorna true si el token es válido y no expiró */
    public boolean validarToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    /** Extrae el rol del token (sin validar de nuevo) */
    public String extraerRol(String token) {
        return (String) getClaims(token).get("rol");
    }

    /** Extrae el idUsuario del token */
    public String extraerSub(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}