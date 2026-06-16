package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.MovimientoInventario;
import com.proyecto.infrastructure.models.EntradaInventarioModel;
import com.proyecto.infrastructure.models.SalidaInventarioModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte MovimientoInventario (dominio) ↔ EntradaInventarioModel / SalidaInventarioModel
 * Tablas BD: entradas_inventario / salidas_inventario
 */
public class InventarioMapper {

    /** MovimientoInventario ENTRADA → EntradaInventarioModel */
    public static EntradaInventarioModel toEntradaModel(MovimientoInventario e) {
        if (e == null) return null;
        EntradaInventarioModel m = new EntradaInventarioModel();
        m.setIdEntrada(e.getIdMovimiento());
        m.setCantidad(e.getCantidad());
        m.setFechaEntrada(e.getFecha());
        // producto y proveedor se setean via sus Models en el repository
        return m;
    }

    /** MovimientoInventario SALIDA → SalidaInventarioModel */
    public static SalidaInventarioModel toSalidaModel(MovimientoInventario e) {
        if (e == null) return null;
        SalidaInventarioModel m = new SalidaInventarioModel();
        m.setIdSalida(e.getIdMovimiento());
        m.setCantidad(e.getCantidad());
        m.setMotivo(e.getMotivo());
        m.setFechaSalida(e.getFecha());
        // producto se setea via ProductoModel en el repository
        return m;
    }

    /** EntradaInventarioModel → MovimientoInventario */
    public static MovimientoInventario toDomainFromEntrada(EntradaInventarioModel m) {
        if (m == null) return null;
        MovimientoInventario e = MovimientoInventario.entrada(
                m.getProducto() != null ? m.getProducto().getIdProducto() : null,
                m.getCantidad(),
                m.getProveedor() != null ? m.getProveedor().getIdProveedor() : null
        );
        e.setIdMovimiento(m.getIdEntrada());
        e.setFecha(m.getFechaEntrada());
        return e;
    }

    /** SalidaInventarioModel → MovimientoInventario */
    public static MovimientoInventario toDomainFromSalida(SalidaInventarioModel m) {
        if (m == null) return null;
        MovimientoInventario e = MovimientoInventario.salida(
                m.getProducto() != null ? m.getProducto().getIdProducto() : null,
                m.getCantidad(),
                m.getMotivo()
        );
        e.setIdMovimiento(m.getIdSalida());
        e.setFecha(m.getFechaSalida());
        return e;
    }
}
