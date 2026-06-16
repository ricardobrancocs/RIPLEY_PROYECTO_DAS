package com.proyecto.application.services;

import com.proyecto.application.dtos.HistorialResponse;
import com.proyecto.application.dtos.MovimientoTrazabilidadRequest;
import com.proyecto.domain.entities.MovimientoTrazabilidad;
import com.proyecto.domain.interfaces.IProductoRepository;
import com.proyecto.domain.interfaces.ITrazabilidadRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;


import java.util.stream.Collectors;

/**
 * CAPA: APLICACIÓN — Services
 * CU-09 Trazabilidad de Productos
 */
@ApplicationScoped
public class TrazabilidadService {

    @Inject private ITrazabilidadRepository trazabilidadRepository;
    @Inject private IProductoRepository     productoRepository;

    /** POST registrar movimiento físico */
    public void registrarMovimiento(MovimientoTrazabilidadRequest dto) {
        MovimientoTrazabilidad mov = new MovimientoTrazabilidad(
                dto.getIdProducto(),
                dto.getUbicacion(),
                dto.getResponsable(),
                dto.getDescripcion());
        trazabilidadRepository.registrarMovimiento(mov);
    }

    /** GET consultar historial completo */
    public HistorialResponse consultarHistorial(Integer idProducto) {
        var producto = productoRepository.buscarPorId(idProducto)
                .orElseThrow(() -> new RuntimeException(
                        "Producto no encontrado ID: " + idProducto));

        var movimientos = trazabilidadRepository
                .consultarHistorial(idProducto)
                .stream()
                .map(m -> {
                    MovimientoTrazabilidadRequest r =
                            new MovimientoTrazabilidadRequest();
                    r.setIdProducto(m.getIdProducto());
                    r.setUbicacion(m.getUbicacion());
                    r.setResponsable(m.getResponsable());
                    r.setFecha(m.getFecha());
                    r.setDescripcion(m.getDescripcion());
                    return r;
                })
                .collect(Collectors.toList());

        HistorialResponse resp = new HistorialResponse();
        resp.setIdProducto(idProducto);
        resp.setNombreProducto(producto.getNombre());
        resp.setMovimientos(movimientos);
        return resp;
    }

    /** Listar todos los movimientos — requerido por GET /api/trazabilidad */
        public List<HistorialResponse> listarHistorial() {
        return trazabilidadRepository.listarTodos()
            .stream()
            .map(m -> {
                HistorialResponse resp = new HistorialResponse();
                resp.setIdProducto(m.getIdProducto());
                resp.setNombreProducto("Producto #" + m.getIdProducto());
                resp.setMovimientos(List.of());
                return resp;
            })
            .collect(Collectors.toList());
    }
    
}
