package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Producto;
import com.proyecto.infrastructure.models.ProductoModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte Producto (dominio) ↔ ProductoModel (JPA)
 * Tabla BD: productos
 */
public class ProductoMapper {

    public static ProductoModel toModel(Producto e) {
        if (e == null) return null;
        ProductoModel m = new ProductoModel();
        m.setIdProducto(e.getIdProducto());
        m.setNombre(e.getNombre());
        m.setDescripcion(e.getDescripcion());
        m.setCodigoBarras(e.getCodigoBarras());
        m.setPrecio(e.getPrecio());
        m.setStockActual(e.getStockActual());
        m.setStockMinimo(e.getStockMinimo());
        m.setFechaRegistro(e.getFechaRegistro());
        // idCategoria se maneja via CategoriaModel en el repository
        return m;
    }

    public static Producto toDomain(ProductoModel m) {
        if (m == null) return null;
        Producto e = new Producto();
        e.setIdProducto(m.getIdProducto());
        e.setNombre(m.getNombre());
        e.setDescripcion(m.getDescripcion());
        e.setCodigoBarras(m.getCodigoBarras());
        e.setPrecio(m.getPrecio());
        e.setStockActual(m.getStockActual());
        e.setStockMinimo(m.getStockMinimo());
        e.setFechaRegistro(m.getFechaRegistro());
        if (m.getCategoria() != null)
            e.setIdCategoria(m.getCategoria().getIdCategoria());
        return e;
    }
}
