package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.MovimientoTrazabilidad;
import java.util.List;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * CU-09 Trazabilidad de Productos
 * Historial INMUTABLE — solo INSERT, nunca UPDATE ni DELETE
 */
public interface ITrazabilidadRepository {

    /**
     * Registra un movimiento físico (INSERT puro — sin update)
     * CU-09 Paso 3: guardar fecha, ubicación y responsable
     */
    MovimientoTrazabilidad registrarMovimiento(MovimientoTrazabilidad movimiento);

    /**
     * Consulta el historial completo de un producto
     * CU-09 Paso 7: buscar trazabilidad — retorna todos los eventos
     */
    List<MovimientoTrazabilidad> consultarHistorial(Integer idProducto);

    /** Filtra historial por ubicación */
    List<MovimientoTrazabilidad> consultarPorUbicacion(Integer idProducto,
                                                        String ubicacion);

    /** Filtra historial por responsable */
    List<MovimientoTrazabilidad> consultarPorResponsable(Integer idProducto,
    String responsable);

    /** Lista todos los movimientos de trazabilidad */
    List<MovimientoTrazabilidad> listarTodos();
}
