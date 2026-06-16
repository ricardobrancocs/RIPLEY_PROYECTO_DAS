package com.proyecto.application.dtos;

/**
 * CAPA: APLICACIÓN — DTOs
 * Respuesta del login exitoso
 */
public class AuthResponse {

    private String  token;
    private String  rol;
    private String  nombre;
    private long    expiresIn; // segundos — 8 horas = 28800

    public AuthResponse() {}

    public AuthResponse(String token, String rol, String nombre) {
        this.token     = token;
        this.rol       = rol;
        this.nombre    = nombre;
        this.expiresIn = 28800L;
    }

    public String getToken()     { return token;     }
    public String getRol()       { return rol;       }
    public String getNombre()    { return nombre;    }
    public long   getExpiresIn() { return expiresIn; }

    public void setToken(String v)     { this.token     = v; }
    public void setRol(String v)       { this.rol       = v; }
    public void setNombre(String v)    { this.nombre    = v; }
    public void setExpiresIn(long v)   { this.expiresIn = v; }
}