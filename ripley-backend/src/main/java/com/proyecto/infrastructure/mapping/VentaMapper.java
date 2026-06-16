package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.DetalleVenta;
import com.proyecto.domain.entities.Venta;
import com.proyecto.infrastructure.models.DetalleVentaModel;
import com.proyecto.infrastructure.models.VentaModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte Venta (dominio) ↔ VentaModel (JPA)
 * Tabla BD: ventas + detalle_ventas
 */
public class VentaMapper {

    // ── Dominio → Model (para GUARDAR en BD) ─────────
    public static VentaModel toModel(Venta venta) {
        if (venta == null) return null;
        VentaModel m = new VentaModel();
        m.setIdVenta(venta.getIdVenta());
        m.setFechaVenta(venta.getFechaVenta());
        m.setTotal(venta.getTotal());
        m.setEstado(venta.getEstado());
        m.setIdUsuario(venta.getIdUsuario());
        return m;
    }

    // ── Model → Dominio (al LEER de BD) ──────────────
    public static Venta toDomain(VentaModel m) {
        if (m == null) return null;
        Venta venta = new Venta();
        venta.setIdVenta(m.getIdVenta());
        venta.setFechaVenta(m.getFechaVenta());
        venta.setTotal(m.getTotal());
        venta.setEstado(m.getEstado());
        venta.setIdUsuario(m.getIdUsuario());

        // Mapear detalles si existen
        if (m.getDetalles() != null) {
            List<DetalleVenta> detalles = m.getDetalles()
                    .stream()
                    .map(VentaMapper::detalleTodominio)
                    .collect(Collectors.toList());
            venta.setDetalles(detalles);
        }
        return venta;
    }

    // ── DetalleVentaModel → DetalleVenta ─────────────
    public static DetalleVenta detalleTodominio(DetalleVentaModel m) {
        if (m == null) return null;
        DetalleVenta d = new DetalleVenta();
        d.setIdDetalle(m.getIdDetalle());
        d.setCantidad(m.getCantidad());
        d.setPrecioUnitario(m.getPrecioUnitario());
        d.setSubtotal(m.getSubtotal());
        // ProductoModel → idProducto
        if (m.getProducto() != null)
            d.setIdProducto(m.getProducto().getIdProducto());
        return d;
    }

    // ── DetalleVenta → DetalleVentaModel ─────────────
    public static DetalleVentaModel detalleToModel(DetalleVenta d, VentaModel ventaModel) {
        if (d == null) return null;
        DetalleVentaModel m = new DetalleVentaModel();
        m.setVenta(ventaModel);
        m.setCantidad(d.getCantidad());
        m.setPrecioUnitario(d.getPrecioUnitario());
        m.setSubtotal(d.getSubtotal());
        // ProductoModel se setea en el Repository usando idProducto
        // m.setProducto(...) se hace en VentaRepositoryImpl
        return m;
    }
}