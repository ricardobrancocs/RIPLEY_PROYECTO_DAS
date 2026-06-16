package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.MovimientoInventario;
import java.util.List;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tablas BD: entradas_inventario / salidas_inventario
 * CU-02 Gestión de Inventario
 */
public interface IInventarioRepository {

    /** Registra un movimiento de entrada o salida */
    MovimientoInventario guardarMovimiento(MovimientoInventario movimiento);

    /** Lista todos los movimientos de un producto */
    List<MovimientoInventario> listarPorProducto(Integer idProducto);

    /** Lista solo las entradas de un producto */
    List<MovimientoInventario> listarEntradasPorProducto(Integer idProducto);

    /** Lista solo las salidas de un producto */
    List<MovimientoInventario> listarSalidasPorProducto(Integer idProducto);
}
