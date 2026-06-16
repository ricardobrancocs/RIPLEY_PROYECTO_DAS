package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CAPA: DOMINIO
 * Entidad pura — sin anotaciones de frameworks
 * Representa una venta registrada en el sistema
 */
public class Venta implements Cloneable {

    private Integer idVenta;
    private LocalDateTime fechaVenta;
    private BigDecimal total;
    private String estado; // PENDIENTE, COMPLETADA, ANULADA

    private Integer idUsuario;
    private List<DetalleVenta> detalles;

    // ═══════════════════════════════════════
    // Constructor vacío
    // ═══════════════════════════════════════
    public Venta() {}

    // ═══════════════════════════════════════
    // Constructor completo
    // ═══════════════════════════════════════
    public Venta(Integer idVenta, LocalDateTime fechaVenta,
        BigDecimal total, String estado, Integer idUsuario) {
        this.idVenta    = idVenta;
        this.fechaVenta = fechaVenta;
        this.total      = total;
        this.estado     = estado;
        this.idUsuario  = idUsuario;
    }

    // ═══════════════════════════════════════
    // PATRÓN PROTOTYPE — clonar()
    // Devuelve una copia profunda de la venta
    // con idVenta null y estado PENDIENTE
    // ═══════════════════════════════════════ 
    public Venta clonar() {
        Venta copia = new Venta();
        copia.setIdVenta(null);                    // nuevo registro, sin ID aún
        copia.setIdUsuario(this.idUsuario);
        copia.setEstado("PENDIENTE");              // siempre empieza como pendiente
        copia.setTotal(this.total);
        copia.setFechaVenta(LocalDateTime.now());  // fecha actual, no la del original
 
        // Copia profunda de los detalles para no compartir referencias
        if (this.detalles != null) {
            List<DetalleVenta> copiaDetalles = new ArrayList<>();
            for (DetalleVenta d : this.detalles) {
                DetalleVenta copiaDetalle = new DetalleVenta();
                copiaDetalle.setIdProducto(d.getIdProducto());
                copiaDetalle.setCantidad(d.getCantidad());
                copiaDetalle.setPrecioUnitario(d.getPrecioUnitario());
                copiaDetalle.setSubtotal(d.getSubtotal());
                // idDetalle e idVenta null — son nuevos registros
                copiaDetalles.add(copiaDetalle);
            }
            copia.setDetalles(copiaDetalles);
        }
        return copia;
    }

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdVenta()                  { return idVenta; }
    public void setIdVenta(Integer idVenta)      { this.idVenta = idVenta; }

    public LocalDateTime getFechaVenta()                     { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta)      { this.fechaVenta = fechaVenta; }

    public BigDecimal getTotal()                 { return total; }
    public void setTotal(BigDecimal total)       { this.total = total; }

    public String getEstado()                    { return estado; }
    public void setEstado(String estado)         { this.estado = estado; }

    public Integer getIdUsuario()                { return idUsuario; }
    public void setIdUsuario(Integer idUsuario)  { this.idUsuario = idUsuario; }

    public List<DetalleVenta> getDetalles()                  { return detalles; }
    public void setDetalles(List<DetalleVenta> detalles)     { this.detalles = detalles; }
}