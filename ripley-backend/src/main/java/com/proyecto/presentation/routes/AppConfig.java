package com.proyecto.presentation.routes;

import com.proyecto.presentation.controllers.*;
import com.proyecto.presentation.CorsFilter;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class AppConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();

        classes.add(CorsFilter.class);

        classes.add(AuthController.class);
        classes.add(InventarioController.class);
        classes.add(VentaController.class);
        classes.add(AlertaController.class);
        classes.add(ProveedorController.class);
        classes.add(PrediccionController.class);
        classes.add(ReposicionController.class);
        classes.add(SimulacionController.class);
        classes.add(TrazabilidadController.class);
        classes.add(ClasificacionAbcController.class);
        classes.add(DevolucionController.class);

        return classes;
    }
}