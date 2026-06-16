package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.ClasificacionResponse;
import com.proyecto.application.services.ClasificacionAbcService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/** CAPA: PRESENTACIÓN — Controllers | CU-10 Clasificación ABC */
@Path("/clasificacion-abc")          // ← CORREGIDO: era "/clasificacion"
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClasificacionAbcController {

    @Inject private ClasificacionAbcService clasificacionService;

    /** GET /api/clasificacion-abc — Obtener resultados ABC */
    @GET                              // ← CORREGIDO: era GET /clasificacion/resultados
    public Response resultados() {
        try {
            List<ClasificacionResponse> lista = clasificacionService.obtenerResultados();
            return Response.ok(lista).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** POST /api/clasificacion-abc/ejecutar — Ejecutar análisis ABC */
    @POST @Path("/ejecutar")
    public Response ejecutar() {
        try {
            clasificacionService.ejecutarClasificacion();
            return Response.ok("{\"mensaje\": \"Clasificación ABC completada\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}