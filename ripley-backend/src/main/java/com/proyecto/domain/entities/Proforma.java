package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CAPA: DOMINIO
 * Entidad pura — sin anotaciones de frameworks
 * Representa una proforma antes de convertirse en venta
 */
public class Proforma {

    private Integer idProforma;
    private LocalDateTime fechaCreacion;
    private String estado; // BORRADOR, CONFIRMADA, ANULADA
    private BigDecimal subtotal;
    private Integer idUsuario;
    private List<DetalleVenta> detalles;

    // ═══════════════════════════════════════
    // Constructor vacío
    // ═══════════════════════════════════════
    public Proforma() {}

    // ═══════════════════════════════════════
    // Constructor completo
    // ═══════════════════════════════════════
    public Proforma(Integer idProforma, LocalDateTime fechaCreacion,
                    String estado, BigDecimal subtotal, Integer idUsuario) {
        this.idProforma    = idProforma;
        this.fechaCreacion = fechaCreacion;
        this.estado        = estado;
        this.subtotal      = subtotal;
        this.idUsuario     = idUsuario;
    }

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdProforma()                   { return idProforma; }
    public void setIdProforma(Integer idProforma)    { this.idProforma = idProforma; }

    public LocalDateTime getFechaCreacion()                      { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion)    { this.fechaCreacion = fechaCreacion; }

    public String getEstado()                    { return estado; }
    public void setEstado(String estado)         { this.estado = estado; }

    public BigDecimal getSubtotal()              { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public Integer getIdUsuario()                { return idUsuario; }
    public void setIdUsuario(Integer idUsuario)  { this.idUsuario = idUsuario; }

    public List<DetalleVenta> getDetalles()                  { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles)     { this.detalles = detalles; }
}