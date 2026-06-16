package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: detalle_ventas
 */
@Entity
@Table(name = "detalle_ventas")
public class DetalleVentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    // N:1 con ventas
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", nullable = false)
    private VentaModel venta;

    // N:1 con productos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    // ── Constructores ─────────────────────────
    public DetalleVentaModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer      getIdDetalle()                    { return idDetalle; }
    public void         setIdDetalle(Integer v)           { this.idDetalle = v; }
    public VentaModel   getVenta()                        { return venta; }
    public void         setVenta(VentaModel v)            { this.venta = v; }
    public ProductoModel getProducto()                    { return producto; }
    public void         setProducto(ProductoModel v)      { this.producto = v; }
    public Integer      getCantidad()                     { return cantidad; }
    public void         setCantidad(Integer v)            { this.cantidad = v; }
    public BigDecimal   getPrecioUnitario()               { return precioUnitario; }
    public void         setPrecioUnitario(BigDecimal v)   { this.precioUnitario = v; }
    public BigDecimal   getSubtotal()                     { return subtotal; }
    public void         setSubtotal(BigDecimal v)         { this.subtotal = v; }
}
