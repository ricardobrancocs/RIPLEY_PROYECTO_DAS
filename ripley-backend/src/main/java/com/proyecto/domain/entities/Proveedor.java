package com.proyecto.domain.entities;

/**
 * CAPA: DOMINIO — Entidades
 * Tabla BD: proveedores
 * Java puro — sin frameworks
 *
 * PATRÓN: Singleton (instancia única del catálogo de proveedores
 *         para evitar cargas duplicadas — aplicado en el Service,
 *         la entidad en sí es un POJO limpio)
 */
public class Proveedor {

    // ── Campos BD ────────────────────────────────
    private Integer idProveedor;
    private String  empresa;    // varchar(150)
    private String  contacto;   // varchar(100)
    private String  telefono;   // varchar(20)
    private String  correo;     // varchar(100)

    // ── Constructores ────────────────────────────
    public Proveedor() {}

    public Proveedor(
        Integer idProveedor, 
        String empresa,
        String contacto, 
        String telefono, 
        String correo) {
        this.idProveedor = idProveedor;
        this.empresa     = empresa;
        this.contacto    = contacto;
        this.telefono    = telefono;
        this.correo      = correo;
    }

    // ── Lógica de negocio ────────────────────────

    /** CU-06: valida que el RUC/empresa no esté vacío antes de registrar */
    public boolean esDatosCompletos() {
        return empresa != null && !empresa.isBlank() && contacto != null && !contacto.isBlank() 
        && telefono != null && !telefono.isBlank() && correo != null && !correo.isBlank();
    }

    // ── Getters y Setters ────────────────────────
    public Integer getIdProveedor()              { return idProveedor; }
    public void    setIdProveedor(Integer v)     { this.idProveedor = v; }
    public String  getEmpresa()                  { return empresa; }
    public void    setEmpresa(String v)          { this.empresa = v; }
    public String  getContacto()                 { return contacto; }
    public void    setContacto(String v)         { this.contacto = v; }
    public String  getTelefono()                 { return telefono; }
    public void    setTelefono(String v)         { this.telefono = v; }
    public String  getCorreo()                   { return correo; }
    public void    setCorreo(String v)           { this.correo = v; }
}
