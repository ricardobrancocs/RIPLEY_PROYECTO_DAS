package com.proyecto.application.services;

import com.proyecto.application.dtos.RecomendacionResponse;
import com.proyecto.domain.entities.Recomendacion;
import com.proyecto.domain.interfaces.IProductoRepository;
import com.proyecto.domain.interfaces.IRecomendacionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CAPA: APLICACIÓN — Services
 * CU-04 Recomendación de Reposición
 */
@ApplicationScoped
public class ReposicionService {

    @Inject private IRecomendacionRepository recomendacionRepository;
    @Inject private IProductoRepository      productoRepository;

    /** GET listar recomendaciones pendientes */
    public List<RecomendacionResponse> obtenerRecomendaciones() {
        return recomendacionRepository.listarPendientes()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /** POST aprobar recomendación */
    public void aprobarRecomendacion(Integer idRecomendacion) {
        recomendacionRepository.buscarPorId(idRecomendacion)
                .orElseThrow(() -> new RuntimeException(
                        "Recomendación no encontrada ID: " + idRecomendacion));
        recomendacionRepository.actualizarEstado(idRecomendacion, "APROBADA");
    }

    /** Genera recomendación para productos bajo stock mínimo */
    public void generarRecomendaciones() {
        productoRepository.listarBajoStockMinimo().forEach(producto -> {
            Recomendacion rec = new Recomendacion.Builder()
                    .idProducto(producto.getIdProducto())
                    .nombreProducto(producto.getNombre())
                    .cantidadSugerida(producto.getStockMinimo() * 2)
                    .idProveedor(1) // proveedor por defecto
                    .nombreProveedor("Proveedor Principal")
                    .costoEstimado(producto.getPrecio()
                            .multiply(BigDecimal.valueOf(
                                    producto.getStockMinimo() * 2)))
                    .fechaLimitePedido(
                            LocalDate.now().plusDays(7).toString())
                    .build();
            recomendacionRepository.guardar(rec);
        });
    }

    private RecomendacionResponse toResponse(Recomendacion r) {
        RecomendacionResponse resp = new RecomendacionResponse();
        resp.setId(r.getIdRecomendacion());
        resp.setIdProducto(r.getIdProducto());
        resp.setNombreProducto(r.getNombreProducto());
        resp.setCantidadSugerida(r.getCantidadSugerida());
        resp.setIdProveedorRecomendado(r.getIdProveedorRecomendado());
        resp.setNombreProveedor(r.getNombreProveedor());
        resp.setCostoEstimado(r.getCostoEstimado());
        resp.setFechaLimitePedido(r.getFechaLimitePedido());
        resp.setEstado(r.getEstado());
        return resp;
    }
}
