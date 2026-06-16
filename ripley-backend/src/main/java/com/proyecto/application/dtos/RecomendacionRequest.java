package com.proyecto.application.dtos;

public class RecomendacionRequest {
    private Integer idProducto;
    private Integer idProveedor;
    private Integer cantidadAjustada;
    private String  justificacion;
    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public Integer getIdProveedor() { return idProveedor; }

    public void setIdProveedor(Integer v) { this.idProveedor = v; }

    public Integer getCantidadAjustada() { return cantidadAjustada; }

    public void setCantidadAjustada(Integer v) { this.cantidadAjustada = v; }
    
    public String getJustificacion() { return justificacion; }

    public void setJustificacion(String v) { this.justificacion = v; }
}
