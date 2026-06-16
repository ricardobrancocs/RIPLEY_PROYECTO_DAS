package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Categoria;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tabla BD: categorias
 */
public interface ICategoriaRepository {

    Optional<Categoria> buscarPorId(Integer idCategoria);
    List<Categoria>     listarTodas();
    Categoria           guardar(Categoria categoria);
}