package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: trazabilidad_movimientos
 * CU-09 Trazabilidad de Productos
 * INMUTABLE: solo INSERT, nunca UPDATE ni DELETE
 */
@Entity
@Table(name = "trazabilidad_movimientos")
public class TrazabilidadModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "ubicacion", nullable = false, length = 150)
    private String ubicacion;

    @Column(name = "responsable", nullable = false, length = 100)
    private String responsable;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    public TrazabilidadModel() {}

    public Integer       getIdMovimiento()                           { return idMovimiento; }
    public void          setIdMovimiento(Integer v)                  { this.idMovimiento = v; }
    public ProductoModel getProducto()                               { return producto; }
    public void          setProducto(ProductoModel v)                { this.producto = v; }
    public String        getUbicacion()                              { return ubicacion; }
    public void          setUbicacion(String v)                      { this.ubicacion = v; }
    public String        getResponsable()                            { return responsable; }
    public void          setResponsable(String v)                    { this.responsable = v; }
    public LocalDateTime getFecha()                                  { return fecha; }
    public void          setFecha(LocalDateTime v)                   { this.fecha = v; }
    public String        getDescripcion()                            { return descripcion; }
    public void          setDescripcion(String v)                    { this.descripcion = v; }
}
