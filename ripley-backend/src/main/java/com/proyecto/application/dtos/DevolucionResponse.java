package com.proyecto.application.dtos;

import java.time.LocalDateTime;

public class DevolucionResponse {
    private Integer idDevolucion;
    private Integer idProducto;
    private String motivo;
    private String estado; // REGISTRADA, ERROR
    private LocalDateTime fecha;
    private String mensaje;

    public DevolucionResponse() {
    }

    public Integer getIdDevolucion() { return idDevolucion; }

    public void setIdDevolucion(Integer v) { this.idDevolucion = v; }

    public Integer getIdProducto() { return idProducto; }

    public void setIdProducto(Integer v) { this.idProducto = v; }

    public String getMotivo() { return motivo; }

    public void setMotivo(String v) { this.motivo = v; }

    public String getEstado() { return estado; }

    public void setEstado(String v) { this.estado = v; }

    public LocalDateTime getFecha() { return fecha; }

    public void setFecha(LocalDateTime v) { this.fecha = v; }

    public String getMensaje() { return mensaje; }

    public void setMensaje(String v) { this.mensaje = v; }
}
