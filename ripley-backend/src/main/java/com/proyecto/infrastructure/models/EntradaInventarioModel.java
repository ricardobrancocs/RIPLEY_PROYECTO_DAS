package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: entradas_inventario
 */
@Entity
@Table(name = "entradas_inventario")
public class EntradaInventarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada")
    private Integer idEntrada;

    // N:1 con productos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "fecha_entrada", nullable = false)
    private LocalDateTime fechaEntrada;

    // N:1 con proveedores
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor")
    private ProveedorModel proveedor;

    // ── Constructores ─────────────────────────
    public EntradaInventarioModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer        getIdEntrada()                     { return idEntrada; }
    public void           setIdEntrada(Integer v)            { this.idEntrada = v; }
    public ProductoModel  getProducto()                      { return producto; }
    public void           setProducto(ProductoModel v)       { this.producto = v; }
    public Integer        getCantidad()                      { return cantidad; }
    public void           setCantidad(Integer v)             { this.cantidad = v; }
    public LocalDateTime  getFechaEntrada()                  { return fechaEntrada; }
    public void           setFechaEntrada(LocalDateTime v)   { this.fechaEntrada = v; }
    public ProveedorModel getProveedor()                     { return proveedor; }
    public void           setProveedor(ProveedorModel v)     { this.proveedor = v; }
}
