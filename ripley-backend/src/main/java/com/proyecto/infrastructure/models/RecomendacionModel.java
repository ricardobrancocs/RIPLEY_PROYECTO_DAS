package com.proyecto.infrastructure.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * CAPA: INFRAESTRUCTURA — Models
 * Tabla BD: recomendaciones
 * CU-04 Recomendación de Reposición
 */
@Entity
@Table(name = "recomendaciones")
public class RecomendacionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recomendacion")
    private Integer idRecomendacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoModel producto;

    @Column(name = "nombre_producto", length = 150)
    private String nombreProducto;

    @Column(name = "cantidad_sugerida", nullable = false)
    private Integer cantidadSugerida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proveedor_rec")
    private ProveedorModel proveedor;

    @Column(name = "nombre_proveedor", length = 150)
    private String nombreProveedor;

    @Column(name = "costo_estimado", precision = 10, scale = 2)
    private BigDecimal costoEstimado;

    @Column(name = "fecha_limite_pedido", length = 20)
    private String fechaLimitePedido;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "fecha_generacion", nullable = false)
    private LocalDateTime fechaGeneracion;

    public RecomendacionModel() {}

    public Integer      getIdRecomendacion()                         { return idRecomendacion; }
    public void         setIdRecomendacion(Integer v)                { this.idRecomendacion = v; }
    public ProductoModel getProducto()                               { return producto; }
    public void         setProducto(ProductoModel v)                 { this.producto = v; }
    public String       getNombreProducto()                          { return nombreProducto; }
    public void         setNombreProducto(String v)                  { this.nombreProducto = v; }
    public Integer      getCantidadSugerida()                        { return cantidadSugerida; }
    public void         setCantidadSugerida(Integer v)               { this.cantidadSugerida = v; }
    public ProveedorModel getProveedor()                             { return proveedor; }
    public void         setProveedor(ProveedorModel v)               { this.proveedor = v; }
    public String       getNombreProveedor()                         { return nombreProveedor; }
    public void         setNombreProveedor(String v)                 { this.nombreProveedor = v; }
    public BigDecimal   getCostoEstimado()                           { return costoEstimado; }
    public void         setCostoEstimado(BigDecimal v)               { this.costoEstimado = v; }
    public String       getFechaLimitePedido()                       { return fechaLimitePedido; }
    public void         setFechaLimitePedido(String v)               { this.fechaLimitePedido = v; }
    public String       getEstado()                                  { return estado; }
    public void         setEstado(String v)                          { this.estado = v; }
    public LocalDateTime getFechaGeneracion()                        { return fechaGeneracion; }
    public void         setFechaGeneracion(LocalDateTime v)          { this.fechaGeneracion = v; }
}
