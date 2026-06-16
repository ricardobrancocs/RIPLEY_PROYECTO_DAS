package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Devolucion;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * CU-08 Proceso de Devoluciones
 * (historial vinculado a ventas + productos)
 */
public interface IDevolucionRepository {

    /** Registra una devolución en BD */
    Devolucion guardar(Devolucion devolucion);

    /** Busca una devolución por su ID */
    Optional<Devolucion> buscarPorId(Integer idDevolucion);

    /** Lista todas las devoluciones de una venta original */
    List<Devolucion> listarPorVenta(Integer idVentaOriginal);

    /** Lista todas las devoluciones de un producto */
    List<Devolucion> listarPorProducto(Integer idProducto);
}
