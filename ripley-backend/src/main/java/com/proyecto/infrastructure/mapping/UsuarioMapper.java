package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Usuario;
import com.proyecto.infrastructure.models.UsuarioModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte Usuario (dominio) ↔ UsuarioModel (JPA)
 * Tabla BD: usuarios
 */
public class UsuarioMapper {

    public static UsuarioModel toModel(Usuario e) {
        if (e == null) return null;
        UsuarioModel m = new UsuarioModel();
        m.setIdUsuario(e.getIdUsuario());
        m.setNombre(e.getNombre());
        m.setCorreo(e.getCorreo());
        m.setContrasena(e.getContrasena());
        m.setRol(e.getRol());
        m.setFechaRegistro(e.getFechaRegistro());
        return m;
    }

    public static Usuario toDomain(UsuarioModel m) {
        if (m == null) return null;
        // Usamos el Builder para construir el Usuario
        return new Usuario.Builder()
                .idUsuario(m.getIdUsuario())
                .nombre(m.getNombre())
                .correo(m.getCorreo())
                .contrasena(m.getContrasena())
                .rol(m.getRol())
                .fechaRegistro(m.getFechaRegistro())
                .build();
    }
}
