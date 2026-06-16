package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Simulacion;
import com.proyecto.domain.interfaces.ISimulacionRepository;

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
 * Implementa ISimulacionRepository
 * Almacenamiento en memoria (no tiene tabla BD propia)
 */
@ApplicationScoped
public class SimulacionRepositoryImpl implements ISimulacionRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    private static final List<Simulacion> store = new ArrayList<>();
    private static int contador = 1;

    @Override
    @Transactional
    public Simulacion guardar(Simulacion simulacion) {
        simulacion.setIdSimulacion(contador++);
        store.add(simulacion);
        return simulacion;
    }

    @Override
    public Optional<Simulacion> buscarPorId(Integer id) {
        return store.stream()
                .filter(s -> id.equals(s.getIdSimulacion()))
                .findFirst();
    }

    @Override
    public List<Simulacion> listarPorUsuario(String usuario) {
        return store.stream()
                .filter(s -> usuario.equals(s.getUsuarioSolicitante()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Simulacion> consultarDatosHistoricos() {
        return new ArrayList<>(store);
    }
}
