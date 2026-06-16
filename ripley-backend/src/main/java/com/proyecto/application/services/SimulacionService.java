package com.proyecto.application.services;

import com.proyecto.application.dtos.EscenariosResponse;
import com.proyecto.application.dtos.SimulacionRequest;
import com.proyecto.domain.entities.Simulacion;
import com.proyecto.domain.interfaces.ISimulacionRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;

/**
 * CAPA: APLICACIÓN — Services
 * CU-11 Simulación de Escenarios Financieros
 */
@ApplicationScoped
public class SimulacionService {

    @Inject private ISimulacionRepository simulacionRepository;

    /** POST generar 3 escenarios financieros */
    public EscenariosResponse generarEscenarios(SimulacionRequest dto) {

        // Validar variables (FA-1a)
        if (dto.getTipoCambio() == null
                || dto.getTipoCambio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException(
                    "Variables fuera de rango — tipo de cambio debe ser > 0");
        }

        // Builder construye el escenario base
        Simulacion base = new Simulacion.Builder()
                .tipoCambio(dto.getTipoCambio())
                .inflacion(dto.getInflacionProyectada() != null
                        ? dto.getInflacionProyectada() : BigDecimal.ZERO)
                .horizonte(dto.getHorizonteDias() != null
                        ? dto.getHorizonteDias() : 30)
                .usuario("sistema")
                .build();

        // Prototype genera los 3 escenarios
        Simulacion alto  = base.escenarioAlto();
        Simulacion medio = base.escenarioMedio();
        Simulacion bajo  = base.escenarioBajo();

        // Calcular costos por escenario
        calcularCostos(alto);
        calcularCostos(medio);
        calcularCostos(bajo);

        // Guardar resultados
        simulacionRepository.guardar(alto);
        simulacionRepository.guardar(medio);
        simulacionRepository.guardar(bajo);

        // Construir respuesta
        EscenariosResponse resp = new EscenariosResponse();
        resp.setAlto(toDTO(alto));
        resp.setMedio(toDTO(medio));
        resp.setBajo(toDTO(bajo));
        return resp;
    }

    private void calcularCostos(Simulacion s) {
        BigDecimal costoBase = new BigDecimal("10000.00");
        BigDecimal costo = costoBase.multiply(s.getFactorAjuste())
                .multiply(s.getTipoCambio());
        s.setCostoTotal(costo);
        s.setImpactoFinanciero(
                costo.subtract(costoBase.multiply(s.getTipoCambio())));
    }

    private EscenariosResponse.EscenarioDTO toDTO(Simulacion s) {
        EscenariosResponse.EscenarioDTO dto =
                new EscenariosResponse.EscenarioDTO();
        dto.setNombre(s.getNombreEscenario());
        dto.setCostoTotal(s.getCostoTotal());
        dto.setImpactoFinanciero(s.getImpactoFinanciero());
        dto.setFactorAjuste(s.getFactorAjuste());
        return dto;
    }
}
