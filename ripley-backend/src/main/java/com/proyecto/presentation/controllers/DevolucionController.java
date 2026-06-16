package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.DevolucionRequest;
import com.proyecto.application.dtos.DevolucionResponse;
import com.proyecto.application.services.DevolucionService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** CAPA: PRESENTACIÓN — Controllers | CU-08 Proceso de Devoluciones */
@Path("/devoluciones")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DevolucionController {

    @Inject private DevolucionService devolucionService;

    /** POST /api/devoluciones — Registrar devolución */
    @POST
    public Response registrar(DevolucionRequest dto) {
        try {
            DevolucionResponse resp = devolucionService.registrarDevolucion(dto);
            return Response.status(Response.Status.CREATED).entity(resp).build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no válido"))
                return Response.status(422)   // HTTP 422 Unprocessable Entity
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}