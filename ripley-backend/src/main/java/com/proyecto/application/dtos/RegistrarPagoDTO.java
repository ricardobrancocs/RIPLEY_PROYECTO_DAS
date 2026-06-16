package com.proyecto.application.dtos;

import java.math.BigDecimal;

/**
 * CAPA: APLICACION — DTOs
 * Data Transfer Object para registrar el pago de una venta
 * Corresponde al Paso 5 del flujo básico del CU-Venta
 */
public class RegistrarPagoDTO {

    private Integer idProforma;
    private BigDecimal montoPagado;
    private String metodoPago; // EFECTIVO, TARJETA, TRANSFERENCIA

    // ═══════════════════════════════════════
    // Constructor vacío
    // ═══════════════════════════════════════
    public RegistrarPagoDTO() {}

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdProforma()                   { return idProforma; }
    public void setIdProforma(Integer idProforma)    { this.idProforma = idProforma; }

    public BigDecimal getMontoPagado()                       { return montoPagado; }
    public void setMontoPagado(BigDecimal montoPagado)       { this.montoPagado = montoPagado; }

    public String getMetodoPago()                    { return metodoPago; }
    public void setMetodoPago(String metodoPago)     { this.metodoPago = metodoPago; }
}