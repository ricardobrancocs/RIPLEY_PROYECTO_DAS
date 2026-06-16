package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: simulaciones
 * CU-11 Simulación de Escenarios Financieros
 */
@Entity
@Table(name = "simulaciones")
public class SimulacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_simulacion")
    private Integer idSimulacion;

    @Column(name = "tipo_cambio", nullable = false, precision = 10, scale = 4)
    private BigDecimal tipoCambio;

    @Column(name = "inflacion_proyectada", precision = 5, scale = 4)
    private BigDecimal inflacionProyectada;

    @Column(name = "horizonte_dias", nullable = false)
    private Integer horizonteDias;

    @Column(name = "nombre_escenario", nullable = false, length = 10)
    private String nombreEscenario;   // ALTO | MEDIO | BAJO | BASE

    @Column(name = "factor_ajuste", nullable = false, precision = 5, scale = 2)
    private BigDecimal factorAjuste;

    @Column(name = "costo_total", precision = 15, scale = 2)
    private BigDecimal costoTotal;

    @Column(name = "impacto_financiero", precision = 15, scale = 2)
    private BigDecimal impactoFinanciero;

    @Column(name = "usuario_solicitante", length = 100)
    private String usuarioSolicitante;

    @Column(name = "fecha_ejecucion", nullable = false)
    private LocalDateTime fechaEjecucion;

    public SimulacionModel() {}

    public Integer       getIdSimulacion()                           { return idSimulacion; }
    public void          setIdSimulacion(Integer v)                  { this.idSimulacion = v; }
    public BigDecimal    getTipoCambio()                             { return tipoCambio; }
    public void          setTipoCambio(BigDecimal v)                 { this.tipoCambio = v; }
    public BigDecimal    getInflacionProyectada()                    { return inflacionProyectada; }
    public void          setInflacionProyectada(BigDecimal v)        { this.inflacionProyectada = v; }
    public Integer       getHorizonteDias()                          { return horizonteDias; }
    public void          setHorizonteDias(Integer v)                 { this.horizonteDias = v; }
    public String        getNombreEscenario()                        { return nombreEscenario; }
    public void          setNombreEscenario(String v)                { this.nombreEscenario = v; }
    public BigDecimal    getFactorAjuste()                           { return factorAjuste; }
    public void          setFactorAjuste(BigDecimal v)               { this.factorAjuste = v; }
    public BigDecimal    getCostoTotal()                             { return costoTotal; }
    public void          setCostoTotal(BigDecimal v)                 { this.costoTotal = v; }
    public BigDecimal    getImpactoFinanciero()                      { return impactoFinanciero; }
    public void          setImpactoFinanciero(BigDecimal v)          { this.impactoFinanciero = v; }
    public String        getUsuarioSolicitante()                     { return usuarioSolicitante; }
    public void          setUsuarioSolicitante(String v)             { this.usuarioSolicitante = v; }
    public LocalDateTime getFechaEjecucion()                         { return fechaEjecucion; }
    public void          setFechaEjecucion(LocalDateTime v)          { this.fechaEjecucion = v; }
}
