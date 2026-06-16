package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Derivada de predicciones_ia + productos + proveedores
 * Java puro — sin frameworks
 *
 * PATRÓN: Builder (construye la recomendación paso a paso
 *         con validación en build() — CU-04)
 */
public class Recomendacion {

    // ── Campos ───────────────────────────────────
    private Integer       idRecomendacion;
    private Integer       idProducto;
    private String        nombreProducto;
    private Integer       cantidadSugerida;        // EOQ calculado
    private Integer       idProveedorRecomendado;
    private String        nombreProveedor;
    private BigDecimal    costoEstimado;
    private String        fechaLimitePedido;
    private String        estado;                  // PENDIENTE|APROBADA|RECHAZADA
    private LocalDateTime fechaGeneracion;

    // ── Constructor privado — solo el Builder ────
    private Recomendacion() {}

    // ════════════════════════════════════════════
    // PATRÓN BUILDER
    // ════════════════════════════════════════════
    public static class Builder {
        private Integer    idProducto;
        private String     nombreProducto;
        private Integer    cantidadSugerida;
        private Integer    idProveedorRecomendado;
        private String     nombreProveedor;
        private BigDecimal costoEstimado;
        private String     fechaLimitePedido;

        public Builder idProducto(Integer v)              { this.idProducto = v;              return this; }
        public Builder nombreProducto(String v)           { this.nombreProducto = v;           return this; }
        public Builder cantidadSugerida(Integer v)        { this.cantidadSugerida = v;         return this; }
        public Builder idProveedor(Integer v)             { this.idProveedorRecomendado = v;   return this; }
        public Builder nombreProveedor(String v)          { this.nombreProveedor = v;          return this; }
        public Builder costoEstimado(BigDecimal v)        { this.costoEstimado = v;            return this; }
        public Builder fechaLimitePedido(String v)        { this.fechaLimitePedido = v;        return this; }

        public Recomendacion build() {
            if (idProducto == null)
                throw new IllegalArgumentException("idProducto requerido");
            if (cantidadSugerida == null || cantidadSugerida <= 0)
                throw new IllegalArgumentException("Cantidad sugerida debe ser > 0");
            if (idProveedorRecomendado == null)
                throw new IllegalArgumentException("Proveedor requerido");

            Recomendacion r = new Recomendacion();
            r.idProducto             = this.idProducto;
            r.nombreProducto         = this.nombreProducto;
            r.cantidadSugerida       = this.cantidadSugerida;
            r.idProveedorRecomendado = this.idProveedorRecomendado;
            r.nombreProveedor        = this.nombreProveedor;
            r.costoEstimado          = this.costoEstimado;
            r.fechaLimitePedido      = this.fechaLimitePedido;
            r.estado                 = "PENDIENTE";
            r.fechaGeneracion        = LocalDateTime.now();
            return r;
        }
    }

    // ── Lógica de negocio CU-04 ──────────────────
    public void aprobar()           { this.estado = "APROBADA"; }
    public void rechazar()          { this.estado = "RECHAZADA"; }
    public boolean estaPendiente()  { return "PENDIENTE".equals(this.estado); }

    // ── Getters y Setters ────────────────────────
    public Integer    getIdRecomendacion()                   { return idRecomendacion; }
    public void       setIdRecomendacion(Integer v)          { this.idRecomendacion = v; }
    public Integer    getIdProducto()                        { return idProducto; }
    public void       setIdProducto(Integer v)               { this.idProducto = v; }
    public String     getNombreProducto()                    { return nombreProducto; }
    public void       setNombreProducto(String v)            { this.nombreProducto = v; }
    public Integer    getCantidadSugerida()                  { return cantidadSugerida; }
    public void       setCantidadSugerida(Integer v)         { this.cantidadSugerida = v; }
    public Integer    getIdProveedorRecomendado()            { return idProveedorRecomendado; }
    public void       setIdProveedorRecomendado(Integer v)   { this.idProveedorRecomendado = v; }
    public String     getNombreProveedor()                   { return nombreProveedor; }
    public void       setNombreProveedor(String v)           { this.nombreProveedor = v; }
    public BigDecimal getCostoEstimado()                     { return costoEstimado; }
    public void       setCostoEstimado(BigDecimal v)         { this.costoEstimado = v; }
    public String     getFechaLimitePedido()                 { return fechaLimitePedido; }
    public void       setFechaLimitePedido(String v)         { this.fechaLimitePedido = v; }
    public String     getEstado()                            { return estado; }
    public void       setEstado(String v)                    { this.estado = v; }
    public LocalDateTime getFechaGeneracion()                { return fechaGeneracion; }
    public void          setFechaGeneracion(LocalDateTime v) { this.fechaGeneracion = v; }
}
