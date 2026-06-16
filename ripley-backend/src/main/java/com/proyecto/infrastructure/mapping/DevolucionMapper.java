package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Devolucion;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Devolucion no tiene tabla propia en BD — se persiste
 * como SalidaInventarioModel con motivo = "DEVOLUCION"
 * Este mapper convierte para ese propósito
 */
public class DevolucionMapper {

    /** Crea una Devolucion desde los datos básicos */
    public static Devolucion toDomain(
        Integer idVenta, 
        Integer idProducto,
        Integer cantidad, 
        String motivo) 
        {
        return Devolucion.porOtroMotivo(idVenta, idProducto, cantidad, motivo);
    }
}
