package com.proyecto.application.dtos;

public class PrediccionRequest {
    private Integer idProducto;
    private Integer horizonteDias; // min 30

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public Integer getHorizonteDias() { return horizonteDias; }
    
    public void setHorizonteDias(Integer v) { this.horizonteDias = v; }
}
