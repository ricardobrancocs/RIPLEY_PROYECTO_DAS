package com.proyecto.domain.entities;

import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * CU-08 Proceso de Devoluciones
 * Java puro — sin frameworks
 *
 * PATRÓN: Factory Method (crea la devolución según el motivo)
 */
public class Devolucion {

    // ── Campos ───────────────────────────────────
    private Integer       idDevolucion;
    private Integer       idVentaOriginal;
    private Integer       idProducto;
    private Integer       cantidad;
    private String        motivo;       // DEFECTO|CAMBIO_TALLA|CAMBIO_COLOR|OTRO
    private String        estado;       // REGISTRADA|RECHAZADA
    private LocalDateTime fecha;

    // ── Constructor privado ───────────────────────
    private Devolucion() {}

    // ── PATRÓN FACTORY METHOD ─────────────────────
    /** Crea devolución por DEFECTO de fabricación */
    public static Devolucion porDefecto(Integer idVenta, Integer idProducto, Integer cantidad) {
        Devolucion d = new Devolucion();
        d.idVentaOriginal = idVenta;
        d.idProducto      = idProducto;
        d.cantidad        = cantidad;
        d.motivo          = "DEFECTO";
        d.estado          = "REGISTRADA";
        d.fecha           = LocalDateTime.now();
        return d;
    }

    /** Crea devolución por CAMBIO DE TALLA */
    public static Devolucion porCambioTalla(Integer idVenta, Integer idProducto, Integer cantidad) {
        Devolucion d = new Devolucion();
        d.idVentaOriginal = idVenta;
        d.idProducto      = idProducto;
        d.cantidad        = cantidad;
        d.motivo          = "CAMBIO_TALLA";
        d.estado          = "REGISTRADA";
        d.fecha           = LocalDateTime.now();
        return d;
    }

    /** Crea devolución por OTRO motivo libre */
    public static Devolucion porOtroMotivo(Integer idVenta, Integer idProducto,
        Integer cantidad, String motivo) {
        Devolucion d = new Devolucion();
        d.idVentaOriginal = idVenta;
        d.idProducto      = idProducto;
        d.cantidad        = cantidad;
        d.motivo          = motivo != null ? motivo : "OTRO";
        d.estado          = "REGISTRADA";
        d.fecha           = LocalDateTime.now();
        return d;
    }

    // ── Lógica de negocio CU-08 ──────────────────
    public void rechazar() { this.estado = "RECHAZADA"; }
    public boolean estaRegistrada() { return "REGISTRADA".equals(this.estado); }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdDevolucion()                    { return idDevolucion; }
    public void          setIdDevolucion(Integer v)           { this.idDevolucion = v; }
    public Integer       getIdVentaOriginal()                 { return idVentaOriginal; }
    public void          setIdVentaOriginal(Integer v)        { this.idVentaOriginal = v; }
    public Integer       getIdProducto()                      { return idProducto; }
    public void          setIdProducto(Integer v)             { this.idProducto = v; }
    public Integer       getCantidad()                        { return cantidad; }
    public void          setCantidad(Integer v)               { this.cantidad = v; }
    public String        getMotivo()                          { return motivo; }
    public void          setMotivo(String v)                  { this.motivo = v; }
    public String        getEstado()                          { return estado; }
    public void          setEstado(String v)                  { this.estado = v; }
    public LocalDateTime getFecha()                           { return fecha; }
    public void          setFecha(LocalDateTime v)            { this.fecha = v; }
}
