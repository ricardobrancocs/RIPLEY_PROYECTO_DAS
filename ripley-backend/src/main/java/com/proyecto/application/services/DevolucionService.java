package com.proyecto.application.services;

import com.proyecto.application.dtos.DevolucionRequest;
import com.proyecto.application.dtos.DevolucionResponse;
import com.proyecto.domain.entities.Devolucion;
import com.proyecto.domain.interfaces.IDevolucionRepository;
import com.proyecto.domain.interfaces.IProductoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.time.LocalDateTime;

/**
 * CAPA: APLICACIÓN — Services
 * CU-08 Proceso de Devoluciones
 */
@ApplicationScoped
public class DevolucionService {

    @Inject private IDevolucionRepository devolucionRepository;
    @Inject private IProductoRepository   productoRepository;

    /** POST registrar devolución */
    public DevolucionResponse registrarDevolucion(DevolucionRequest dto) {

        // Paso 2: validar que el producto existe
        productoRepository.buscarPorId(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException(
                        "Producto no válido ID: " + dto.getIdProducto()));

        // Paso 3: crear devolución con Factory Method
        Devolucion devolucion = Devolucion.porOtroMotivo(
                dto.getIdVentaOriginal(),
                dto.getIdProducto(),
                dto.getCantidad(),
                dto.getMotivo());

        // Subflujo B1: actualizar stock — reingresa unidades
        productoRepository.reponerStock(
                dto.getIdProducto(), dto.getCantidad());

        // Subflujo B2: guardar devolución
        Devolucion guardada = devolucionRepository.guardar(devolucion);

        DevolucionResponse resp = new DevolucionResponse();
        resp.setIdDevolucion(guardada.getIdDevolucion());
        resp.setIdProducto(guardada.getIdProducto());
        resp.setMotivo(guardada.getMotivo());
        resp.setEstado("REGISTRADA");
        resp.setFecha(LocalDateTime.now());
        resp.setMensaje("Devolución registrada y stock actualizado");
        return resp;
    }
}
