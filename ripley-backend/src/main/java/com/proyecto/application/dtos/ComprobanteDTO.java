package com.proyecto.application.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: APLICACION — DTOs
 * Data Transfer Object para la respuesta del comprobante generado
 * Corresponde al Subflujo P-2 del CU-Venta
 */
public class ComprobanteDTO {

    private Integer idVenta;
    private LocalDateTime fechaVenta;
    private BigDecimal total;
    private String estado;
    private String mensajeEstado; // "Comprobante generado exitosamente"
    private byte[] archivoPDF;

    // ═══════════════════════════════════════
    // Constructor vacío
    // ═══════════════════════════════════════
    public ComprobanteDTO() {}

    // ═══════════════════════════════════════
    // Constructor de respuesta exitosa
    // ═══════════════════════════════════════
    public ComprobanteDTO(Integer idVenta, LocalDateTime fechaVenta,
        BigDecimal total, String estado, byte[] archivoPDF) {
        this.idVenta       = idVenta;
        this.fechaVenta    = fechaVenta;
        this.total         = total;
        this.estado        = estado;
        this.archivoPDF    = archivoPDF;
        this.mensajeEstado = "Comprobante generado exitosamente";
    }

    // ═══════════════════════════════════════
    // Getters y Setters
    // ═══════════════════════════════════════
    public Integer getIdVenta()                  { return idVenta; }
    public void setIdVenta(Integer idVenta)      { this.idVenta = idVenta; }

    public LocalDateTime getFechaVenta()                     { return fechaVenta; }
    public void setFechaVenta(LocalDateTime fechaVenta)      { this.fechaVenta = fechaVenta; }

    public BigDecimal getTotal()                 { return total; }
    public void setTotal(BigDecimal total)       { this.total = total; }

    public String getEstado()                    { return estado; }
    public void setEstado(String estado)         { this.estado = estado; }

    public String getMensajeEstado()                     { return mensajeEstado; }
    public void setMensajeEstado(String mensajeEstado)   { this.mensajeEstado = mensajeEstado; }

    public byte[] getArchivoPDF()                { return archivoPDF; }
    public void setArchivoPDF(byte[] archivoPDF) { this.archivoPDF = archivoPDF; }
}