package com.proyecto.application.dtos;

import java.util.List;

public class HistorialResponse {
    private Integer idProducto;
    private String nombreProducto;
    private List<MovimientoTrazabilidadRequest> movimientos;

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public String getNombreProducto() { return nombreProducto; }

    public void setNombreProducto(String v) { this.nombreProducto = v; }

    public List<MovimientoTrazabilidadRequest> getMovimientos() { return movimientos; }
    
    public void setMovimientos(List<MovimientoTrazabilidadRequest> v) { this.movimientos = v; }
}
