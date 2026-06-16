package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.PrediccionDemanda;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tabla BD: predicciones_ia
 * CU-03 Predicción de Demanda
 */
public interface IPrediccionRepository {

    /** Guarda el resultado de una predicción */
    PrediccionDemanda guardar(PrediccionDemanda prediccion);

    /** Obtiene la última predicción generada para un producto */
    Optional<PrediccionDemanda> obtenerUltimaPorProducto(Integer idProducto);

    /** Lista todas las predicciones de un producto */
    List<PrediccionDemanda> listarPorProducto(Integer idProducto);

    /**
     * Consulta el histórico de ventas de los últimos N días
     * necesario para ejecutar el modelo predictivo (CU-03 Paso 2)
     * Requiere mínimo 90 días de datos
     */
    List<PrediccionDemanda> consultarHistorico(Integer idProducto, int dias);

    /** Verifica si hay suficientes datos históricos (mínimo 90 días) */
    boolean tieneDatosHistoricos(Integer idProducto);

    /** Lista todas las predicciones */
    List<PrediccionDemanda> listarTodas();
}
