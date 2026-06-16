package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.ClasificacionAbc;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * ClasificacionAbc no tiene tabla propia en BD actual.
 * La clasificación se calcula en memoria y se retorna como DTO.
 * Este mapper construye registros individuales de clasificación.
 */
public class ClasificacionAbcMapper {

    public static ClasificacionAbc toDomain(Integer idProducto, String nombre,
                                             Double porcentaje, String categoria) {
        return ClasificacionAbc.registro(idProducto, nombre, porcentaje, categoria);
    }
}
