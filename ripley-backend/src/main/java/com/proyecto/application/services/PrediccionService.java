package com.proyecto.application.services;

import com.proyecto.application.dtos.PrediccionRequest;
import com.proyecto.application.dtos.PrediccionResponse;
import com.proyecto.domain.entities.PrediccionDemanda;
import com.proyecto.domain.interfaces.IPrediccionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * CAPA: APLICACIÓN — Services
 * CU-03 Predicción de Demanda
 */
@ApplicationScoped
public class PrediccionService {

    @Inject private IPrediccionRepository prediccionRepository;

    /** POST ejecutar modelo de predicción */
    public PrediccionResponse ejecutarModelo(PrediccionRequest dto) {

        // Paso 3: verificar datos históricos suficientes (mín 90 días)
        if (!prediccionRepository.tieneDatosHistoricos(dto.getIdProducto())) {
            throw new RuntimeException(
                    "datos incompletos — se requieren mínimo 90 días de historial");
        }

        // Paso 4: ejecutar modelo (simplificado — en prod usar ML real)
        int horizonteDias = dto.getHorizonteDias() != null
                ? dto.getHorizonteDias() : 30;

        PrediccionDemanda prediccion = new PrediccionDemanda(
                dto.getIdProducto(),
                100,  // demanda estimada base
                LocalDate.now().plusDays(horizonteDias),
                "Reposición recomendada en " + horizonteDias + " días",
                new BigDecimal("85.00")
        );

        // Paso 6: guardar resultados
        PrediccionDemanda guardada = prediccionRepository.guardar(prediccion);

        PrediccionResponse resp = new PrediccionResponse();
        resp.setIdProducto(guardada.getIdProducto());
        resp.setDemandaProyectada(
                BigDecimal.valueOf(guardada.getDemandaEstimada()));
        resp.setHorizonteDias(horizonteDias);
        resp.setFechaGeneracion(guardada.getFechaGeneracion());
        resp.setEstado("COMPLETADA");
        return resp;
    }

    /** GET obtener última predicción */
    public PrediccionResponse obtenerUltimaPrediccion() {
        // Retorna la última predicción disponible (simplificado)
        PrediccionResponse resp = new PrediccionResponse();
        resp.setEstado("SIN_DATOS");
        return resp;
    }

  /** Listar predicciones — requerido por GET /api/predicciones */
    public List<PrediccionResponse> listarPredicciones() {
    return prediccionRepository.listarTodas()
            .stream()
            .map(p -> {
                PrediccionResponse resp = new PrediccionResponse();
                resp.setIdProducto(p.getIdProducto());
                resp.setDemandaProyectada(BigDecimal.valueOf(p.getDemandaEstimada()));
                resp.setHorizonteDias(30);
                resp.setFechaGeneracion(p.getFechaGeneracion());
                resp.setEstado("COMPLETADA");
                return resp;
            })
            .collect(java.util.stream.Collectors.toList());
    }
}
