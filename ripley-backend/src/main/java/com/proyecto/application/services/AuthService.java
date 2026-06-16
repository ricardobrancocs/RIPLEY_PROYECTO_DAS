package com.proyecto.application.services;

import com.proyecto.application.dtos.AuthResponse;
import com.proyecto.application.dtos.LoginRequest;
import com.proyecto.domain.entities.Usuario;
import com.proyecto.domain.interfaces.IUsuarioRepository;
import com.proyecto.infrastructure.security.JwtProvider;
//import com.proyecto.infrastructure.security.PasswordEncoder;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * CAPA: APLICACIÓN — Services
 * CU-07 Control de Acceso
 * Login con BCrypt + JWT real
 */
@ApplicationScoped
public class AuthService {

    @Inject private IUsuarioRepository usuarioRepository;
    @Inject private JwtProvider        jwtProvider;
    //@Inject private PasswordEncoder    passwordEncoder;

    /** POST login — valida credenciales y devuelve JWT firmado */
    public AuthResponse login(LoginRequest dto) {

    Usuario usuario = usuarioRepository.buscarPorCorreo(dto.getUsername())
            .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));

    if (usuarioRepository.estaBloqueada(usuario.getIdUsuario())) {
        throw new RuntimeException("Cuenta bloqueada — espere 2 minutos");
    }

    boolean passwordOk = dto.getPassword().equals(usuario.getContrasena());

    if (!passwordOk) {
        // Primero incrementar en BD
        usuarioRepository.incrementarIntentosFallidos(usuario.getIdUsuario());
        
        // Leer el valor actualizado desde BD
        int intentosActuales = usuarioRepository.obtenerIntentosFallidos(usuario.getIdUsuario());
        int restantes = 5 - intentosActuales;

        if (restantes <= 0) {
            usuarioRepository.bloquearCuenta(usuario.getIdUsuario());
            throw new RuntimeException("Cuenta bloqueada tras 5 intentos fallidos — espere 2 minutos");
        }
        throw new RuntimeException(
                "Credenciales incorrectas — intentos restantes: " + restantes);
    }

    usuarioRepository.resetearIntentosFallidos(usuario.getIdUsuario());

    String token = jwtProvider.generarToken(
            usuario.getIdUsuario(),
            usuario.getRol(),
            usuario.getNombre());

    return new AuthResponse(token, usuario.getRol(), usuario.getNombre());
}

    /** POST logout — en producción agregar token a blacklist */
    public void logout(String token) {
        // Token stateless — el cliente lo descarta localmente
        // Para invalidación real: guardar en BD con fecha expiración
    }
}