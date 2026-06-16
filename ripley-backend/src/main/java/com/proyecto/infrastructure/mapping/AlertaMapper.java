package com.proyecto.infrastructure.mapping;

import com.proyecto.domain.entities.Alerta;
import com.proyecto.infrastructure.models.AlertaStockModel;

/**
 * CAPA: INFRAESTRUCTURA — Mapping
 * Convierte Alerta (dominio) ↔ AlertaStockModel (JPA)
 * Tabla BD: alertas_stock
 */
public class AlertaMapper {

    public static AlertaStockModel toModel(Alerta e) {
        if (e == null) return null;
        AlertaStockModel m = new AlertaStockModel();
        m.setIdAlerta(e.getIdAlerta());
        m.setTipoAlerta(e.getTipoAlerta());
        m.setMensaje(e.getMensaje());
        m.setFechaAlerta(e.getFechaAlerta());
        m.setEstado(e.getEstado());
        // idProducto se setea via ProductoModel en el repository
        return m;
    }

    public static Alerta toDomain(AlertaStockModel m) {
        if (m == null) return null;
        Alerta e = new Alerta();
        e.setIdAlerta(m.getIdAlerta());
        e.setTipoAlerta(m.getTipoAlerta());
        e.setMensaje(m.getMensaje());
        e.setFechaAlerta(m.getFechaAlerta());
        e.setEstado(m.getEstado());
        if (m.getProducto() != null)
            e.setIdProducto(m.getProducto().getIdProducto());
        return e;
    }
}
