package com.proyecto.application.dtos;

import java.time.LocalDateTime;

public class AlertaResponse {
    private Integer id;
    private Integer idProducto;
    private String nombreProducto;
    private String nivel; // INFORMATIVA, ADVERTENCIA, CRITICA
    private String mensaje;
    private String estado; // ACTIVA, ATENDIDA, DESCARTADA
    private LocalDateTime fechaGeneracion;

    public AlertaResponse() {
    }

    public Integer getId() {return id;}

    public void setId(Integer v) {this.id = v;}

    public Integer getIdProducto() {return idProducto;}

    public void setIdProducto(Integer v) {this.idProducto = v;}
    
    public String getNombreProducto() {return nombreProducto;}

    public void setNombreProducto(String v) {this.nombreProducto = v;}

    public String getNivel() {return nivel;}

    public void setNivel(String v) {this.nivel = v;}

    public String getMensaje() {return mensaje;}

    public void setMensaje(String v) {this.mensaje = v;}

    public String getEstado() {return estado;}

    public void setEstado(String v) {this.estado = v;}

    public LocalDateTime getFechaGeneracion() {return fechaGeneracion;}

    public void setFechaGeneracion(LocalDateTime v) {this.fechaGeneracion = v;}
}
