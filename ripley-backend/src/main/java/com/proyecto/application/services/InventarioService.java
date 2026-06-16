package com.proyecto.application.services;

import com.proyecto.application.dtos.MovimientoRequest;
import com.proyecto.application.dtos.StockResponse;
import com.proyecto.domain.entities.MovimientoInventario;
import com.proyecto.domain.interfaces.IInventarioRepository;
import com.proyecto.domain.interfaces.IProductoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

/**
 * CAPA: APLICACIÓN — Services
 * CU-02 Gestión de Inventario
 */
@ApplicationScoped
public class InventarioService {

    @Inject private IInventarioRepository inventarioRepository;
    @Inject private IProductoRepository   productoRepository;

    /** POST registrar movimiento entrada o salida */
    public void registrarMovimiento(MovimientoRequest dto) {

        var producto = productoRepository.buscarPorId(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado ID: " + dto.getIdProducto()));

        MovimientoInventario movimiento;

        if ("ENTRADA".equalsIgnoreCase(dto.getTipoMovimiento())) {
            movimiento = MovimientoInventario.entrada(
                    dto.getIdProducto(), dto.getCantidad(), null);
            productoRepository.reponerStock(
                    dto.getIdProducto(), dto.getCantidad());
        } else {
            // Validar stock antes de salida — FA-4a: bloquear si stock negativo
            if (!producto.tieneStockSuficiente(dto.getCantidad())) {
                throw new RuntimeException(
                        "stock negativo — operación bloqueada para: "
                        + producto.getNombre());
            }
            movimiento = MovimientoInventario.salida(
                    dto.getIdProducto(), dto.getCantidad(), "VENTA");
            productoRepository.reducirStock(
                    dto.getIdProducto(), dto.getCantidad());
        }

        inventarioRepository.guardarMovimiento(movimiento);
    }

    /** GET consultar stock actual de un producto */
    public StockResponse consultarStock(Integer idProducto) {
        var producto = productoRepository.buscarPorId(idProducto)
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado ID: " + idProducto));

        String estado;
        if (producto.getStockActual() <= 0) {
            estado = "CRITICO";
        } else if (producto.estaBajoStockMinimo()) {
            estado = "BAJO";
        } else {
            estado = "NORMAL";
        }

        return new StockResponse(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getStockActual(),
                producto.getStockMinimo(),
                estado);
    }

    /** GET obtener stock de todos los productos */
public List<StockResponse> obtenerTodoElStock() {
    return productoRepository.listarTodos()
            .stream()
            .map(producto -> {
                String estado;
                if (producto.getStockActual() <= 0) {
                    estado = "CRITICO";
                } else if (producto.estaBajoStockMinimo()) {
                    estado = "BAJO";
                } else {
                    estado = "NORMAL";
                }
                return new StockResponse(
                        producto.getIdProducto(),
                        producto.getNombre(),
                        producto.getStockActual(),
                        producto.getStockMinimo(),
                        estado);
            })
            .collect(java.util.stream.Collectors.toList());
    }
}
