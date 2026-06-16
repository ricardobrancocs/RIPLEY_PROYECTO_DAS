package com.proyecto.infrastructure.models;

import jakarta.persistence.*;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: proveedores
 */
@Entity
@Table(name = "proveedores")
public class ProveedorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proveedor")
    private Integer idProveedor;

    @Column(name = "empresa", nullable = false, length = 150)
    private String empresa;

    @Column(name = "contacto", length = 100)
    private String contacto;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "correo", length = 100)
    private String correo;

    // ── Constructores ─────────────────────────
    public ProveedorModel() {}

    // ── Getters y Setters ─────────────────────
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
