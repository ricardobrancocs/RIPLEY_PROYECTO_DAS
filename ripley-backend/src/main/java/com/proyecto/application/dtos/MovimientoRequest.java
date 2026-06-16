package com.proyecto.application.dtos;

import java.time.LocalDateTime;

public class MovimientoRequest {
    private Integer idProducto;
    private Integer cantidad;
    private String tipoMovimiento; // ENTRADA, SALIDA
    private LocalDateTime fecha;

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer v) { this.cantidad = v; }

    public String getTipoMovimiento() { return tipoMovimiento; }

    public void setTipoMovimiento(String v) { this.tipoMovimiento = v; }

    public LocalDateTime getFecha() { return fecha; }

    public void setFecha(LocalDateTime v) { this.fecha = v; }
}
