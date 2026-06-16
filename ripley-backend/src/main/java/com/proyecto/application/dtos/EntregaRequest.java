package com.proyecto.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EntregaRequest {
    private Integer idProveedor;
    private LocalDate fechaPedido;
    private LocalDate fechaEntregaReal;
    private Integer cantidadPedida;
    private Integer cantidadEntregada;
    private BigDecimal calidadObservada; // 1-5

    public Integer getIdProveedor() { return idProveedor; }

    public void setIdProveedor(Integer v) { this.idProveedor = v; }

    public LocalDate getFechaPedido() { return fechaPedido; }

    public void setFechaPedido(LocalDate v) { this.fechaPedido = v; }

    public LocalDate getFechaEntregaReal() { return fechaEntregaReal; }

    public void setFechaEntregaReal(LocalDate v) { this.fechaEntregaReal = v; }

    public Integer getCantidadPedida() { return cantidadPedida; }

    public void setCantidadPedida(Integer v) { this.cantidadPedida = v; }

    public Integer getCantidadEntregada() { return cantidadEntregada; }

    public void setCantidadEntregada(Integer v) { this.cantidadEntregada = v; }

    public BigDecimal getCalidadObservada() { return calidadObservada; }

    public void setCalidadObservada(BigDecimal v) { this.calidadObservada = v; }
}
