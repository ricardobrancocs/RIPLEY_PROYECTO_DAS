package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.MovimientoInventario;
import com.proyecto.domain.interfaces.IInventarioRepository;
import com.proyecto.infrastructure.mapping.InventarioMapper;
import com.proyecto.infrastructure.models.EntradaInventarioModel;
import com.proyecto.infrastructure.models.ProductoModel;
import com.proyecto.infrastructure.models.ProveedorModel;
import com.proyecto.infrastructure.models.SalidaInventarioModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IInventarioRepository usando JPA + Hibernate
 * Tablas BD: entradas_inventario / salidas_inventario
 */
@ApplicationScoped
public class InventarioRepositoryImpl implements IInventarioRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    @Transactional
    public MovimientoInventario guardarMovimiento(MovimientoInventario movimiento) {
        if (movimiento.esEntrada()) {
            EntradaInventarioModel model =
                    InventarioMapper.toEntradaModel(movimiento);
            ProductoModel producto = em.find(ProductoModel.class,
                    movimiento.getIdProducto());
            model.setProducto(producto);
            if (movimiento.getIdProveedor() != null) {
                ProveedorModel proveedor = em.find(ProveedorModel.class,
                        movimiento.getIdProveedor());
                model.setProveedor(proveedor);
            }
            em.persist(model);
            return InventarioMapper.toDomainFromEntrada(model);
        } else {
            SalidaInventarioModel model =
                    InventarioMapper.toSalidaModel(movimiento);
            ProductoModel producto = em.find(ProductoModel.class,
                    movimiento.getIdProducto());
            model.setProducto(producto);
            em.persist(model);
            return InventarioMapper.toDomainFromSalida(model);
        }
    }

    @Override
    public List<MovimientoInventario> listarPorProducto(Integer idProducto) {
        List<MovimientoInventario> resultado = new ArrayList<>();
        resultado.addAll(listarEntradasPorProducto(idProducto));
        resultado.addAll(listarSalidasPorProducto(idProducto));
        return resultado;
    }

    @Override
    public List<MovimientoInventario> listarEntradasPorProducto(Integer idProducto) {
        return em.createQuery(
                "SELECT e FROM EntradaInventarioModel e " +
                "WHERE e.producto.idProducto = :id",
                EntradaInventarioModel.class)
                .setParameter("id", idProducto)
                .getResultList()
                .stream()
                .map(InventarioMapper::toDomainFromEntrada)
                .collect(Collectors.toList());
    }

    @Override
    public List<MovimientoInventario> listarSalidasPorProducto(Integer idProducto) {
        return em.createQuery(
                "SELECT s FROM SalidaInventarioModel s " +
                "WHERE s.producto.idProducto = :id",
                SalidaInventarioModel.class)
                .setParameter("id", idProducto)
                .getResultList()
                .stream()
                .map(InventarioMapper::toDomainFromSalida)
                .collect(Collectors.toList());
    }
}
