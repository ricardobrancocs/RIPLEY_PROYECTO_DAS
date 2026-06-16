package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.AlertaResponse;
import com.proyecto.application.services.AlertaService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/** CAPA: PRESENTACIÓN — Controllers | CU-05 Alertas Inteligentes */
@Path("/alertas")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlertaController {

    @Inject private AlertaService alertaService;

    /** GET /api/alertas — Listar alertas activas */
    @GET
    public Response listarAlertas() {
        try {
            List<AlertaResponse> alertas = alertaService.obtenerActivas();
            return Response.ok(alertas).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** PUT /api/alertas/{id}/atender — Marcar alerta como atendida */
    @PUT @Path("/{id}/atender")
    public Response atender(@PathParam("id") Integer id) {
        try {
            alertaService.atenderAlerta(id);
            return Response.ok("{\"mensaje\": \"Alerta atendida\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /** PUT /api/alertas/{id}/descartar — Descartar alerta con justificación */
    @PUT @Path("/{id}/descartar")
    public Response descartar(@PathParam("id") Integer id,
        @QueryParam("justificacion") String justificacion) {
        try {
            alertaService.descartarAlerta(id, justificacion);
            return Response.ok("{\"mensaje\": \"Alerta descartada\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
