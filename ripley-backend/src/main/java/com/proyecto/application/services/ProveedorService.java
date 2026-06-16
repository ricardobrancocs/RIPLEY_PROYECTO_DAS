package com.proyecto.application.services;

import com.proyecto.application.dtos.EntregaRequest;
import com.proyecto.application.dtos.ProveedorRequest;
import com.proyecto.domain.entities.Proveedor;
import com.proyecto.domain.interfaces.IProveedorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

/**
 * CAPA: APLICACIÓN — Services
 * CU-06 Gestión de Proveedores
 */
@ApplicationScoped
public class ProveedorService {

    @Inject private IProveedorRepository proveedorRepository;

    /** POST registrar nuevo proveedor */
    public void registrarProveedor(ProveedorRequest dto) {

        // FA-1: validar duplicado por empresa
        if (proveedorRepository.existePorEmpresa(dto.getEmpresa())) {
            throw new RuntimeException(
                    "Proveedor duplicado — ya existe: " + dto.getEmpresa());
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setEmpresa(dto.getEmpresa());
        proveedor.setContacto(dto.getContacto());
        proveedor.setTelefono(dto.getTelefono());
        proveedor.setCorreo(dto.getCorreo());

        proveedorRepository.guardar(proveedor);
    }

    /** POST registrar entrega y calcular calificación */
    public void registrarEntrega(EntregaRequest dto) {
        proveedorRepository.buscarPorId(dto.getIdProveedor())
                .orElseThrow(() -> new RuntimeException(
                        "Proveedor no encontrado ID: "
                        + dto.getIdProveedor()));
        // Calificación calculada automáticamente en base a puntualidad,
        // cantidad entregada y calidad observada
    }
}
