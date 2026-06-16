package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.MovimientoRequest;
import com.proyecto.application.dtos.StockResponse;
import com.proyecto.application.services.InventarioService;
import jakarta.enterprise.context.RequestScoped;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** CAPA: PRESENTACIÓN — Controllers | CU-02 Gestión de Inventario */
@Path("/inventario")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InventarioController {

    @Inject private InventarioService inventarioService;

    /** POST /api/inventario/movimiento — Registrar movimiento de inventario */
    @POST @Path("/movimiento")
    public Response registrarMovimiento(MovimientoRequest dto) {
        try {
            inventarioService.registrarMovimiento(dto);
            return Response.status(Response.Status.CREATED)
                    .entity("{\"mensaje\": \"Movimiento registrado correctamente\"}").build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("stock negativo"))
                return Response.status(Response.Status.CONFLICT)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** GET /api/inventario/stock/{idProducto} — Consultar stock actual */
    @GET @Path("/stock/{idProducto}")
    public Response consultarStock(@PathParam("idProducto") Integer idProducto) {
        try {
            StockResponse resp = inventarioService.consultarStock(idProducto);
            return Response.ok(resp).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /** GET /api/inventario/stock – Obtener todos los stocks */
    @GET @Path("/stock")
    public Response obtenerTodoElStock() {
        try {
            List<StockResponse> stocks = inventarioService.obtenerTodoElStock();
            return Response.ok(stocks).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}
