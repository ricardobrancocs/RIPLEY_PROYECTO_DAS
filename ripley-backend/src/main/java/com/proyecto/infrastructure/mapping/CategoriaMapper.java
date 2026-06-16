package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Categoria;
import com.proyecto.infrastructure.models.CategoriaModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte Categoria (dominio) ↔ CategoriaModel (JPA)
 * Tabla BD: categorias
 */
public class CategoriaMapper {

    public static CategoriaModel toModel(Categoria e) {
        if (e == null) return null;
        CategoriaModel m = new CategoriaModel();
        m.setIdCategoria(e.getIdCategoria());
        m.setNombre(e.getNombre());
        m.setDescripcion(e.getDescripcion());
        return m;
    }

    public static Categoria toDomain(CategoriaModel m) {
        if (m == null) return null;
        Categoria e = new Categoria();
        e.setIdCategoria(m.getIdCategoria());
        e.setNombre(m.getNombre());
        e.setDescripcion(m.getDescripcion());
        return e;
    }
}
