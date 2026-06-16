package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Usuario;
import java.util.Optional;


/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tabla BD: usuarios
 * CU-07 Control de Acceso
 */
public interface IUsuarioRepository {

    /** Guarda un nuevo usuario */
    Usuario guardar(Usuario usuario);

    /** Busca usuario por correo (para login) */
    Optional<Usuario> buscarPorCorreo(String correo);

    /** Busca usuario por ID */
    Optional<Usuario> buscarPorId(Integer idUsuario);

    /**
     * Incrementa el contador de intentos fallidos en BD
     * CU-07: bloqueo progresivo hasta 5 intentos
     */
    void incrementarIntentosFallidos(Integer idUsuario);

    /**
     * Resetea los intentos fallidos tras login exitoso
     * CU-07: reseteo al autenticarse correctamente
     */
    void resetearIntentosFallidos(Integer idUsuario);

    /** Bloquea la cuenta tras 5 intentos fallidos */
    void bloquearCuenta(Integer idUsuario);

    /** Verifica si la cuenta está bloqueada */
    boolean estaBloqueada(Integer idUsuario);

    /** Obtiene el número de intentos fallidos para un usuario */
    int obtenerIntentosFallidos(Integer idUsuario);

}
