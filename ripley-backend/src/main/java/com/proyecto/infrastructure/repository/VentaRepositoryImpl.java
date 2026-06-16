package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Venta;
import com.proyecto.domain.interfaces.IVentaRepository;
import com.proyecto.infrastructure.mapping.VentaMapper;
import com.proyecto.infrastructure.models.VentaModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repositories
 * Implementa IVentaRepository usando Hibernate + JPA
 * Aquí sí usamos EntityManager para operar con la BD
 */
@ApplicationScoped
public class VentaRepositoryImpl implements IVentaRepository {

    // EntityManager es el puente entre Java y PostgreSQL via Hibernate
    // WildFly lo inyecta automáticamente usando el datasource java:/PostgreSQLDS
    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    // ═══════════════════════════════════════
    // GUARDAR venta nueva en BD
    // ═══════════════════════════════════════
    @Override
    @Transactional
    public Venta guardar(Venta venta) {
        VentaModel model = VentaMapper.toModel(venta);
        em.persist(model);
        em.flush();
        return VentaMapper.toDomain(model);
    }

    // ═══════════════════════════════════════
    // BUSCAR venta por ID
    // ═══════════════════════════════════════
    @Override
    public Optional<Venta> buscarPorId(Integer idVenta) {
        VentaModel model = em.find(VentaModel.class, idVenta);
        return Optional.ofNullable(VentaMapper.toDomain(model));
    }

    // ═══════════════════════════════════════
    // LISTAR todas las ventas
    // ═══════════════════════════════════════
    @Override
    public List<Venta> listarTodas() {
        List<VentaModel> models = em
            .createQuery("SELECT v FROM VentaModel v", VentaModel.class)
            .getResultList();

        return models.stream()
            .map(VentaMapper::toDomain)
            .collect(Collectors.toList());
    }

    // ═══════════════════════════════════════
    // ACTUALIZAR estado de una venta
    // ═══════════════════════════════════════
    @Override
    @Transactional
    public void actualizarEstado(Integer idVenta, String nuevoEstado) {
    VentaModel model = em.find(VentaModel.class, idVenta);
    if (model != null) {
        model.setEstado(nuevoEstado);
        em.merge(model);
        }
    }
}