package com.proyecto.application.dtos;

import java.math.BigDecimal;

public class EscenariosResponse {
    private EscenarioDTO alto;
    private EscenarioDTO medio;
    private EscenarioDTO bajo;

    public EscenariosResponse() {      
    }

    public EscenarioDTO getAlto() { return alto; }

    public void setAlto(EscenarioDTO v) { this.alto = v; }

    public EscenarioDTO getMedio() { return medio; }

    public void setMedio(EscenarioDTO v) { this.medio = v; }

    public EscenarioDTO getBajo() { return bajo; }

    public void setBajo(EscenarioDTO v) { this.bajo = v; }

    public static class EscenarioDTO {
        private String nombre;
        private BigDecimal costoTotal;
        private BigDecimal impactoFinanciero;
        private BigDecimal factorAjuste;

        public String getNombre() { return nombre; }

        public void setNombre(String v) { this.nombre = v; }

        public BigDecimal getCostoTotal() { return costoTotal; }

        public void setCostoTotal(BigDecimal v) { this.costoTotal = v; }

        public BigDecimal getImpactoFinanciero() { return impactoFinanciero; }

        public void setImpactoFinanciero(BigDecimal v) { this.impactoFinanciero = v; }

        public BigDecimal getFactorAjuste() { return factorAjuste; }
        
        public void setFactorAjuste(BigDecimal v) { this.factorAjuste = v; }
    }
}
