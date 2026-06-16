package com.proyecto.application.services;

import com.proyecto.application.dtos.ClasificacionResponse;
import com.proyecto.domain.entities.ClasificacionAbc;
import com.proyecto.domain.interfaces.IClasificacionAbcRepository;
import com.proyecto.domain.interfaces.IProductoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * CAPA: APLICACIÓN — Services
 * CU-10 Clasificación ABC de Productos
 */
@ApplicationScoped
public class ClasificacionAbcService {

    @Inject private IClasificacionAbcRepository clasificacionRepository;
    @Inject private IProductoRepository         productoRepository;

    /** POST ejecutar clasificación ABC sobre todo el catálogo */
    public void ejecutarClasificacion() {
        var productos = productoRepository.listarTodos();
        if (productos.isEmpty()) return;

        // Motor Singleton con umbrales configurados
        ClasificacionAbc motor = ClasificacionAbc.getInstance();

        AtomicInteger contador = new AtomicInteger(0);
        int total = productos.size();

        List<ClasificacionAbc> clasificaciones = productos.stream()
                .map(p -> {
                    double porcentaje = ((double) contador.incrementAndGet()
                            / total) * 100;
                    String categoria = motor.determinarCategoria(porcentaje);
                    return ClasificacionAbc.registro(
                            p.getIdProducto(),
                            p.getNombre(),
                            porcentaje,
                            categoria);
                })
                .collect(Collectors.toList());

        clasificacionRepository.guardarTodas(clasificaciones);
    }

    /** GET obtener resultados del dashboard */
    public List<ClasificacionResponse> obtenerResultados() {
        return clasificacionRepository.listarTodas()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private ClasificacionResponse toResponse(ClasificacionAbc c) {
        ClasificacionResponse r = new ClasificacionResponse();
        r.setIdProducto(c.getIdProducto());
        r.setNombre(c.getNombreProducto());
        r.setCategoria(c.getCategoria());
        r.setPorcentajeVentas(c.getPorcentajeVentas());
        r.setFechaClasificacion(c.getFechaClasificacion());
        return r;
    }
}
