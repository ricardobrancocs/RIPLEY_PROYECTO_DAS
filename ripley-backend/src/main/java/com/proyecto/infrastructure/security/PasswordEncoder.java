package com.proyecto.infrastructure.security;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * CAPA: INFRASTRUCTURE — Security
 * Hashea y verifica contraseñas con BCrypt (cost=12)
 */
@ApplicationScoped
public class PasswordEncoder {

    private static final int COST = 12;

    /** Hashea una contraseña en texto plano */
    public String hashear(String plain) {
        return BCrypt.withDefaults().hashToString(COST, plain.toCharArray());
    }

    /** Verifica una contraseña contra su hash */
    public boolean verificar(String plain, String hash) {
        BCrypt.Result result = BCrypt.verifyer()
                .verify(plain.toCharArray(), hash);
        return result.verified;
    }
}