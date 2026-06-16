package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.EscenariosResponse;
import com.proyecto.application.dtos.SimulacionRequest;
import com.proyecto.application.services.SimulacionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** CAPA: PRESENTACIÓN — Controllers | CU-11 Simulación de Escenarios */
@Path("/simulaciones")               // ← CORREGIDO: era "/simulacion"
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SimulacionController {

    @Inject private SimulacionService simulacionService;

    /** POST /api/simulaciones — Generar los 3 escenarios financieros */
    @POST                             // ← CORREGIDO: era POST /simulacion/ejecutar
    public Response ejecutar(SimulacionRequest dto) {
        try {
            EscenariosResponse resp = simulacionService.generarEscenarios(dto);
            return Response.ok(resp).build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("rango"))
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            if (e.getMessage().contains("históricos"))
                return Response.status(422)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}