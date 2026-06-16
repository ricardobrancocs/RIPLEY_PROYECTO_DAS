package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: categorias
 */
@Entity
@Table(name = "categorias")
public class CategoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    // 1:N con productos
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<ProductoModel> productos;

    // ── Constructores ─────────────────────────
    public CategoriaModel() {}

    // ── Getters y Setters ─────────────────────
    public Integer              getIdCategoria()              { return idCategoria; }
    public void                 setIdCategoria(Integer v)     { this.idCategoria = v; }
    public String               getNombre()                   { return nombre; }
    public void                 setNombre(String v)           { this.nombre = v; }
    public String               getDescripcion()              { return descripcion; }
    public void                 setDescripcion(String v)      { this.descripcion = v; }
    public List<ProductoModel>  getProductos()                { return productos; }
    public void                 setProductos(List<ProductoModel> v) { this.productos = v; }
}
