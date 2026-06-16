package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * Tabla BD: predicciones_ia
 * Java puro — sin frameworks
 *
 * Campos de BD:
 *   id_prediccion     serial PK
 *   id_producto       integer FK → productos
 *   demanda_estimada  integer
 *   fecha_prediccion  date
 *   recomendacion     text
 *   nivel_confianza   numeric(5,2)
 *
 * PATRÓN: Prototype (clonar predicción base para distintos horizontes)
 */
public class PrediccionDemanda implements Cloneable {

    // ── Campos BD ────────────────────────────────
    private Integer       idPrediccion;
    private Integer       idProducto;
    private Integer       demandaEstimada;
    private LocalDate     fechaPrediccion;
    private String        recomendacion;
    private BigDecimal    nivelConfianza;   // numeric(5,2) — 0 a 100
    private LocalDateTime fechaGeneracion; // marca de ejecución

    // ── Constructores ────────────────────────────
    public PrediccionDemanda() {
        this.fechaGeneracion = LocalDateTime.now();
    }

    public PrediccionDemanda(
        Integer idProducto, 
        Integer demandaEstimada,
        LocalDate fechaPrediccion, 
        String recomendacion,
        BigDecimal nivelConfianza) {
        this.idProducto      = idProducto;
        this.demandaEstimada = demandaEstimada;
        this.fechaPrediccion = fechaPrediccion;
        this.recomendacion   = recomendacion;
        this.nivelConfianza  = nivelConfianza;
        this.fechaGeneracion = LocalDateTime.now();
    }

    // ── PATRÓN PROTOTYPE ─────────────────────────
    /** Clona la predicción base para ajustarla a otro horizonte temporal */
    @Override
    public PrediccionDemanda clone() {
        try {
            return (PrediccionDemanda) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar PrediccionDemanda", e);
        }
    }

    // ── Lógica de negocio CU-03 ──────────────────
    /** Confianza aceptable para usar la predicción (>= 70%) */
    public boolean esConfiable() {
        return nivelConfianza != null
            && nivelConfianza.compareTo(new BigDecimal("70.00")) >= 0;
    }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdPrediccion()               { return idPrediccion; }
    public void          setIdPrediccion(Integer v)      { this.idPrediccion = v; }
    public Integer       getIdProducto()                 { return idProducto; }
    public void          setIdProducto(Integer v)        { this.idProducto = v; }
    public Integer       getDemandaEstimada()            { return demandaEstimada; }
    public void          setDemandaEstimada(Integer v)   { this.demandaEstimada = v; }
    public LocalDate     getFechaPrediccion()            { return fechaPrediccion; }
    public void          setFechaPrediccion(LocalDate v) { this.fechaPrediccion = v; }
    public String        getRecomendacion()              { return recomendacion; }
    public void          setRecomendacion(String v)      { this.recomendacion = v; }
    public BigDecimal    getNivelConfianza()              { return nivelConfianza; }
    public void          setNivelConfianza(BigDecimal v) { this.nivelConfianza = v; }
    public LocalDateTime getFechaGeneracion()            { return fechaGeneracion; }
    public void          setFechaGeneracion(LocalDateTime v) { this.fechaGeneracion = v; }
}
