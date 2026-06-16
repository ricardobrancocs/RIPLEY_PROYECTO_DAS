package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Recomendacion;
import java.math.BigDecimal;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Recomendacion no tiene tabla propia en BD actual.
 * Se construye a partir de predicciones_ia + productos + proveedores.
 */
public class RecomendacionMapper {

    public static Recomendacion toDomain(Integer idProducto, String nombreProducto,
                                          Integer cantidad, Integer idProveedor,
                                          String nombreProveedor, BigDecimal costo,
                                          String fechaLimite) {
        return new Recomendacion.Builder()
                .idProducto(idProducto)
                .nombreProducto(nombreProducto)
                .cantidadSugerida(cantidad)
                .idProveedor(idProveedor)
                .nombreProveedor(nombreProveedor)
                .costoEstimado(costo)
                .fechaLimitePedido(fechaLimite)
                .build();
    }
}
