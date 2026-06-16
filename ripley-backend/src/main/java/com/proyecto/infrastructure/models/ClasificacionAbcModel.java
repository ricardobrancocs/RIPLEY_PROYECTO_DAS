package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: clasificacion_abc
 * CU-10 Clasificación ABC de Productos
 */
@Entity
@Table(name = "clasificacion_abc")
public class ClasificacionAbcModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion")
    private Integer idClasificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "nombre_producto", length = 150)
    private String nombreProducto;

    @Column(name = "porcentaje_ventas")
    private Double porcentajeVentas;

    @Column(name = "categoria", nullable = false, length = 1)
    private String categoria;   // A | B | C

    @Column(name = "fecha_clasificacion", nullable = false)
    private LocalDateTime fechaClasificacion;

    public ClasificacionAbcModel() {}

    public Integer       getIdClasificacion()                        { return idClasificacion; }
    public void          setIdClasificacion(Integer v)               { this.idClasificacion = v; }
    public ProductoModel getProducto()                               { return producto; }
    public void          setProducto(ProductoModel v)                { this.producto = v; }
    public String        getNombreProducto()                         { return nombreProducto; }
    public void          setNombreProducto(String v)                 { this.nombreProducto = v; }
    public Double        getPorcentajeVentas()                       { return porcentajeVentas; }
    public void          setPorcentajeVentas(Double v)               { this.porcentajeVentas = v; }
    public String        getCategoria()                              { return categoria; }
    public void          setCategoria(String v)                      { this.categoria = v; }
    public LocalDateTime getFechaClasificacion()                     { return fechaClasificacion; }
    public void          setFechaClasificacion(LocalDateTime v)      { this.fechaClasificacion = v; }
}
