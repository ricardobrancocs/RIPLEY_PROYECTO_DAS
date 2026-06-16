package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.PrediccionDemanda;
import com.proyecto.domain.interfaces.IPrediccionRepository;
import com.proyecto.infrastructure.mapping.PrediccionMapper;
import com.proyecto.infrastructure.models.PrediccionIaModel;
import com.proyecto.infrastructure.models.ProductoModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IPrediccionRepository usando JPA + Hibernate
 * Tabla BD: predicciones_ia
 */
@ApplicationScoped
public class PrediccionRepositoryImpl implements IPrediccionRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    @Transactional
    public PrediccionDemanda guardar(PrediccionDemanda prediccion) {
        PrediccionIaModel model = PrediccionMapper.toModel(prediccion);
        if (prediccion.getIdProducto() != null) {
            ProductoModel producto = em.find(ProductoModel.class,
                    prediccion.getIdProducto());
            model.setProducto(producto);
        }
        if (model.getIdPrediccion() == null) {
            em.persist(model);
        } else {
            model = em.merge(model);
        }
        return PrediccionMapper.toDomain(model);
    }

    @Override
    public Optional<PrediccionDemanda> obtenerUltimaPorProducto(Integer idProducto) {
        return em.createQuery(
                "SELECT p FROM PrediccionIaModel p " +
                "WHERE p.producto.idProducto = :id " +
                "ORDER BY p.fechaPrediccion DESC",
                PrediccionIaModel.class)
                .setParameter("id", idProducto)
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .map(PrediccionMapper::toDomain);
    }

    @Override
    public List<PrediccionDemanda> listarPorProducto(Integer idProducto) {
        return em.createQuery(
                "SELECT p FROM PrediccionIaModel p " +
                "WHERE p.producto.idProducto = :id",
                PrediccionIaModel.class)
                .setParameter("id", idProducto)
                .getResultList()
                .stream()
                .map(PrediccionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<PrediccionDemanda> consultarHistorico(Integer idProducto, int dias) {
        LocalDate fechaDesde = LocalDate.now().minusDays(dias);
        return em.createQuery(
                "SELECT p FROM PrediccionIaModel p " +
                "WHERE p.producto.idProducto = :id " +
                "AND p.fechaPrediccion >= :fecha",
                PrediccionIaModel.class)
                .setParameter("id", idProducto)
                .setParameter("fecha", fechaDesde)
                .getResultList()
                .stream()
                .map(PrediccionMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean tieneDatosHistoricos(Integer idProducto) {
        LocalDate fecha90dias = LocalDate.now().minusDays(90);
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM PrediccionIaModel p " +
                "WHERE p.producto.idProducto = :id " +
                "AND p.fechaPrediccion >= :fecha",
                Long.class)
                .setParameter("id", idProducto)
                .setParameter("fecha", fecha90dias)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public List<PrediccionDemanda> listarTodas() {
    return em.createQuery(
            "SELECT p FROM PrediccionIaModel p",
            PrediccionIaModel.class)
            .getResultList()
            .stream()
            .map(PrediccionMapper::toDomain)
            .collect(Collectors.toList());
    }
}
