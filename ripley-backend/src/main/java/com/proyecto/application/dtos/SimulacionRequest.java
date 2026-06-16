package com.proyecto.application.dtos;

import java.math.BigDecimal;

public class SimulacionRequest {
    private BigDecimal tipoCambio;
    private BigDecimal inflacionProyectada;
    private Integer horizonteDias;

    public BigDecimal getTipoCambio() { return tipoCambio; }

    public void setTipoCambio(BigDecimal v) { this.tipoCambio = v; }

    public BigDecimal getInflacionProyectada() { return inflacionProyectada; }

    public void setInflacionProyectada(BigDecimal v) { this.inflacionProyectada = v; }

    public Integer getHorizonteDias() { return horizonteDias; }
    
    public void setHorizonteDias(Integer v) { this.horizonteDias = v; }
}
