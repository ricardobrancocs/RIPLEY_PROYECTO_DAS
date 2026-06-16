package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PATRÓN: BUILDER
 * CAPA: DOMINIO — Entidades
 *
 * Construye objetos Venta de forma legible y encadenada.
 * Evita constructores con muchos parámetros y llamadas
 * repetitivas a setters.
 *
 * USO EN CU-01:
 *   Venta venta = new VentaBuilder()
 *       .conUsuario(1)
 *       .conEstado("PENDIENTE")
 *       .conDetalle(2, 2, BigDecimal.valueOf(150))
 *       .build();
 */
public class VentaBuilder {

    // ═══════════════════════════════════════
    // ATRIBUTOS INTERNOS DEL BUILDER
    // ═══════════════════════════════════════
    private Integer            idUsuario;
    private String             estado;
    private BigDecimal         total;
    private LocalDateTime      fechaVenta;
    private List<DetalleVenta> detalles;

    // ═══════════════════════════════════════
    // CONSTRUCTOR — valores por defecto
    // ═══════════════════════════════════════
    public VentaBuilder() {
        this.estado     = "PENDIENTE";
        this.total      = BigDecimal.ZERO;
        this.fechaVenta = LocalDateTime.now();
        this.detalles   = new ArrayList<>();
    }

    // ═══════════════════════════════════════
    // MÉTODOS ENCADENADOS
    // ═══════════════════════════════════════

    public VentaBuilder conUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public VentaBuilder conEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public VentaBuilder conTotal(BigDecimal total) {
        this.total = total;
        return this;
    }

    public VentaBuilder conFecha(LocalDateTime fecha) {
        this.fechaVenta = fecha;
        return this;
    }

    /**
     * Agrega un detalle y acumula su subtotal al total general.
     *
     * @param idProducto ID del producto
     * @param cantidad   Cantidad solicitada
     * @param precio     Precio unitario
     */
    public VentaBuilder conDetalle(Integer idProducto, Integer cantidad, BigDecimal precio) {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdProducto(idProducto);
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precio);
        detalle.setSubtotal(precio.multiply(BigDecimal.valueOf(cantidad)));
        this.total = this.total.add(detalle.getSubtotal());
        this.detalles.add(detalle);
        return this;
    }

    /**
     * Agrega una lista completa de detalles ya construidos.
     */
    public VentaBuilder conDetalles(List<DetalleVenta> detalles) {
        this.detalles.addAll(detalles);
        return this;
    }

    // ═══════════════════════════════════════
    // BUILD — construye y valida la Venta
    // ═══════════════════════════════════════

    /**
     * Construye el objeto Venta con los valores acumulados.
     *
     * @throws IllegalStateException si falta idUsuario
     */
    public Venta build() {
        if (idUsuario == null) {
            throw new IllegalStateException(
                "VentaBuilder: idUsuario es obligatorio"
            );
        }
        Venta venta = new Venta();
        venta.setIdUsuario(idUsuario);
        venta.setEstado(estado);
        venta.setTotal(total);
        venta.setFechaVenta(fechaVenta);
        venta.setDetalles(detalles);
        return venta;
    }
}