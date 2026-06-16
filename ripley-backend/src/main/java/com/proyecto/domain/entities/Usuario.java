package com.proyecto.domain.entities;

import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Tabla BD: usuarios
 * Java puro — sin frameworks
 *
 * PATRÓN: Builder (UsuarioBuilder — construye el usuario paso a paso
 *         con validaciones antes del build())
 */
public class Usuario {

    // ── Campos BD ────────────────────────────────
    private Integer       idUsuario;
    private String        nombre;
    private String        correo;
    private String        contrasena;
    private String        rol;
    private LocalDateTime fechaRegistro;

    // ── Control de acceso CU-07 ──────────────────
    private int           intentosFallidos;
    private boolean       cuentaBloqueada;
    private LocalDateTime fechaBloqueo;      // ← NUEVO

    // ── Constructor privado — solo el Builder lo llama ───────
    private Usuario() {
        this.intentosFallidos = 0;
        this.cuentaBloqueada  = false;
        this.fechaBloqueo     = null;
    }

    // ════════════════════════════════════════════════════════
    // PATRÓN BUILDER
    // ════════════════════════════════════════════════════════
    public static class Builder {
        private Integer       idUsuario;
        private String        nombre;
        private String        correo;
        private String        contrasena;
        private String        rol;
        private LocalDateTime fechaRegistro = LocalDateTime.now();

        public Builder idUsuario(Integer v)          { this.idUsuario = v;      return this; }
        public Builder nombre(String v)              { this.nombre = v;         return this; }
        public Builder correo(String v)              { this.correo = v;         return this; }
        public Builder contrasena(String v)          { this.contrasena = v;     return this; }
        public Builder rol(String v)                 { this.rol = v;            return this; }
        public Builder fechaRegistro(LocalDateTime v){ this.fechaRegistro = v;  return this; }

        public Usuario build() {
            if (nombre == null || nombre.isBlank())
                throw new IllegalArgumentException("Nombre de usuario requerido");
            if (correo == null || correo.isBlank())
                throw new IllegalArgumentException("Correo requerido");
            if (contrasena == null || contrasena.isBlank())
                throw new IllegalArgumentException("Contraseña requerida");
            if (rol == null || rol.isBlank())
                throw new IllegalArgumentException("Rol requerido");

            Usuario u = new Usuario();
            u.idUsuario     = this.idUsuario;
            u.nombre        = this.nombre;
            u.correo        = this.correo;
            u.contrasena    = this.contrasena;
            u.rol           = this.rol;
            u.fechaRegistro = this.fechaRegistro;
            return u;
        }
    }

    // ── Lógica de negocio CU-07 ──────────────────
    public void registrarIntentoFallido() {
        this.intentosFallidos++;
        if (this.intentosFallidos >= 5) {
            this.cuentaBloqueada = true;
            this.fechaBloqueo    = LocalDateTime.now();  // ← NUEVO
        }
    }

    public void resetearIntentos() {
        this.intentosFallidos = 0;
        this.cuentaBloqueada  = false;
        this.fechaBloqueo     = null;  // ← NUEVO
    }

    public boolean estaBloqueada()     { return cuentaBloqueada; }
    public int     intentosRestantes() { return Math.max(0, 5 - intentosFallidos); }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdUsuario()                     { return idUsuario; }
    public void          setIdUsuario(Integer v)            { this.idUsuario = v; }
    public String        getNombre()                        { return nombre; }
    public void          setNombre(String v)                { this.nombre = v; }
    public String        getCorreo()                        { return correo; }
    public void          setCorreo(String v)                { this.correo = v; }
    public String        getContrasena()                    { return contrasena; }
    public void          setContrasena(String v)            { this.contrasena = v; }
    public String        getRol()                           { return rol; }
    public void          setRol(String v)                   { this.rol = v; }
    public LocalDateTime getFechaRegistro()                 { return fechaRegistro; }
    public void          setFechaRegistro(LocalDateTime v)  { this.fechaRegistro = v; }
    public int           getIntentosFallidos()              { return intentosFallidos; }
    public void          setIntentosFallidos(int v)         { this.intentosFallidos = v; }
    public boolean       isCuentaBloqueada()                { return cuentaBloqueada; }
    public void          setCuentaBloqueada(boolean v)      { this.cuentaBloqueada = v; }
    public LocalDateTime getFechaBloqueo()                  { return fechaBloqueo; }      // ← NUEVO
    public void          setFechaBloqueo(LocalDateTime v)   { this.fechaBloqueo = v; }    // ← NUEVO
}