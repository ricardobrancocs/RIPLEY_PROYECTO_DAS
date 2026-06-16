package com.proyecto.application.dtos;

import java.time.LocalDateTime;

public class ClasificacionResponse {
    private Integer idProducto;
    private String nombre;
    private String categoria; // A, B, C
    private Double porcentajeVentas;
    private LocalDateTime fechaClasificacion;

    public ClasificacionResponse() {}

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public String getNombre() { return nombre; }

    public void setNombre(String v) { this.nombre = v; }

    public String getCategoria() { return categoria; }

    public void setCategoria(String v) { this.categoria = v; }

    public Double getPorcentajeVentas() { return porcentajeVentas; }

    public void setPorcentajeVentas(Double v) { this.porcentajeVentas = v; }

    public LocalDateTime getFechaClasificacion() { return fechaClasificacion; }

    public void setFechaClasificacion(LocalDateTime v) { this.fechaClasificacion = v; }
}
