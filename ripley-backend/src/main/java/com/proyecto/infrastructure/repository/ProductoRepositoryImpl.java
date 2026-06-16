package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Producto;
import com.proyecto.domain.interfaces.IProductoRepository;
import com.proyecto.infrastructure.mapping.ProductoMapper;
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
 * Implementa IProductoRepository usando JPA + Hibernate
 * Tabla BD: productos
 */
@ApplicationScoped
public class ProductoRepositoryImpl implements IProductoRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    public Optional<Producto> buscarPorId(Integer idProducto) {
        ProductoModel model = em.find(ProductoModel.class, idProducto);
        return Optional.ofNullable(ProductoMapper.toDomain(model));
    }

    @Override
    public Optional<Producto> buscarPorCodigoBarras(String codigoBarras) {
        return em.createQuery(
                "SELECT p FROM ProductoModel p WHERE p.codigoBarras = :cb",
                ProductoModel.class)
                .setParameter("cb", codigoBarras)
                .getResultStream()
                .findFirst()
                .map(ProductoMapper::toDomain);
    }

    @Override
    public List<Producto> listarTodos() {
        return em.createQuery("SELECT p FROM ProductoModel p", ProductoModel.class)
                .getResultList()
                .stream()
                .map(ProductoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Producto> listarPorCategoria(Integer idCategoria) {
        return em.createQuery(
                "SELECT p FROM ProductoModel p WHERE p.categoria.idCategoria = :id",
                ProductoModel.class)
                .setParameter("id", idCategoria)
                .getResultList()
                .stream()
                .map(ProductoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Producto guardar(Producto producto) {
        ProductoModel model = ProductoMapper.toModel(producto);
        if (model.getIdProducto() == null) {
            em.persist(model);
        } else {
            model = em.merge(model);
        }
        return ProductoMapper.toDomain(model);
    }

    @Override
    @Transactional
    public void reducirStock(Integer idProducto, Integer cantidad) {
        em.createQuery(
                "UPDATE ProductoModel p SET p.stockActual = p.stockActual - :cant " +
                "WHERE p.idProducto = :id AND p.stockActual >= :cant")
                .setParameter("cant", cantidad)
                .setParameter("id", idProducto)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void reponerStock(Integer idProducto, Integer cantidad) {
        em.createQuery(
                "UPDATE ProductoModel p SET p.stockActual = p.stockActual + :cant " +
                "WHERE p.idProducto = :id")
                .setParameter("cant", cantidad)
                .setParameter("id", idProducto)
                .executeUpdate();
    }

    @Override
    public List<Producto> listarBajoStockMinimo() {
        return em.createQuery(
                "SELECT p FROM ProductoModel p WHERE p.stockActual < p.stockMinimo",
                ProductoModel.class)
                .getResultList()
                .stream()
                .map(ProductoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
