package com.proyecto.domain.entities;

import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Tabla BD: alertas_stock
 * Java puro — sin frameworks
 *
 * PATRÓN: Factory Method (AlertaFactory en el Service decide
 *         qué tipo de alerta crear según el nivel de riesgo)
 */
public class Alerta {
// implementar una interfaz y abstract 
    // ── Campos BD ────────────────────────────────
    private Integer       idAlerta;
    private Integer       idProducto;
    private String        tipoAlerta;   // varchar(100): CRITICA|ADVERTENCIA|INFORMATIVA
    private String        mensaje;      // text
    private LocalDateTime fechaAlerta;  // timestamp
    private String        estado;       // varchar(1): A=activa|T=atendida|D=descartada

    // ── Constructores ────────────────────────────
    public Alerta() {}

    public Alerta(
        Integer idProducto, 
        String tipoAlerta,
        String mensaje, 
        LocalDateTime fechaAlerta) {
        this.idProducto = idProducto;
        this.tipoAlerta = tipoAlerta;
        this.mensaje    = mensaje;
        this.fechaAlerta = fechaAlerta;
        this.estado     = "A"; // activa por defecto
    }

    // ── PATRÓN FACTORY METHOD ─────────────────────
    /** Crea una alerta CRÍTICA — stock proyectado llega a 0 en < 48h */
    public static Alerta critica(Integer idProducto, String nombreProducto) {
        return new Alerta(idProducto,
                "CRITICA",
                "URGENTE: quiebre de stock en menos de 48h para: " + nombreProducto,
                LocalDateTime.now());
    }

    /** Crea una alerta de ADVERTENCIA — stock < mínimo */
    public static Alerta advertencia(Integer idProducto, String nombreProducto) {
        return new Alerta(idProducto,
                "ADVERTENCIA",
                "Stock bajo el mínimo para: " + nombreProducto + ". Revisar reposición.",
                LocalDateTime.now());
    }

    /** Crea una alerta INFORMATIVA — stock próximo al mínimo */
    public static Alerta informativa(Integer idProducto, String nombreProducto) {
        return new Alerta(idProducto,
                "INFORMATIVA",
                "Stock próximo al mínimo para: " + nombreProducto,
                LocalDateTime.now());
    }

    // ── Lógica de negocio CU-05 ──────────────────
    public void atender()            { this.estado = "T"; }
    public void descartar()          { this.estado = "D"; }
    public boolean estaActiva()      { return "A".equals(this.estado); }
    public boolean esCritica()       { return "CRITICA".equals(this.tipoAlerta); }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdAlerta()                   { return idAlerta; }
    public void          setIdAlerta(Integer v)          { this.idAlerta = v; }
    public Integer       getIdProducto()                 { return idProducto; }
    public void          setIdProducto(Integer v)        { this.idProducto = v; }
    public String        getTipoAlerta()                 { return tipoAlerta; }
    public void          setTipoAlerta(String v)         { this.tipoAlerta = v; }
    public String        getMensaje()                    { return mensaje; }
    public void          setMensaje(String v)            { this.mensaje = v; }
    public LocalDateTime getFechaAlerta()                { return fechaAlerta; }
    public void          setFechaAlerta(LocalDateTime v) { this.fechaAlerta = v; }
    public String        getEstado()                     { return estado; }
    public void          setEstado(String v)             { this.estado = v; }
}
