package com.proyecto.presentation.controllers;

import com.proyecto.application.dtos.AuthResponse;
import com.proyecto.application.dtos.LoginRequest;
import com.proyecto.application.services.AuthService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/** CAPA: PRESENTACIÓN — Controllers | CU-07 Control de Acceso */
@Path("/auth")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject private AuthService authService;

    /** POST /api/auth/login — Iniciar sesión */
    @POST @Path("/login")
    public Response login(LoginRequest dto) {
        try {
            AuthResponse resp = authService.login(dto);
            return Response.ok(resp).build();
        } catch (RuntimeException e) {
            if (e.getMessage().contains("bloqueada"))
                return Response.status(423)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            if (e.getMessage().contains("incorrectas"))
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\": \"" + e.getMessage() + "\"}").build();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    /** POST /api/auth/logout — Cerrar sesión */
    @POST @Path("/logout")
    public Response logout(@HeaderParam("Authorization") String token) {
        try {
            authService.logout(token);
            return Response.ok("{\"mensaje\": \"Sesión cerrada\"}").build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"Error interno\"}").build();
        }
    }
}
