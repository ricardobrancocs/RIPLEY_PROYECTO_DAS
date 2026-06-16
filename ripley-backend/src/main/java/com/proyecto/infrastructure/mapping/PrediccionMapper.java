package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.PrediccionDemanda;
import com.proyecto.infrastructure.models.PrediccionIaModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte PrediccionDemanda (dominio) ↔ PrediccionIaModel (JPA)
 * Tabla BD: predicciones_ia
 */
public class PrediccionMapper {

    public static PrediccionIaModel toModel(PrediccionDemanda e) {
        if (e == null) return null;
        PrediccionIaModel m = new PrediccionIaModel();
        m.setIdPrediccion(e.getIdPrediccion());
        m.setDemandaEstimada(e.getDemandaEstimada());
        m.setFechaPrediccion(e.getFechaPrediccion());
        m.setRecomendacion(e.getRecomendacion());
        m.setNivelConfianza(e.getNivelConfianza());
        // idProducto se setea via ProductoModel en el repository
        return m;
    }

    public static PrediccionDemanda toDomain(PrediccionIaModel m) {
        if (m == null) return null;
        PrediccionDemanda e = new PrediccionDemanda();
        e.setIdPrediccion(m.getIdPrediccion());
        e.setDemandaEstimada(m.getDemandaEstimada());
        e.setFechaPrediccion(m.getFechaPrediccion());
        e.setRecomendacion(m.getRecomendacion());
        e.setNivelConfianza(m.getNivelConfianza());
        if (m.getProducto() != null)
            e.setIdProducto(m.getProducto().getIdProducto());
        return e;
    }
}
