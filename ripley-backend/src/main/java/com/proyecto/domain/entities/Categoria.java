package com.proyecto.domain.entities;

/**
 * CAPA: DOMINIO — Entidades
 * Tabla BD: categorias
 * Clase Java pura — sin frameworks
 *
 * id_categoria serial PK
 * nombre       varchar(100)
 * descripcion  text
 *
 * Usada como referencia en Producto.idCategoria
 */
public class Categoria {

    private Integer idCategoria;
    private String  nombre;
    private String  descripcion;

    public Categoria() {}

    public Categoria(Integer idCategoria, String nombre, String descripcion) {
        this.idCategoria = idCategoria;
        this.nombre      = nombre;
        this.descripcion = descripcion;
    }

    public Integer getIdCategoria()                    { return idCategoria; }
    public void    setIdCategoria(Integer v)           { this.idCategoria = v; }
    public String  getNombre()                         { return nombre; }
    public void    setNombre(String v)                 { this.nombre = v; }
    public String  getDescripcion()                    { return descripcion; }
    public void    setDescripcion(String v)            { this.descripcion = v; }
}