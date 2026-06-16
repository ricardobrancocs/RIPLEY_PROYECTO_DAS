package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: alertas_stock
 */
@Entity
@Table(name = "alertas_stock")
public class AlertaStockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alerta")
    private Integer idAlerta;

    // N:1 con productos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "tipo_alerta", nullable = false, length = 100)
    private String tipoAlerta;   // CRITICA | ADVERTENCIA | INFORMATIVA

    @Column(name = "mensaje", columnDefinition = "text")
    private String mensaje;

    @Column(name = "fecha_alerta", nullable = false)
    private LocalDateTime fechaAlerta;

    @Column(name = "estado", length = 1)
    private String estado;       // A=activa | T=atendida | D=descartada

    // ── Constructores ─────────────────────────
    public AlertaStockModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer       getIdAlerta()                   { return idAlerta; }
    public void          setIdAlerta(Integer v)          { this.idAlerta = v; }
    public ProductoModel getProducto()                   { return producto; }
    public void          setProducto(ProductoModel v)    { this.producto = v; }
    public String        getTipoAlerta()                 { return tipoAlerta; }
    public void          setTipoAlerta(String v)         { this.tipoAlerta = v; }
    public String        getMensaje()                    { return mensaje; }
    public void          setMensaje(String v)            { this.mensaje = v; }
    public LocalDateTime getFechaAlerta()                { return fechaAlerta; }
    public void          setFechaAlerta(LocalDateTime v) { this.fechaAlerta = v; }
    public String        getEstado()                     { return estado; }
    public void          setEstado(String v)             { this.estado = v; }
}
