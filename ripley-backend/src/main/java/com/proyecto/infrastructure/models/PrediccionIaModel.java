package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: predicciones_ia
 */
@Entity
@Table(name = "predicciones_ia")
public class PrediccionIaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prediccion")
    private Integer idPrediccion;

    // N:1 con productos
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "demanda_estimada", nullable = false)
    private Integer demandaEstimada;

    @Column(name = "fecha_prediccion", nullable = false)
    private LocalDate fechaPrediccion;

    @Column(name = "recomendacion", columnDefinition = "text")
    private String recomendacion;

    @Column(name = "nivel_confianza", precision = 5, scale = 2)
    private BigDecimal nivelConfianza;

    // ── Constructores ─────────────────────────
    public PrediccionIaModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer       getIdPrediccion()                    { return idPrediccion; }
    public void          setIdPrediccion(Integer v)           { this.idPrediccion = v; }
    public ProductoModel getProducto()                        { return producto; }
    public void          setProducto(ProductoModel v)         { this.producto = v; }
    public Integer       getDemandaEstimada()                 { return demandaEstimada; }
    public void          setDemandaEstimada(Integer v)        { this.demandaEstimada = v; }
    public LocalDate     getFechaPrediccion()                 { return fechaPrediccion; }
    public void          setFechaPrediccion(LocalDate v)      { this.fechaPrediccion = v; }
    public String        getRecomendacion()                   { return recomendacion; }
    public void          setRecomendacion(String v)           { this.recomendacion = v; }
    public BigDecimal    getNivelConfianza()                  { return nivelConfianza; }
    public void          setNivelConfianza(BigDecimal v)      { this.nivelConfianza = v; }
}
