package com.proyecto.domain.entities;

import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Tablas BD: entradas_inventario / salidas_inventario
 * Java puro — sin frameworks
 *
 * entradas_inventario: id_entrada, id_producto, cantidad,
 *                      fecha_entrada, id_proveedor
 * salidas_inventario:  id_salida, id_producto, cantidad,
 *                      motivo, fecha_salida
 *
 * PATRÓN: Abstract Factory (EntradaFactory / SalidaFactory
 *         crean el tipo correcto de movimiento)
 */
public class MovimientoInventario {
// implementar interfaz apra los metodos 
    // ── Campos BD ────────────────────────────────
    private Integer       idMovimiento;
    private Integer       idProducto;
    private Integer       cantidad;
    private String        tipoMovimiento; // ENTRADA | SALIDA
    private String        motivo;         // solo salidas — varchar(100)
    private Integer       idProveedor;    // solo entradas — FK proveedores
    private LocalDateTime fecha;

    // ── Constructores ────────────────────────────
    public MovimientoInventario() {}

    private MovimientoInventario(
        Integer idProducto, 
        Integer cantidad,
        String tipo, String motivo,
        Integer idProveedor, 
        LocalDateTime fecha) {
        this.idProducto     = idProducto;
        this.cantidad       = cantidad;
        this.tipoMovimiento = tipo;
        this.motivo         = motivo;
        this.idProveedor    = idProveedor;
        this.fecha          = fecha;
    }

    // ── PATRÓN ABSTRACT FACTORY ───────────────────
    /** Crea un movimiento de ENTRADA (desde proveedor) */
    public static MovimientoInventario entrada(
        Integer idProducto,
        Integer cantidad,
        Integer idProveedor) {
        return new MovimientoInventario(idProducto, cantidad,
                "ENTRADA", null, idProveedor, LocalDateTime.now());
    }

    /** Crea un movimiento de SALIDA (venta o devolución) */
    public static MovimientoInventario salida(
        Integer idProducto,
        Integer cantidad,
        String motivo) {
        return new MovimientoInventario(idProducto, cantidad,
                "SALIDA", motivo, null, LocalDateTime.now());
    }

    // ── Lógica de negocio CU-02 ──────────────────
    public boolean esEntrada() { return "ENTRADA".equalsIgnoreCase(tipoMovimiento); }
    public boolean esSalida()  { return "SALIDA".equalsIgnoreCase(tipoMovimiento); }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdMovimiento()                { return idMovimiento; }
    public void          setIdMovimiento(Integer v)       { this.idMovimiento = v; }
    public Integer       getIdProducto()                  { return idProducto; }
    public void          setIdProducto(Integer v)         { this.idProducto = v; }
    public Integer       getCantidad()                    { return cantidad; }
    public void          setCantidad(Integer v)           { this.cantidad = v; }
    public String        getTipoMovimiento()              { return tipoMovimiento; }
    public void          setTipoMovimiento(String v)      { this.tipoMovimiento = v; }
    public String        getMotivo()                      { return motivo; }
    public void          setMotivo(String v)              { this.motivo = v; }
    public Integer       getIdProveedor()                 { return idProveedor; }
    public void          setIdProveedor(Integer v)        { this.idProveedor = v; }
    public LocalDateTime getFecha()                       { return fecha; }
    public void          setFecha(LocalDateTime v)        { this.fecha = v; }
}
