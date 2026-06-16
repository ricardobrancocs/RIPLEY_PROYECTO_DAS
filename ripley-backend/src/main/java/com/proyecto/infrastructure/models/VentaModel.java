package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Mapea la entidad Venta a la tabla "ventas" de PostgreSQL
 * Aquí sí usamos anotaciones de Hibernate/JPA
 */
@Entity
@Table(name = "ventas")
public class VentaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    @Column(name = "fecha_venta", nullable = false)
    private LocalDateTime fechaVenta;

    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    // Relación con detalle_ventas
    @OneToMany(mappedBy = "venta", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DetalleVentaModel> detalles;

    // ═══════════════════════════════════════
    // Constructor vacío requerido por JPA
    // ═══════════════════════════════════════
    public VentaModel() {}

    // ═══════════════════════════════════════
    // Constructor completo
    // ═══════════════════════════════════════
    public VentaModel(LocalDateTime fechaVenta, BigDecimal total,
        String estado, Integer idUsuario) {
        this.fechaVenta = fechaVenta;
        this.total      = total;
        this.estado     = estado;
        this.idUsuario  = idUsuario;
    }

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdVenta()                  { return idVenta; }
    public void setIdVenta(Integer idVenta)      { this.idVenta = idVenta; }

    public LocalDateTime getFechaVenta()                     { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta)      { this.fechaVenta = fechaVenta; }

    public BigDecimal getTotal()                 { return total; }
    public void setTotal(BigDecimal total)       { this.total = total; }

    public String getEstado()                    { return estado; }
    public void setEstado(String estado)         { this.estado = estado; }

    public Integer getIdUsuario()                { return idUsuario; }
    public void setIdUsuario(Integer idUsuario)  { this.idUsuario = idUsuario; }

    public List<DetalleVentaModel> getDetalles()                 { return detalles; }
    public void setDetalles(List<DetalleVentaModel> detalles)    { this.detalles = detalles; }
}