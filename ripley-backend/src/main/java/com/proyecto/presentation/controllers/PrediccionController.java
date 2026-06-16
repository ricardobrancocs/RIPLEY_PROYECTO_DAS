package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.PrediccionRequest;
import com.proyecto.application.dtos.PrediccionResponse;
import com.proyecto.application.services.PrediccionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

/** CAPA: PRESENTACIÓN — Controllers | CU-03 Predicción de Demanda */
@Path("/predicciones")               // ← CORREGIDO: era "/prediccion"
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PrediccionController {

    @Inject private PrediccionService prediccionService;

    /** GET /api/predicciones — Obtener lista de predicciones */
    @GET                              // ← CORREGIDO: era GET /prediccion/resultados
    public Response listar() {
        try {
            List<PrediccionResponse> lista = prediccionService.listarPredicciones();
            return Response.ok(lista).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /** POST /api/predicciones — Ejecutar modelo de predicción */
    @POST                             // ← CORREGIDO: era POST /prediccion/ejecutar
    public Response ejecutar(PrediccionRequest dto) {
        try {
            PrediccionResponse resp = prediccionService.ejecutarModelo(dto);
            return Response.status(Response.Status.CREATED).entity(resp).build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("datos incompletos"))
                return Response.status(422)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}