package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Alerta;
import com.proyecto.domain.interfaces.IAlertaRepository;
import com.proyecto.infrastructure.mapping.AlertaMapper;
import com.proyecto.infrastructure.models.AlertaStockModel;
import com.proyecto.infrastructure.models.ProductoModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IAlertaRepository usando JPA + Hibernate
 * Tabla BD: alertas_stock
 */
@ApplicationScoped
public class AlertaRepositoryImpl implements IAlertaRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    @Transactional
    public Alerta guardar(Alerta alerta) {
        AlertaStockModel model = AlertaMapper.toModel(alerta);
        // Setear ProductoModel por id
        if (alerta.getIdProducto() != null) {
            ProductoModel producto = em.find(ProductoModel.class, alerta.getIdProducto());
            model.setProducto(producto);
        }
        if (model.getIdAlerta() == null) {
            em.persist(model);
        } else {
            model = em.merge(model);
        }
        return AlertaMapper.toDomain(model);
    }

    @Override
    public Optional<Alerta> buscarPorId(Integer idAlerta) {
        AlertaStockModel model = em.find(AlertaStockModel.class, idAlerta);
        return Optional.ofNullable(AlertaMapper.toDomain(model));
    }

    @Override
    public List<Alerta> listarActivas() {
        return em.createQuery(
                "SELECT a FROM AlertaStockModel a WHERE a.estado = 'A'",
                AlertaStockModel.class)
                .getResultList()
                .stream()
                .map(AlertaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Alerta> listarPorProducto(Integer idProducto) {
        return em.createQuery(
                "SELECT a FROM AlertaStockModel a WHERE a.producto.idProducto = :id",
                AlertaStockModel.class)
                .setParameter("id", idProducto)
                .getResultList()
                .stream()
                .map(AlertaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Alerta> listarPorTipo(String tipoAlerta) {
        return em.createQuery(
                "SELECT a FROM AlertaStockModel a WHERE a.tipoAlerta = :tipo",
                AlertaStockModel.class)
                .setParameter("tipo", tipoAlerta)
                .getResultList()
                .stream()
                .map(AlertaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void actualizarEstado(Integer idAlerta, String estado) {
        em.createQuery(
                "UPDATE AlertaStockModel a SET a.estado = :estado WHERE a.idAlerta = :id")
                .setParameter("estado", estado)
                .setParameter("id", idAlerta)
                .executeUpdate();
    }
}
