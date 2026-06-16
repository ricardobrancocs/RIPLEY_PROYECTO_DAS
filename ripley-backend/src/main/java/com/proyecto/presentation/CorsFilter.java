package com.proyecto.presentation;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

/**
 * CAPA: PRESENTACIÓN — Filtro CORS
 * Permite que Angular (localhost:4200) consuma la API.
 * Agregar este archivo en el mismo paquete que AppConfig.
 */
@Provider
@PreMatching
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext req) {
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            req.abortWith(
                jakarta.ws.rs.core.Response.ok().build()
            );
        }
    }

    @Override
    public void filter(ContainerRequestContext req, ContainerResponseContext res) {
        res.getHeaders().add("Access-Control-Allow-Origin",  "http://localhost:4200");
        res.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        res.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        res.getHeaders().add("Access-Control-Allow-Credentials", "true");
    }
}
