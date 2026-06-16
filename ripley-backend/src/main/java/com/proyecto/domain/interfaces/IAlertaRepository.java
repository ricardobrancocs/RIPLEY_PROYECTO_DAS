package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Alerta;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tabla BD: alertas_stock
 * CU-05 Alertas Inteligentes
 */
public interface IAlertaRepository {

    /** Guarda una nueva alerta (estado inicial: A = activa) */
    Alerta guardar(Alerta alerta);

    /** Busca una alerta por su ID */
    Optional<Alerta> buscarPorId(Integer idAlerta);

    /** Lista todas las alertas activas (estado = 'A') */
    List<Alerta> listarActivas();

    /** Lista todas las alertas de un producto */
    List<Alerta> listarPorProducto(Integer idProducto);

    /** Lista alertas por tipo (CRITICA / ADVERTENCIA / INFORMATIVA) */
    List<Alerta> listarPorTipo(String tipoAlerta);

    /** Actualiza el estado de una alerta (T=atendida / D=descartada) */
    void actualizarEstado(Integer idAlerta, String estado);
}
