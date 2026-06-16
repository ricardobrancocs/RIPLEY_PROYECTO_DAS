package com.proyecto.domain.entities;

import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * CU-09 Trazabilidad de Productos
 * Java puro — sin frameworks
 *
 * PATRÓN: Prototype (clonar un movimiento base para registrar
 *         variantes de traslado en distintas ubicaciones)
 * Nota: el historial es INMUTABLE — no se permite editar ni borrar
 */
public class MovimientoTrazabilidad implements Cloneable {

    // ── Campos ───────────────────────────────────
    private Integer       idMovimiento;
    private Integer       idProducto;
    private String        ubicacion;       // almacén, tienda, en tránsito
    private String        responsable;     // nombre o código del operario
    private LocalDateTime fecha;           // marca de tiempo precisa
    private String        descripcion;     // detalle de la acción

    // ── Constructores ────────────────────────────
    public MovimientoTrazabilidad() {}

    public MovimientoTrazabilidad(
        Integer idProducto, 
        String ubicacion,
        String responsable, 
        String descripcion) {
        this.idProducto  = idProducto;
        this.ubicacion   = ubicacion;
        this.responsable = responsable;
        this.descripcion = descripcion;
        this.fecha       = LocalDateTime.now(); // marca de tiempo exacta
    }

    // ── PATRÓN PROTOTYPE ─────────────────────────
    /** Clona el movimiento base para registrar traslado a nueva ubicación */
    @Override
    public MovimientoTrazabilidad clone() {
        try {
            MovimientoTrazabilidad clon = (MovimientoTrazabilidad) super.clone();
            clon.idMovimiento = null;          // el clon tendrá su propio ID al persistir
            clon.fecha        = LocalDateTime.now();
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar MovimientoTrazabilidad", e);
        }
    }

    // ── Getters y Setters ────────────────────────
    // Sin setters para fecha (inmutabilidad del historial)
    public Integer       getIdMovimiento()               { return idMovimiento; }
    public void          setIdMovimiento(Integer v)      { this.idMovimiento = v; }
    public Integer       getIdProducto()                 { return idProducto; }
    public void          setIdProducto(Integer v)        { this.idProducto = v; }
    public String        getUbicacion()                  { return ubicacion; }
    public void          setUbicacion(String v)          { this.ubicacion = v; }
    public String        getResponsable()                { return responsable; }
    public void          setResponsable(String v)        { this.responsable = v; }
    public LocalDateTime getFecha()                      { return fecha; }
    public String        getDescripcion()                { return descripcion; }
    public void          setDescripcion(String v)        { this.descripcion = v; }
}
