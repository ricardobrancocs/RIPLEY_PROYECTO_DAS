package com.proyecto.presentation.filters;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import java.io.IOException;

/**
 * CAPA: PRESENTACIÓN — Filters
 * JWT deshabilitado temporalmente para desarrollo
 * Activar en producción agregando @Provider
 */
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        // Deshabilitado — activar en producción
    }
}