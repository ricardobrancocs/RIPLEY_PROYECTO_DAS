package com.proyecto.application.dtos;

import java.math.BigDecimal;

public class RecomendacionResponse {
    private Integer id;
    private Integer idProducto;
    private String nombreProducto;
    private Integer cantidadSugerida;
    private Integer idProveedorRecomendado;
    private String  nombreProveedor;
    private BigDecimal costoEstimado;
    private String fechaLimitePedido;
    private String estado; // PENDIENTE, APROBADA

    public Integer getId() { return id; }

    public void setId(Integer v) { this.id = v; }

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public String getNombreProducto() { return nombreProducto; }

    public void setNombreProducto(String v) { this.nombreProducto = v; }

    public Integer getCantidadSugerida() { return cantidadSugerida; }

    public void setCantidadSugerida(Integer v) { this.cantidadSugerida = v; }

    public Integer getIdProveedorRecomendado() { return idProveedorRecomendado; }

    public void setIdProveedorRecomendado(Integer v) { this.idProveedorRecomendado = v; }

    public String getNombreProveedor() { return nombreProveedor; }

    public void setNombreProveedor(String v) { this.nombreProveedor = v; }

    public BigDecimal getCostoEstimado() { return costoEstimado; }

    public void setCostoEstimado(BigDecimal v) { this.costoEstimado = v; }

    public String getFechaLimitePedido() { return fechaLimitePedido; }

    public void setFechaLimitePedido(String v) { this.fechaLimitePedido = v; }

    public String getEstado() { return estado; }

    public void setEstado(String v) { this.estado = v; }
}
