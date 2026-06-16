package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.MovimientoTrazabilidad;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * MovimientoTrazabilidad no tiene tabla propia en BD actual.
 * Se persiste como registro en entradas/salidas con descripción.
 * Este mapper construye el objeto de dominio para uso en memoria.
 */
public class TrazabilidadMapper {

    public static MovimientoTrazabilidad toDomain(Integer idProducto,
    String ubicacion,
    String responsable,
    String descripcion) {
        return new MovimientoTrazabilidad(
        idProducto, 
        ubicacion,
        responsable, 
        descripcion);
    }
}
