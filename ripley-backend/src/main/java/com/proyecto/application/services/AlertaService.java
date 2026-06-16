package com.proyecto.application.services;

import com.proyecto.application.dtos.AlertaResponse;
import com.proyecto.domain.entities.Alerta;
import com.proyecto.domain.interfaces.IAlertaRepository;
import com.proyecto.domain.interfaces.IProductoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

/**
 * CAPA: APLICACIÓN — Services
 * CU-05 Alertas Inteligentes
 */
@ApplicationScoped
public class AlertaService {

    @Inject private IAlertaRepository alertaRepository;
    @Inject private IProductoRepository productoRepository;

    /** GET alertas activas */
    public List<AlertaResponse> obtenerActivas() {
        return alertaRepository.listarActivas()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /** PUT atender alerta */
    public void atenderAlerta(Integer idAlerta) {
        Alerta alerta = alertaRepository.buscarPorId(idAlerta)
                .orElseThrow(() -> new RuntimeException(
                        "Alerta no encontrada ID: " + idAlerta));
        alerta.atender();
        alertaRepository.actualizarEstado(idAlerta, "T");
    }

    /** PUT descartar alerta */
    public void descartarAlerta(Integer idAlerta, String justificacion) {
        Alerta alerta = alertaRepository.buscarPorId(idAlerta)
                .orElseThrow(() -> new RuntimeException(
                        "Alerta no encontrada ID: " + idAlerta));
        alerta.descartar();
        alertaRepository.actualizarEstado(idAlerta, "D");
    }

    /** Genera alerta según nivel de stock */
    public void evaluarYGenerarAlerta(Integer idProducto) {
        productoRepository.buscarPorId(idProducto).ifPresent(producto -> {
            Alerta alerta;
            if (producto.getStockActual() <= 0) {
                alerta = Alerta.critica(idProducto, producto.getNombre());
            } else if (producto.estaBajoStockMinimo()) {
                alerta = Alerta.advertencia(idProducto, producto.getNombre());
            } else {
                alerta = Alerta.informativa(idProducto, producto.getNombre());
            }
            alertaRepository.guardar(alerta);
        });
    }

    private AlertaResponse toResponse(Alerta a) {
        AlertaResponse r = new AlertaResponse();
        r.setId(a.getIdAlerta());
        r.setIdProducto(a.getIdProducto());
        r.setNivel(a.getTipoAlerta());
        r.setMensaje(a.getMensaje());
        r.setEstado(a.getEstado());
        r.setFechaGeneracion(a.getFechaAlerta());
        return r;
    }
}
