package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Producto;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tabla BD: productos
 * CU-01 Proceso de Venta / CU-02 Gestión de Inventario
 */
public interface IProductoRepository {

    /** Busca un producto por su ID */
    Optional<Producto> buscarPorId(Integer idProducto);

    /** Busca un producto por código de barras */
    Optional<Producto> buscarPorCodigoBarras(String codigoBarras);

    /** Lista todos los productos activos */
    List<Producto> listarTodos();

    /** Lista productos por categoría */
    List<Producto> listarPorCategoria(Integer idCategoria);

    /** Guarda o actualiza un producto */
    Producto guardar(Producto producto);

    /** Reduce el stock tras una venta (Subflujo P-3 CU-01) */
    void reducirStock(Integer idProducto, Integer cantidad);

    /** Repone el stock tras una devolución (CU-08) */
    void reponerStock(Integer idProducto, Integer cantidad);

    /** Lista productos con stock por debajo del mínimo (CU-02) */
    List<Producto> listarBajoStockMinimo();
}
