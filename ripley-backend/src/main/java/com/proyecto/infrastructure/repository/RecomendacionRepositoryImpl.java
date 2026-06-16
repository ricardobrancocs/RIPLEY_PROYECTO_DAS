package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Recomendacion;
import com.proyecto.domain.interfaces.IRecomendacionRepository;

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
 * Implementa IRecomendacionRepository
 * Almacenamiento en memoria (no tiene tabla BD propia)
 */
@ApplicationScoped
public class RecomendacionRepositoryImpl implements IRecomendacionRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    private static final List<Recomendacion> store = new ArrayList<>();
    private static int contador = 1;

    @Override
    @Transactional
    public Recomendacion guardar(Recomendacion recomendacion) {
        recomendacion.setIdRecomendacion(contador++);
        store.add(recomendacion);
        return recomendacion;
    }

    @Override
    public Optional<Recomendacion> buscarPorId(Integer id) {
        return store.stream()
                .filter(r -> id.equals(r.getIdRecomendacion()))
                .findFirst();
    }

    @Override
    public List<Recomendacion> listarPendientes() {
        return store.stream()
                .filter(Recomendacion::estaPendiente)
                .collect(Collectors.toList());
    }

    @Override
    public List<Recomendacion> listarPorProducto(Integer idProducto) {
        return store.stream()
                .filter(r -> idProducto.equals(r.getIdProducto()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void actualizarEstado(Integer id, String estado) {
        store.stream()
                .filter(r -> id.equals(r.getIdRecomendacion()))
                .findFirst()
                .ifPresent(r -> r.setEstado(estado));
    }
}
