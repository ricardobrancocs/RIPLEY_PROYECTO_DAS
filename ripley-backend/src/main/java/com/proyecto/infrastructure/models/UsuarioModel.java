package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: usuarios
 */
@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    // PK autogenerado
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    // Campos básicos del usuario 
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "correo", nullable = false, length = 100, unique = true)
    private String correo;

    @Column(name = "contraseña", nullable = false, length = 255)
    private String contrasena;   // hash bcrypt

    @Column(name = "rol", nullable = false, length = 10)
    private String rol;          // VENDEDOR|ANALISTA|ADMIN|GERENTE

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "intentos_fallidos")
    private int intentosFallidos = 0;

    @Column(name = "cuenta_bloqueada")
    private boolean cuentaBloqueada = false;

    @Column(name = "fecha_bloqueo")
    private LocalDateTime fechaBloqueo;
    // ── Constructores ─────────────────────────
    public UsuarioModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer       getIdUsuario()                    { return idUsuario; }

    public void          setIdUsuario(Integer v)           { this.idUsuario = v; }

    public String        getNombre()                       { return nombre; }

    public void          setNombre(String v)               { this.nombre = v; }

    public String        getCorreo()                       { return correo; }

    public void          setCorreo(String v)               { this.correo = v; }

    public String        getContrasena()                   { return contrasena; }

    public void          setContrasena(String v)           { this.contrasena = v; }

    public String        getRol()                          { return rol; }

    public void          setRol(String v)                  { this.rol = v; }

    public LocalDateTime getFechaRegistro()                { return fechaRegistro; }

    public void          setFechaRegistro(LocalDateTime v) { this.fechaRegistro = v; }

    public int           getIntentosFallidos()            { return intentosFallidos; }

    public void          setIntentosFallidos(int v)       { this.intentosFallidos = v; }   

    public boolean       isCuentaBloqueada()              { return cuentaBloqueada; }

    public void          setCuentaBloqueada(boolean v)    { this.cuentaBloqueada = v; }

    public LocalDateTime getFechaBloqueo()                { return fechaBloqueo; }

    public void          setFechaBloqueo(LocalDateTime v) { this.fechaBloqueo = v; }
}
