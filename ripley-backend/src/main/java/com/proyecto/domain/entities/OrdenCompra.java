package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Generada por CU-02 cuando stock < mínimo
 * Java puro — sin frameworks
 *
 * PATRÓN: Builder (construye la orden paso a paso antes de enviarla)
 */
public class OrdenCompra {

    // ── Campos ───────────────────────────────────
    private Integer       idOrdenCompra;
    private Integer       idProducto;
    private Integer       idProveedor;
    private Integer       cantidadPedida;
    private BigDecimal    costoUnitario;
    private BigDecimal    costoTotal;
    private String        estado;           // PENDIENTE|ENVIADA|RECIBIDA|CANCELADA
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaEstimadaEntrega;

    // ── Constructor privado — solo el Builder ────
    private OrdenCompra() {}

    // ════════════════════════════════════════════
    // PATRÓN BUILDER
    // ════════════════════════════════════════════
    public static class Builder {
        private Integer    idProducto;
        private Integer    idProveedor;
        private Integer    cantidadPedida;
        private BigDecimal costoUnitario;
        private LocalDateTime fechaEstimadaEntrega;

        public Builder idProducto(Integer v)                { this.idProducto = v;              return this; }
        public Builder idProveedor(Integer v)               { this.idProveedor = v;             return this; }
        public Builder cantidadPedida(Integer v)            { this.cantidadPedida = v;          return this; }
        public Builder costoUnitario(BigDecimal v)          { this.costoUnitario = v;           return this; }
        public Builder fechaEstimadaEntrega(LocalDateTime v){ this.fechaEstimadaEntrega = v;    return this; }

        public OrdenCompra build() {
            if (idProducto == null)     throw new IllegalArgumentException("idProducto requerido");
            if (idProveedor == null)    throw new IllegalArgumentException("idProveedor requerido");
            if (cantidadPedida == null || cantidadPedida <= 0)
                throw new IllegalArgumentException("cantidadPedida debe ser > 0");
            if (costoUnitario == null)  throw new IllegalArgumentException("costoUnitario requerido");

            OrdenCompra o = new OrdenCompra();
            o.idProducto           = this.idProducto;
            o.idProveedor          = this.idProveedor;
            o.cantidadPedida       = this.cantidadPedida;
            o.costoUnitario        = this.costoUnitario;
            o.costoTotal           = this.costoUnitario.multiply(BigDecimal.valueOf(cantidadPedida));
            o.fechaEstimadaEntrega = this.fechaEstimadaEntrega;
            o.estado               = "PENDIENTE";
            o.fechaCreacion        = LocalDateTime.now();
            return o;
        }
    }

    // ── Lógica de negocio CU-02 ──────────────────
    public void enviar()    { this.estado = "ENVIADA"; }
    public void recibir()   { this.estado = "RECIBIDA"; }
    public void cancelar()  { this.estado = "CANCELADA"; }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdOrdenCompra()                      { return idOrdenCompra; }
    public void          setIdOrdenCompra(Integer v)             { this.idOrdenCompra = v; }
    public Integer       getIdProducto()                         { return idProducto; }
    public void          setIdProducto(Integer v)                { this.idProducto = v; }
    public Integer       getIdProveedor()                        { return idProveedor; }
    public void          setIdProveedor(Integer v)               { this.idProveedor = v; }
    public Integer       getCantidadPedida()                     { return cantidadPedida; }
    public void          setCantidadPedida(Integer v)            { this.cantidadPedida = v; }
    public BigDecimal    getCostoUnitario()                      { return costoUnitario; }
    public void          setCostoUnitario(BigDecimal v)          { this.costoUnitario = v; }
    public BigDecimal    getCostoTotal()                         { return costoTotal; }
    public void          setCostoTotal(BigDecimal v)             { this.costoTotal = v; }
    public String        getEstado()                             { return estado; }
    public void          setEstado(String v)                     { this.estado = v; }
    public LocalDateTime getFechaCreacion()                      { return fechaCreacion; }
    public void          setFechaCreacion(LocalDateTime v)       { this.fechaCreacion = v; }
    public LocalDateTime getFechaEstimadaEntrega()               { return fechaEstimadaEntrega; }
    public void          setFechaEstimadaEntrega(LocalDateTime v){ this.fechaEstimadaEntrega = v; }
}
