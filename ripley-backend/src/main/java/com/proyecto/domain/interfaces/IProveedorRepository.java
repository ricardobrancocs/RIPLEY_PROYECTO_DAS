package com.proyecto.domain.interfaces;

import com.proyecto.domain.entities.Proveedor;
import java.util.List;
import java.util.Optional;

/**
 * CAPA: DOMINIO — Interfaces (puerto)
 * Tabla BD: proveedores
 * CU-06 Gestión de Proveedores
 */
public interface IProveedorRepository {

    /** Guarda un nuevo proveedor */
    Proveedor guardar(Proveedor proveedor);

    /** Busca un proveedor por su ID */
    Optional<Proveedor> buscarPorId(Integer idProveedor);

    /** Busca un proveedor por nombre de empresa (para validar duplicados) */
    Optional<Proveedor> buscarPorEmpresa(String empresa);

    /** Lista todos los proveedores registrados */
    List<Proveedor> listarTodos();

    /** Actualiza los datos de un proveedor existente */
    Proveedor actualizar(Proveedor proveedor);

    /** Verifica si ya existe un proveedor con esa empresa (CU-06 FA-1) */
    boolean existePorEmpresa(String empresa);
}
