package com.proyecto.application.dtos;

public class StockResponse {
    private Integer idProducto;
    private String nombre;
    private Integer stockActual;
    private Integer stockMinimo;
    private String estado; // NORMAL, BAJO, CRITICO

    public StockResponse() {
    }

    public StockResponse(Integer idProducto, String nombre, Integer stockActual, Integer stockMinimo, String estado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
    }

    public Integer getIdProducto() {return idProducto;}

    public void setIdProducto(Integer v) {this.idProducto = v;}
    
    public String getNombre() {return nombre;}

    public void setNombre(String v) {this.nombre = v;}

    public Integer getStockActual() {return stockActual;}

    public void setStockActual(Integer v) {this.stockActual = v;}

    public Integer getStockMinimo() {return stockMinimo;}

    public void setStockMinimo(Integer v) {this.stockMinimo = v;}

    public String getEstado() {return estado;}

    public void setEstado(String v) {this.estado = v;}
}
