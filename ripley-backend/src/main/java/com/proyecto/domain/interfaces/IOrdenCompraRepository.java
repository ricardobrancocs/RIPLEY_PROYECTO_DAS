package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.OrdenCompra;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * CU-02 Gestión de Inventario — generación de orden de compra
 * cuando stock < stockMinimo (Paso 8 del CU-02)
 */
public interface IOrdenCompraRepository {

    /** Guarda una nueva orden de compra con estado PENDIENTE */
    OrdenCompra guardar(OrdenCompra ordenCompra);

    /** Busca una orden por su ID */
    Optional<OrdenCompra> buscarPorId(Integer idOrdenCompra);

    /** Lista todas las órdenes pendientes */
    List<OrdenCompra> listarPendientes();

    /** Lista las órdenes de un proveedor */
    List<OrdenCompra> listarPorProveedor(Integer idProveedor);

    /** Actualiza el estado de la orden (ENVIADA / RECIBIDA / CANCELADA) */
    void actualizarEstado(Integer idOrdenCompra, String estado);
}
