package com.proyecto.domain.entities;

import java.math.BigDecimal;

/**
 * CAPA: DOMINIO
 * Representa cada producto dentro de una venta o proforma
 */
public class DetalleVenta {

    private Integer idDetalle;
    private Integer idVenta;
    private Integer idProducto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    // ═══════════════════════════════════════
    // Constructor vacío
    // ═══════════════════════════════════════
    public DetalleVenta() {}

    // ═══════════════════════════════════════
    // Constructor completo
    // ═══════════════════════════════════════
    public DetalleVenta(Integer idDetalle, Integer idVenta, Integer idProducto,
                        Integer cantidad, BigDecimal precioUnitario, BigDecimal subtotal) {
        this.idDetalle      = idDetalle;
        this.idVenta        = idVenta;
        this.idProducto     = idProducto;
        this.cantidad       = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal       = subtotal;
    }

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdDetalle()                    { return idDetalle; }
    public void setIdDetalle(Integer idDetalle)      { this.idDetalle = idDetalle; }

    public Integer getIdVenta()                      { return idVenta; }
    public void setIdVenta(Integer idVenta)          { this.idVenta = idVenta; }

    public Integer getIdProducto()                   { return idProducto; }
    public void setIdProducto(Integer idProducto)    { this.idProducto = idProducto; }

    public Integer getCantidad()                     { return cantidad; }
    public void setCantidad(Integer cantidad)        { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario()                        { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario)     { this.precioUnitario = precioUnitario; }

    public BigDecimal getSubtotal()                  { return subtotal; }
    public void setSubtotal(BigDecimal subtotal)     { this.subtotal = subtotal; }
}