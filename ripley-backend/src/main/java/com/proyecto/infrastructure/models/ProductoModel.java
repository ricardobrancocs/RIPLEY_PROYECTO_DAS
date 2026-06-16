package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: productos
 */
@Entity
@Table(name = "productos")
public class ProductoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @Column(name = "codigo_barras", length = 50)
    private String codigoBarras;

    @Column(name = "precio", nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "stock_actual", nullable = false)
    private Integer stockActual;

    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;

    // N:1 con categorias
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaModel categoria;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    // ── Constructores ─────────────────────────
    public ProductoModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer        getIdProducto()                    { return idProducto; }
    public void           setIdProducto(Integer v)           { this.idProducto = v; }
    public String         getNombre()                        { return nombre; }
    public void           setNombre(String v)                { this.nombre = v; }
    public String         getDescripcion()                   { return descripcion; }
    public void           setDescripcion(String v)           { this.descripcion = v; }
    public String         getCodigoBarras()                  { return codigoBarras; }
    public void           setCodigoBarras(String v)          { this.codigoBarras = v; }
    public BigDecimal     getPrecio()                        { return precio; }
    public void           setPrecio(BigDecimal v)            { this.precio = v; }
    public Integer        getStockActual()                   { return stockActual; }
    public void           setStockActual(Integer v)          { this.stockActual = v; }
    public Integer        getStockMinimo()                   { return stockMinimo; }
    public void           setStockMinimo(Integer v)          { this.stockMinimo = v; }
    public CategoriaModel getCategoria()                     { return categoria; }
    public void           setCategoria(CategoriaModel v)     { this.categoria = v; }
    public LocalDateTime  getFechaRegistro()                 { return fechaRegistro; }
    public void           setFechaRegistro(LocalDateTime v)  { this.fechaRegistro = v; }
}
