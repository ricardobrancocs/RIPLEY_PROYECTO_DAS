package com.proyecto.domain.entities;

import java.time.LocalDateTime;

/**
 * CAPA: DOMINIO — Entidades
 * CU-10 Clasificación ABC de Productos
 * Java puro — sin frameworks
 *
 * PATRÓN: Singleton (instancia única del motor de clasificación
 *         que mantiene los umbrales A/B/C configurados)
 */
public class ClasificacionAbc {

    // ── Campos ───────────────────────────────────
    private Integer       idClasificacion;
    private Integer       idProducto;
    private String        nombreProducto;
    private Double        porcentajeVentas; // % acumulado sobre total ventas
    private String        categoria;        // A | B | C
    private LocalDateTime fechaClasificacion;

    // ── PATRÓN SINGLETON — umbrales configurables ──
    private static ClasificacionAbc instancia;
    private double umbralA = 80.0; // top 80% del valor → categoría A
    private double umbralB = 95.0; // siguiente 15%    → categoría B
                                   // resto             → categoría C

    private ClasificacionAbc() {}

    /** Devuelve la única instancia del motor de clasificación */
    public static synchronized ClasificacionAbc getInstance() {
        if (instancia == null)
            instancia = new ClasificacionAbc();
        return instancia;
    }

    /** Actualiza los umbrales desde configuración del administrador */
    public void configurarUmbrales(double umbralA, double umbralB) {
        if (umbralA >= umbralB)
            throw new IllegalArgumentException("umbralA debe ser menor que umbralB");
        this.umbralA = umbralA;
        this.umbralB = umbralB;
    }

    // ── Lógica de negocio CU-10 ──────────────────
    /** Determina la categoría según el porcentaje acumulado de ventas */
    public String determinarCategoria(double porcentajeAcumulado) {
        if (porcentajeAcumulado <= umbralA) return "A";
        if (porcentajeAcumulado <= umbralB) return "B";
        return "C";
    }

    // ── Constructores para registros individuales ─
    public static ClasificacionAbc registro(
        Integer idProducto, 
        String nombre,
        Double porcentaje, 
        String categoria) {
        ClasificacionAbc c = new ClasificacionAbc();
        c.idProducto         = idProducto;
        c.nombreProducto     = nombre;
        c.porcentajeVentas   = porcentaje;
        c.categoria          = categoria;
        c.fechaClasificacion = LocalDateTime.now();
        return c;
    }

    // ── Getters y Setters ────────────────────────
    public Integer       getIdClasificacion()                      { return idClasificacion; }
    public void          setIdClasificacion(Integer v)             { this.idClasificacion = v; }
    public Integer       getIdProducto()                           { return idProducto; }
    public void          setIdProducto(Integer v)                  { this.idProducto = v; }
    public String        getNombreProducto()                       { return nombreProducto; }
    public void          setNombreProducto(String v)               { this.nombreProducto = v; }
    public Double        getPorcentajeVentas()                     { return porcentajeVentas; }
    public void          setPorcentajeVentas(Double v)             { this.porcentajeVentas = v; }
    public String        getCategoria()                            { return categoria; }
    public void          setCategoria(String v)                    { this.categoria = v; }
    public LocalDateTime getFechaClasificacion()                   { return fechaClasificacion; }
    public void          setFechaClasificacion(LocalDateTime v)    { this.fechaClasificacion = v; }
    public double        getUmbralA()                              { return umbralA; }
    public double        getUmbralB()                              { return umbralB; }
}
