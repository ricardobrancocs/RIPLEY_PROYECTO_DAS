package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.MovimientoTrazabilidad;
import com.proyecto.domain.interfaces.ITrazabilidadRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa ITrazabilidadRepository
 * Trazabilidad se almacena en memoria (no tiene tabla BD propia)
 * En producción se persistiría en una tabla audit_log
 */
@ApplicationScoped
public class TrazabilidadRepositoryImpl implements ITrazabilidadRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    // Almacenamiento en memoria — reemplazar con tabla BD en producción
    private static final List<MovimientoTrazabilidad> historial = new ArrayList<>();
    private static int contador = 1;

    @Override
    @Transactional
    public MovimientoTrazabilidad registrarMovimiento(MovimientoTrazabilidad mov) {
        mov.setIdMovimiento(contador++);
        historial.add(mov);
        return mov;
    }

    @Override
    public List<MovimientoTrazabilidad> consultarHistorial(Integer idProducto) {
        return historial.stream()
                .filter(m -> idProducto.equals(m.getIdProducto()))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<MovimientoTrazabilidad> consultarPorUbicacion(Integer idProducto,
        String ubicacion) {
        return historial.stream()
                .filter(m -> idProducto.equals(m.getIdProducto())
                        && ubicacion.equals(m.getUbicacion()))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<MovimientoTrazabilidad> consultarPorResponsable(Integer idProducto,
        String responsable) {
        return historial.stream()
                .filter(m -> idProducto.equals(m.getIdProducto())
                        && responsable.equals(m.getResponsable()))
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public List<MovimientoTrazabilidad> listarTodos() {
    return new ArrayList<>(historial);
}

}
