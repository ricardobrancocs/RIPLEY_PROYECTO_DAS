package com.proyecto.application.dtos;

import java.util.List;

/**
 * CAPA: APLICACION — DTOs
 * Data Transfer Object para crear/modificar una proforma
 * Recibe los datos del request HTTP desde el Controller
 */
public class CrearProformaDTO {

    private Integer idUsuario;
    private List<DetalleDTO> detalles;

    // ═══════════════════════════════════════
    // Clase interna para cada producto
    // ═══════════════════════════════════════
    public static class DetalleDTO {
        private Integer idProducto;
        private Integer cantidad;

        public DetalleDTO() {}

        public Integer getIdProducto()                   { return idProducto; }
        public void setIdProducto(Integer idProducto)    { this.idProducto = idProducto; }

        public Integer getCantidad()                     { return cantidad; }
        public void setCantidad(Integer cantidad)        { this.cantidad = cantidad; }
    }

    // ═══════════════════════════════════════
    // Constructor vacío
    // ═══════════════════════════════════════
    public CrearProformaDTO() {}

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdUsuario()                { return idUsuario; }
    public void setIdUsuario(Integer idUsuario)  { this.idUsuario = idUsuario; }

    public List<DetalleDTO> getDetalles()                { return detalles; }
    public void setDetalles(List<DetalleDTO> detalles)   { this.detalles = detalles; }
}