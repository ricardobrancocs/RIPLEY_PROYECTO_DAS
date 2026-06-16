package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.ClasificacionAbc;
import com.proyecto.domain.interfaces.IClasificacionAbcRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IClasificacionAbcRepository
 * Almacenamiento en memoria (no tiene tabla BD propia)
 */
@ApplicationScoped
public class ClasificacionAbcRepositoryImpl implements IClasificacionAbcRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    private static final List<ClasificacionAbc> store = new ArrayList<>();

    @Override
    @Transactional
    public ClasificacionAbc guardar(ClasificacionAbc clasificacion) {
        store.removeIf(c -> clasificacion.getIdProducto()
                .equals(c.getIdProducto()));
        store.add(clasificacion);
        return clasificacion;
    }

    @Override
    public Optional<ClasificacionAbc> buscarPorProducto(Integer idProducto) {
        return store.stream()
                .filter(c -> idProducto.equals(c.getIdProducto()))
                .findFirst();
    }

    @Override
    public List<ClasificacionAbc> listarTodas() {
        return new ArrayList<>(store);
    }

    @Override
    public List<ClasificacionAbc> listarPorCategoria(String categoria) {
        return store.stream()
                .filter(c -> categoria.equals(c.getCategoria()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void guardarTodas(List<ClasificacionAbc> clasificaciones) {
        store.clear();
        store.addAll(clasificaciones);
    }
}
