package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.ComprobanteDTO;
import com.proyecto.application.dtos.DevolucionRequest;
import com.proyecto.application.dtos.DevolucionResponse;
import com.proyecto.application.dtos.RegistrarPagoDTO;
import com.proyecto.application.services.DevolucionService;
import com.proyecto.application.services.VentaService;
import com.proyecto.domain.entities.Venta;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/** CAPA: PRESENTACIÓN — Controllers | Ventas + Devoluciones */
@Path("/ventas")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VentaController {

    @Inject private VentaService ventaService;
    @Inject private DevolucionService devolucionService;

    /** GET /api/ventas — Listar todas las ventas */
    @GET                              // ← NUEVO: el frontend hace GET /ventas
    public Response listarVentas() {
        try {
            List<Venta> ventas = ventaService.listarVentas();
            return Response.ok(ventas).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** GET /api/ventas/{id} — Consultar venta por ID */
    @GET @Path("/{id}")
    public Response getVenta(@PathParam("id") Integer id) {
        try {
            Optional<Venta> venta = ventaService.obtenerVenta(id);
            if (venta.isEmpty())
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Venta no encontrada ID: " + id + "\"}").build();
            return Response.ok(venta.get()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno del servidor\"}").build();
        }
    }

    /** POST /api/ventas/{id}/pago — Registrar pago */
    @POST @Path("/{id}/pago")
    public Response registrarPago(@PathParam("id") Integer id, RegistrarPagoDTO dto) {
        try {
            dto.setIdProforma(id);
            ComprobanteDTO comprobante = ventaService.registrarPago(dto);
            return Response.ok(comprobante).build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Pago inválido"))
                return Response.status(402)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            if (e.getMessage().contains("no encontrada"))
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
        }
    }

    /** POST /api/ventas/devolucion — Registrar devolución desde ventas */
    @POST @Path("/devolucion")        // ← NUEVO: el frontend llama a /ventas/devolucion
    public Response registrarDevolucion(DevolucionRequest dto) {
        try {
            DevolucionResponse resp = devolucionService.registrarDevolucion(dto);
            return Response.status(Response.Status.CREATED).entity(resp).build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("no válido"))
                return Response.status(422)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}