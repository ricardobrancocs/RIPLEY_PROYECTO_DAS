package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Tabla BD: productos
 * Java puro — sin frameworks
 *
 * PATRÓN: Prototype (clonar producto base para variantes)
 */
public class Producto implements Cloneable {

    // ── Campos BD ────────────────────────────────
    private Integer       idProducto;
    private String        nombre;
    private String        descripcion;
    private String        codigoBarras;
    private BigDecimal    precio;
    private Integer       stockActual;
    private Integer       stockMinimo;
    private Integer       idCategoria;
    private LocalDateTime fechaRegistro;

    // ── Constructores ────────────────────────────
    public Producto() {}

    public Producto(
        Integer idProducto, 
        String nombre, 
        String descripcion,
        String codigoBarras, 
        BigDecimal precio,
        Integer stockActual, 
        Integer stockMinimo,
        Integer idCategoria, 
        LocalDateTime fechaRegistro) {
        this.idProducto    = idProducto;
        this.nombre        = nombre;
        this.descripcion   = descripcion;
        this.codigoBarras  = codigoBarras;
        this.precio        = precio;
        this.stockActual   = stockActual;
        this.stockMinimo   = stockMinimo;
        this.idCategoria   = idCategoria;
        this.fechaRegistro = fechaRegistro;
    }

    // ── PATRÓN PROTOTYPE ─────────────────────────
    @Override
    public Producto clone() {
        try {
            return (Producto) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Producto", e);
        }
    }

    // ── Lógica de negocio ────────────────────────

    /** CU-01 Paso 3: ¿hay stock suficiente? */
    public boolean tieneStockSuficiente(int cantidad) {
        return this.stockActual != null && this.stockActual >= cantidad;
    }

    /** CU-01 Subflujo P-3: descuenta stock tras pago */
    public void reducirStock(int cantidad) {
        if (!tieneStockSuficiente(cantidad))
            throw new RuntimeException("Stock insuficiente para: " + this.nombre);
        this.stockActual -= cantidad;
    }

    /** CU-08 Devoluciones: reingresa unidades al stock */
    public void reponerStock(int cantidad) {
        this.stockActual += cantidad;
    }

    /** CU-02: stock por debajo del mínimo → genera orden de compra */
    public boolean estaBajoStockMinimo() {
        return stockActual != null && stockMinimo != null && stockActual < stockMinimo;
    }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdProducto()                          { return idProducto; }
    public void          setIdProducto(Integer v)                 { this.idProducto = v; }
    public String        getNombre()                              { return nombre; }
    public void          setNombre(String v)                      { this.nombre = v; }
    public String        getDescripcion()                         { return descripcion; }
    public void          setDescripcion(String v)                 { this.descripcion = v; }
    public String        getCodigoBarras()                        { return codigoBarras; }
    public void          setCodigoBarras(String v)                { this.codigoBarras = v; }
    public BigDecimal    getPrecio()                              { return precio; }
    public void          setPrecio(BigDecimal v)                  { this.precio = v; }
    public Integer       getStockActual()                         { return stockActual; }
    public void          setStockActual(Integer v)                { this.stockActual = v; }
    public Integer       getStockMinimo()                         { return stockMinimo; }
    public void          setStockMinimo(Integer v)                { this.stockMinimo = v; }
    public Integer       getIdCategoria()                         { return idCategoria; }
    public void          setIdCategoria(Integer v)                { this.idCategoria = v; }
    public LocalDateTime getFechaRegistro()                       { return fechaRegistro; }
    public void          setFechaRegistro(LocalDateTime v)        { this.fechaRegistro = v; }
}
