package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.EntregaRequest;
import com.proyecto.application.dtos.ProveedorRequest;
import com.proyecto.application.services.ProveedorService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** CAPA: PRESENTACIÓN — Controllers | CU-06 Gestión de Proveedores */
@Path("/proveedores")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProveedorController {

    @Inject private ProveedorService proveedorService;

    /** POST /api/proveedores — Registrar nuevo proveedor */
    @POST
    public Response registrar(ProveedorRequest dto) {
        try {
            proveedorService.registrarProveedor(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\": \"Proveedor registrado\"}").build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("duplicado"))
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** POST /api/proveedores/{id}/entregas — Registrar entrega de proveedor */
    @POST @Path("/{id}/entregas")
    public Response registrarEntrega(@PathParam("id") Integer id, EntregaRequest dto) {
        try {
            dto.setIdProveedor(id);
            proveedorService.registrarEntrega(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\": \"Entrega registrada y calificación actualizada\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
