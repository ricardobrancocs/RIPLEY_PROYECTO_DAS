package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.HistorialResponse;
import com.proyecto.application.dtos.MovimientoTrazabilidadRequest;
import com.proyecto.application.services.TrazabilidadService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/** CAPA: PRESENTACIÓN — Controllers | CU-09 Trazabilidad de Productos */
@Path("/trazabilidad")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrazabilidadController {

    @Inject private TrazabilidadService trazabilidadService;

    /** GET /api/trazabilidad — Obtener historial general */
    @GET                              // ← NUEVO: el frontend hace GET /trazabilidad
    public Response listar() {
        try {
            List<HistorialResponse> lista = trazabilidadService.listarHistorial();
            return Response.ok(lista).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** POST /api/trazabilidad — Registrar movimiento físico */
    @POST                             // ← CORREGIDO: era POST /trazabilidad/movimiento
    public Response registrar(MovimientoTrazabilidadRequest dto) {
        try {
            trazabilidadService.registrarMovimiento(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\": \"Movimiento registrado\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** GET /api/trazabilidad/{productoId}/historial — Historial por producto */
    @GET @Path("/{productoId}/historial")
    public Response historialProducto(@PathParam("productoId") Integer productoId) {
        try {
            HistorialResponse resp = trazabilidadService.consultarHistorial(productoId);
            return Response.ok(resp).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}