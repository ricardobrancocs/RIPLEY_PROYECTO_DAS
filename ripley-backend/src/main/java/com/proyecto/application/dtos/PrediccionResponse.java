package com.proyecto.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PrediccionResponse {
    private Integer idProducto;
    private BigDecimal demandaProyectada;
    private Integer horizonteDias;
    private LocalDateTime fechaGeneracion;
    private String estado; // COMPLETADA, SIN_DATOS

    public PrediccionResponse() {
    }

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public BigDecimal getDemandaProyectada() { return demandaProyectada; }

    public void setDemandaProyectada(BigDecimal v) { this.demandaProyectada = v; }

    public Integer getHorizonteDias() { return horizonteDias; }

    public void setHorizonteDias(Integer v) { this.horizonteDias = v; }

    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }

    public void setFechaGeneracion(LocalDateTime v) { this.fechaGeneracion = v; }

    public String getEstado() { return estado; }
    
    public void setEstado(String v) { this.estado = v; }
}
