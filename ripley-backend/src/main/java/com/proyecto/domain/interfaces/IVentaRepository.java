package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Venta;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO
 * Interfaz pura — define el contrato sin implementación
 * La infraestructura implementa esta interfaz con Hibernate
 */
public interface IVentaRepository {

    // Guardar una venta nueva
    Venta guardar(Venta venta);

    // Buscar venta por ID
    Optional<Venta> buscarPorId(Integer idVenta);

    // Listar todas las ventas
    List<Venta> listarTodas();

    // Actualizar estado de una venta
    void actualizarEstado(Integer idVenta, String nuevoEstado);
}