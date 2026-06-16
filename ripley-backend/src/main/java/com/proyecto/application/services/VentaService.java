package com.proyecto.application.services;

import com.proyecto.application.dtos.ComprobanteDTO;
import com.proyecto.application.dtos.CrearProformaDTO;
import com.proyecto.application.dtos.RegistrarPagoDTO;
import com.proyecto.domain.entities.DetalleVenta;
import com.proyecto.domain.entities.Venta;
import com.proyecto.domain.interfaces.IProductoRepository;
import com.proyecto.domain.interfaces.IVentaRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: APLICACIÓN — Services
 * CU-01 Proceso de Venta
 * Orquesta: proforma → validar stock → registrar pago
 * → subflujos P-1 (entrega) P-2 (comprobante) P-3 (stock) P-4 (log)
 */
@ApplicationScoped
public class VentaService {

    @Inject
    private IVentaRepository ventaRepository;

    @Inject
    private IProductoRepository productoRepository;

    // ═══════════════════════════════════════════════════
    // PASOS 1-4: Crear Proforma
    // ═══════════════════════════════════════════════════
    public Venta crearProforma(CrearProformaDTO dto) {

        // Paso 2-3: Validar stock de cada producto (FA-3a)
        for (CrearProformaDTO.DetalleDTO detalle : dto.getDetalles()) {
            var producto = productoRepository.buscarPorId(detalle.getIdProducto())
                    .orElseThrow(() -> new RuntimeException(
                            "Producto no encontrado ID: " + detalle.getIdProducto()));

            if (!producto.tieneStockSuficiente(detalle.getCantidad())) {
                throw new RuntimeException(
                        "Stock insuficiente para: " + producto.getNombre());
            }
        }

        // Paso 4: Crear proforma con estado PENDIENTE
        Venta venta = new Venta();
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstado("PENDIENTE");
        venta.setIdUsuario(dto.getIdUsuario());
        venta.setTotal(BigDecimal.ZERO);

        // Mapear detalles
        List<DetalleVenta> detalles = new ArrayList<>();
        for (CrearProformaDTO.DetalleDTO d : dto.getDetalles()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdProducto(d.getIdProducto());
            detalle.setCantidad(d.getCantidad());
            detalle.setPrecioUnitario(BigDecimal.ZERO); // se calcula al pagar
            detalle.setSubtotal(BigDecimal.ZERO);
            detalles.add(detalle);
        }
        venta.setDetalles(detalles);

        return ventaRepository.guardar(venta);
    }

    // ═══════════════════════════════════════════════════
    // PASOS 5-10: Registrar Pago
    // ═══════════════════════════════════════════════════
    public ComprobanteDTO registrarPago(RegistrarPagoDTO dto) {

        // Buscar la venta (proforma)
        Venta venta = ventaRepository.buscarPorId(dto.getIdProforma())
                .orElseThrow(() -> new RuntimeException(
                        "Venta no encontrada ID: " + dto.getIdProforma()));

        // Paso 6: Validar pago (FA-7a)
        if (dto.getMontoPagado() == null
                || dto.getMontoPagado().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Pago inválido — monto incorrecto");
        }

        // Paso 8: Registrar venta como COMPLETADA
        venta.setTotal(dto.getMontoPagado());
        venta.setEstado("COMPLETADA");
        ventaRepository.actualizarEstado(venta.getIdVenta(), "COMPLETADA");

        // ── Subflujos paralelos P-1 a P-4 ──────────────

        // P-2: Generar número de comprobante
        String numeroComprobante = generarNumeroComprobante(venta);

        // P-3: Actualizar stock de cada producto vendido
        if (venta.getDetalles() != null) {
            for (DetalleVenta detalle : venta.getDetalles()) {
                productoRepository.reducirStock(
                        detalle.getIdProducto(),
                        detalle.getCantidad());
            }
        }

        // P-1: Programar entrega (lead time estimado 2 días)
        String fechaEntrega = LocalDateTime.now()
                .plusDays(2).toLocalDate().toString();

        // P-4: Guardar entrega (registrado en la venta)
        ventaRepository.guardar(venta);

        return new ComprobanteDTO(
                venta.getIdVenta(),
                venta.getFechaVenta(),
                venta.getTotal(),
                venta.getEstado() + " | " + numeroComprobante + " | Entrega: " + fechaEntrega,
                null
                // PDF se genera en otra capa si se requiere
        );
    }

    // ═══════════════════════════════════════════════════
    // Consultar venta por ID
    // ═══════════════════════════════════════════════════
    public Optional<Venta> obtenerVenta(Integer idVenta) {
        return ventaRepository.buscarPorId(idVenta);
    }

    // ── Método privado P-2 ──────────────────────────────
    private String generarNumeroComprobante(Venta venta) {
        return "B001-" + String.format("%05d", venta.getIdVenta());
    }

    /** Listar todas las ventas — requerido por GET /api/ventas */
    public List<Venta> listarVentas() {
    return ventaRepository.listarTodas();
}
}