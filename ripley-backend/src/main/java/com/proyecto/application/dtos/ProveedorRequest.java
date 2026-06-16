package com.proyecto.application.dtos;
public class ProveedorRequest {
    private String empresa;
    private String contacto;
    private String telefono;
    private String correo;
    private Integer leadTimeDias;

    public String getEmpresa() { return empresa; }

    public void setEmpresa(String v) { this.empresa = v; }

    public String getContacto() { return contacto; }

    public void setContacto(String v) { this.contacto = v; }

    public String getTelefono() { return telefono; }

    public void setTelefono(String v) { this.telefono = v; }

    public String getCorreo() { return correo; }

    public void setCorreo(String v) { this.correo = v; }

    public Integer getLeadTimeDias() { return leadTimeDias; }

    public void setLeadTimeDias(Integer v) { this.leadTimeDias = v; }
}
