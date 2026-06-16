package com.proyecto.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * CU-11 Simulación de Escenarios Financieros
 * Java puro — sin frameworks
 *
 * PATRONES:
 *   Builder   — construye la simulación con variables opcionales
 *   Prototype — clona el escenario base para generar Alto/Medio/Bajo
 */
public class Simulacion implements Cloneable {

    // ── Campos ───────────────────────────────────
    private Integer       idSimulacion;
    private BigDecimal    tipoCambio;
    private BigDecimal    inflacionProyectada;
    private Integer       horizonteDias;
    private String        nombreEscenario;     // ALTO | MEDIO | BAJO
    private BigDecimal    factorAjuste;        // 1.25 | 1.00 | 0.75
    private BigDecimal    costoTotal;
    private BigDecimal    impactoFinanciero;
    private String        usuarioSolicitante;
    private LocalDateTime fechaEjecucion;

    // ── Constructor privado — solo Builder/clone ──
    private Simulacion() {}

    // ════════════════════════════════════════════
    // PATRÓN BUILDER — construye el escenario base
    // ════════════════════════════════════════════
    public static class Builder {
        private BigDecimal tipoCambio;
        private BigDecimal inflacionProyectada = BigDecimal.ZERO;
        private Integer    horizonteDias       = 30;
        private String     usuarioSolicitante;

        public Builder tipoCambio(BigDecimal v)          { this.tipoCambio = v;              return this; }
        public Builder inflacion(BigDecimal v)            { this.inflacionProyectada = v;     return this; }
        public Builder horizonte(Integer v)               { this.horizonteDias = v;           return this; }
        public Builder usuario(String v)                  { this.usuarioSolicitante = v;      return this; }

        public Simulacion build() {
            if (tipoCambio == null || tipoCambio.compareTo(BigDecimal.ZERO) <= 0)
                throw new IllegalArgumentException("Tipo de cambio inválido — debe ser > 0");
            if (usuarioSolicitante == null || usuarioSolicitante.isBlank())
                throw new IllegalArgumentException("Usuario solicitante requerido");

            Simulacion s = new Simulacion();
            s.tipoCambio           = this.tipoCambio;
            s.inflacionProyectada  = this.inflacionProyectada;
            s.horizonteDias        = this.horizonteDias;
            s.usuarioSolicitante   = this.usuarioSolicitante;
            s.nombreEscenario      = "BASE";
            s.factorAjuste         = BigDecimal.ONE;
            s.fechaEjecucion       = LocalDateTime.now();
            return s;
        }
    }

    // ════════════════════════════════════════════
    // PATRÓN PROTOTYPE — genera los 3 escenarios
    // ════════════════════════════════════════════
    @Override
    public Simulacion clone() {
        try {
            return (Simulacion) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Simulacion", e);
        }
    }

    /** Genera escenario ALTO (optimista: +25%) a partir del base */
    public Simulacion escenarioAlto() {
        Simulacion clon      = this.clone();
        clon.nombreEscenario = "ALTO";
        clon.factorAjuste    = new BigDecimal("1.25");
        clon.idSimulacion    = null;
        return clon;
    }

    /** Genera escenario MEDIO (neutro: 0%) a partir del base */
    public Simulacion escenarioMedio() {
        Simulacion clon      = this.clone();
        clon.nombreEscenario = "MEDIO";
        clon.factorAjuste    = BigDecimal.ONE;
        clon.idSimulacion    = null;
        return clon;
    }

    /** Genera escenario BAJO (pesimista: -25%) a partir del base */
    public Simulacion escenarioBajo() {
        Simulacion clon      = this.clone();
        clon.nombreEscenario = "BAJO";
        clon.factorAjuste    = new BigDecimal("0.75");
        clon.idSimulacion    = null;
        return clon;
    }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdSimulacion()                         { return idSimulacion; }
    public void          setIdSimulacion(Integer v)                { this.idSimulacion = v; }
    public BigDecimal    getTipoCambio()                           { return tipoCambio; }
    public void          setTipoCambio(BigDecimal v)               { this.tipoCambio = v; }
    public BigDecimal    getInflacionProyectada()                  { return inflacionProyectada; }
    public void          setInflacionProyectada(BigDecimal v)      { this.inflacionProyectada = v; }
    public Integer       getHorizonteDias()                        { return horizonteDias; }
    public void          setHorizonteDias(Integer v)               { this.horizonteDias = v; }
    public String        getNombreEscenario()                      { return nombreEscenario; }
    public void          setNombreEscenario(String v)              { this.nombreEscenario = v; }
    public BigDecimal    getFactorAjuste()                         { return factorAjuste; }
    public void          setFactorAjuste(BigDecimal v)             { this.factorAjuste = v; }
    public BigDecimal    getCostoTotal()                           { return costoTotal; }
    public void          setCostoTotal(BigDecimal v)               { this.costoTotal = v; }
    public BigDecimal    getImpactoFinanciero()                    { return impactoFinanciero; }
    public void          setImpactoFinanciero(BigDecimal v)        { this.impactoFinanciero = v; }
    public String        getUsuarioSolicitante()                   { return usuarioSolicitante; }
    public void          setUsuarioSolicitante(String v)           { this.usuarioSolicitante = v; }
    public LocalDateTime getFechaEjecucion()                       { return fechaEjecucion; }
    public void          setFechaEjecucion(LocalDateTime v)        { this.fechaEjecucion = v; }
}
