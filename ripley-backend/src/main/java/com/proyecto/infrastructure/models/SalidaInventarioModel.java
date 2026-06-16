package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: salidas_inventario
 */
@Entity
@Table(name = "salidas_inventario")
public class SalidaInventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_salida")
    private Integer idSalida;

    // N:1 con productos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "motivo", length = 100)
    private String motivo;

    @Column(name = "fecha_salida", nullable = false)
    private LocalDateTime fechaSalida;

    // ── Constructores ─────────────────────────
    public SalidaInventarioModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer       getIdSalida()                   { return idSalida; }
    public void          setIdSalida(Integer v)          { this.idSalida = v; }
    public ProductoModel getProducto()                   { return producto; }
    public void          setProducto(ProductoModel v)    { this.producto = v; }
    public Integer       getCantidad()                   { return cantidad; }
    public void          setCantidad(Integer v)          { this.cantidad = v; }
    public String        getMotivo()                     { return motivo; }
    public void          setMotivo(String v)             { this.motivo = v; }
    public LocalDateTime getFechaSalida()                { return fechaSalida; }
    public void          setFechaSalida(LocalDateTime v) { this.fechaSalida = v; }
}
