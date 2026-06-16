package com.proyecto.infrastructure.repository;

import com.proyecto.domain.entities.Devolucion;
import com.proyecto.domain.interfaces.IDevolucionRepository;
import com.proyecto.infrastructure.models.ProductoModel;
import com.proyecto.infrastructure.models.SalidaInventarioModel;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * CAPA: INFRAESTRUCTURA — Repository
 * Implementa IDevolucionRepository
 * Devolucion se persiste como SalidaInventarioModel
 * con motivo = "DEVOLUCION-{motivo}"
 */
@ApplicationScoped
public class DevolucionRepositoryImpl implements IDevolucionRepository {

    @PersistenceContext(unitName = "proyecto-pu")
    private EntityManager em;

    @Override
    @Transactional
    public Devolucion guardar(Devolucion devolucion) {
        // Persistir como salida de inventario con motivo DEVOLUCION
        SalidaInventarioModel model = new SalidaInventarioModel();
        ProductoModel producto = em.find(ProductoModel.class,
                devolucion.getIdProducto());
        model.setProducto(producto);
        model.setCantidad(devolucion.getCantidad());
        model.setMotivo("DEVOLUCION-" + devolucion.getMotivo());
        model.setFechaSalida(devolucion.getFecha());
        em.persist(model);
        devolucion.setIdDevolucion(model.getIdSalida());
        return devolucion;
    }

    @Override
    public Optional<Devolucion> buscarPorId(Integer idDevolucion) {
        SalidaInventarioModel model = em.find(SalidaInventarioModel.class,
                idDevolucion);
        if (model == null || model.getMotivo() == null
                || !model.getMotivo().startsWith("DEVOLUCION")) {
            return Optional.empty();
        }
        return Optional.of(mapToDomain(model));
    }

    @Override
    public List<Devolucion> listarPorVenta(Integer idVentaOriginal) {
        // Las devoluciones se identifican por motivo que contiene "DEVOLUCION"
        return em.createQuery(
                "SELECT s FROM SalidaInventarioModel s " +
                "WHERE s.motivo LIKE 'DEVOLUCION%'",
                SalidaInventarioModel.class)
                .getResultList()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Devolucion> listarPorProducto(Integer idProducto) {
        return em.createQuery(
                "SELECT s FROM SalidaInventarioModel s " +
                "WHERE s.producto.idProducto = :id " +
                "AND s.motivo LIKE 'DEVOLUCION%'",
                SalidaInventarioModel.class)
                .setParameter("id", idProducto)
                .getResultList()
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private Devolucion mapToDomain(SalidaInventarioModel m) {
        String motivo = m.getMotivo() != null
                ? m.getMotivo().replace("DEVOLUCION-", "") : "OTRO";
        Devolucion d = Devolucion.porOtroMotivo(null,
                m.getProducto() != null ? m.getProducto().getIdProducto() : null,
                m.getCantidad(), motivo);
        d.setIdDevolucion(m.getIdSalida());
        d.setFecha(m.getFechaSalida());
        return d;
    }
}
