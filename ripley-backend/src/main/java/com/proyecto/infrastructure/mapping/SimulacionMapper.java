package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Simulacion;
import java.math.BigDecimal;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Simulacion no tiene tabla propia en BD actual.
 * Se construye usando el Builder de Simulacion.
 */
public class SimulacionMapper {

    public static Simulacion toDomain(
    BigDecimal tipoCambio,
    BigDecimal inflacion,
    Integer horizonteDias,
    String usuario) {
        return new Simulacion.Builder()
                .tipoCambio(tipoCambio)
                .inflacion(inflacion)
                .horizonte(horizonteDias)
                .usuario(usuario)
                .build();
    }
}
