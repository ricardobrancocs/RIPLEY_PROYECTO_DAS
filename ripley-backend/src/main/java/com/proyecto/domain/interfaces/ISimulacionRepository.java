package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Simulacion;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * CU-11 Simulación de Escenarios Financieros
 * Los datos de simulación NO alteran datos reales (entorno sandbox)
 */
public interface ISimulacionRepository {

    /**
     * Guarda los parámetros y resultados de una simulación
     * CU-11: los resultados son auditables — se persisten con parámetros de entrada
     */
    Simulacion guardar(Simulacion simulacion);

    /** Busca una simulación por su ID */
    Optional<Simulacion> buscarPorId(Integer idSimulacion);

    /**
     * Lista las simulaciones del usuario solicitante
     * CU-11: el gerente puede revisar simulaciones anteriores
     */
    List<Simulacion> listarPorUsuario(String usuarioSolicitante);

    /**
     * Consulta datos históricos de costos e inventario
     * CU-11 Paso 5: base de datos devuelve información para el cálculo
     */
    List<Simulacion> consultarDatosHistoricos();
}
