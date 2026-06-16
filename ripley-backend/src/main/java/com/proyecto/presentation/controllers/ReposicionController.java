package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.RecomendacionResponse;
import com.proyecto.application.services.ReposicionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/** CAPA: PRESENTACIÓN — Controllers | CU-04 Recomendación de Reposición */
@Path("/reposicion")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReposicionController {

    @Inject private ReposicionService reposicionService;

    /** GET /api/reposicion/recomendaciones — Listar recomendaciones pendientes */
    @GET @Path("/recomendaciones")
    public Response listarRecomendaciones() {
        try {
            List<RecomendacionResponse> lista = reposicionService.obtenerRecomendaciones();
            return Response.ok(lista).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** POST /api/reposicion/aprobar/{id} — Aprobar una recomendación */
    @POST @Path("/aprobar/{id}")
    public Response aprobar(@PathParam("id") Integer id) {
        try {
            reposicionService.aprobarRecomendacion(id);
            return Response.ok("{\"mensaje\": \"Recomendación aprobada\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }
}
