package com.proyecto.application.dtos;

public class DevolucionRequest {
    private Integer idProducto;
    private Integer idVentaOriginal;
    private Integer cantidad;
    private String motivo; // DEFECTO, CAMBIO_TALLA, OTRO

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public Integer getIdVentaOriginal() { return idVentaOriginal; }

    public void setIdVentaOriginal(Integer v) { this.idVentaOriginal = v; }

    public Integer getCantidad() { return cantidad; }

    public void setCantidad(Integer v) { this.cantidad = v; }

    public String getMotivo() { return motivo; }

    public void setMotivo(String v) { this.motivo = v; }
}
