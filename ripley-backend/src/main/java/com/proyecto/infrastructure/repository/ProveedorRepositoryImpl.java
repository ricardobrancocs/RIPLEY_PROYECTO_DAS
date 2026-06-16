package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Proveedor;
import com.proyecto.domain.interfaces.IProveedorRepository;
import com.proyecto.infrastructure.mapping.ProveedorMapper;
import com.proyecto.infrastructure.models.ProveedorModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IProveedorRepository usando JPA + Hibernate
 * Tabla BD: proveedores
 */
@ApplicationScoped
public class ProveedorRepositoryImpl implements IProveedorRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    @Transactional
    public Proveedor guardar(Proveedor proveedor) {
        ProveedorModel model = ProveedorMapper.toModel(proveedor);
        if (model.getIdProveedor() == null) {
            em.persist(model);
        } else {
            model = em.merge(model);
        }
        return ProveedorMapper.toDomain(model);
    }

    @Override
    public Optional<Proveedor> buscarPorId(Integer idProveedor) {
        ProveedorModel model = em.find(ProveedorModel.class, idProveedor);
        return Optional.ofNullable(ProveedorMapper.toDomain(model));
    }

    @Override
    public Optional<Proveedor> buscarPorEmpresa(String empresa) {
        return em.createQuery(
                "SELECT p FROM ProveedorModel p WHERE p.empresa = :empresa",
                ProveedorModel.class)
                .setParameter("empresa", empresa)
                .getResultStream()
                .findFirst()
                .map(ProveedorMapper::toDomain);
    }

    @Override
    public List<Proveedor> listarTodos() {
        return em.createQuery("SELECT p FROM ProveedorModel p", ProveedorModel.class)
                .getResultList()
                .stream()
                .map(ProveedorMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Proveedor actualizar(Proveedor proveedor) {
        ProveedorModel model = ProveedorMapper.toModel(proveedor);
        return ProveedorMapper.toDomain(em.merge(model));
    }

    @Override
    public boolean existePorEmpresa(String empresa) {
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM ProveedorModel p WHERE p.empresa = :empresa",
                Long.class)
                .setParameter("empresa", empresa)
                .getSingleResult();
        return count > 0;
    }
}
