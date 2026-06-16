package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Proveedor;
import com.proyecto.infrastructure.models.ProveedorModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte Proveedor (dominio) ↔ ProveedorModel (JPA)
 * Tabla BD: proveedores
 */
public class ProveedorMapper {

    public static ProveedorModel toModel(Proveedor e) {
        if (e == null) return null;
        ProveedorModel m = new ProveedorModel();
        m.setIdProveedor(e.getIdProveedor());
        m.setEmpresa(e.getEmpresa());
        m.setContacto(e.getContacto());
        m.setTelefono(e.getTelefono());
        m.setCorreo(e.getCorreo());
        return m;
    }

    public static Proveedor toDomain(ProveedorModel m) {
        if (m == null) return null;
        Proveedor e = new Proveedor();
        e.setIdProveedor(m.getIdProveedor());
        e.setEmpresa(m.getEmpresa());
        e.setContacto(m.getContacto());
        e.setTelefono(m.getTelefono());
        e.setCorreo(m.getCorreo());
        return e;
    }
}
