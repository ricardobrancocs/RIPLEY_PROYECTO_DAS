package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.ClasificacionAbc;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * CU-10 Clasificación ABC de Productos
 */
public interface IClasificacionAbcRepository {

    /** Guarda o actualiza la clasificación de un producto */
    ClasificacionAbc guardar(ClasificacionAbc clasificacion);

    /** Obtiene la clasificación actual de un producto */
    Optional<ClasificacionAbc> buscarPorProducto(Integer idProducto);

    /** Lista todos los productos clasificados */
    List<ClasificacionAbc> listarTodas();

    /** Lista productos por categoría (A, B o C) */
    List<ClasificacionAbc> listarPorCategoria(String categoria);

    /**
     * Guarda masivamente las clasificaciones de todo el catálogo
     * CU-10 Paso 5: guardar resultado del análisis completo
     */
    void guardarTodas(List<ClasificacionAbc> clasificaciones);
}
