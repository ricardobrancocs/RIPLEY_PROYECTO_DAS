package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Recomendacion;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Derivada de predicciones_ia + productos + proveedores
 * CU-04 Recomendación de Reposición
 */
public interface IRecomendacionRepository {

    /** Guarda una nueva recomendación con estado PENDIENTE */
    Recomendacion guardar(Recomendacion recomendacion);

    /** Busca una recomendación por su ID */
    Optional<Recomendacion> buscarPorId(Integer idRecomendacion);

    /** Lista todas las recomendaciones pendientes */
    List<Recomendacion> listarPendientes();

    /** Lista todas las recomendaciones de un producto */
    List<Recomendacion> listarPorProducto(Integer idProducto);

    /** Actualiza el estado de la recomendación (APROBADA / RECHAZADA) */
    void actualizarEstado(Integer idRecomendacion, String estado);
}
