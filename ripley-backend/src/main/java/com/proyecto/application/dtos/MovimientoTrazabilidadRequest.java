package com.proyecto.application.dtos;
import java.time.LocalDateTime;

public class MovimientoTrazabilidadRequest {
    private Integer idProducto;
    private String ubicacion;
    private String responsable;
    private LocalDateTime fecha;
    private String descripcion;

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public String getUbicacion() { return ubicacion; }

    public void setUbicacion(String v) { this.ubicacion = v; }

    public String getResponsable() { return responsable; }

    public void setResponsable(String v) { this.responsable = v; }

    public LocalDateTime getFecha() { return fecha; }

    public void setFecha(LocalDateTime v) { this.fecha = v; }

    public String getDescripcion() { return descripcion; }
    
    public void setDescripcion(String v) { this.descripcion = v; }
}
