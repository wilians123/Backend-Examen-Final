package com.umg.edu.gt.dev.demo.entity;

import jakarta.persistence.*;

/**
 * Entidad JPA que representa los items de un pedido
 * Relación: Muchos Items pertenecen a Un Pedido
 */
@Entity
@Table(name = "items_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_nombre", nullable = false, length = 100)
    private String productoNombre;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "precio_unitario", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    // Relación: Muchos items pertenecen a un pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    private PedidoEntity pedido;

    // Constructor vacío
    public ItemPedidoEntity() {
    }

    // Constructor con parámetros
    public ItemPedidoEntity(String productoNombre, Integer cantidad, Double precioUnitario) {
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        calcularSubtotal();
    }

    // Método para calcular subtotal automáticamente
    @PrePersist
    @PreUpdate
    protected void calcularSubtotal() {
        if (cantidad != null && precioUnitario != null) {
            this.subtotal = cantidad * precioUnitario;
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductoNombre() {
        return productoNombre;
    }

    public void setProductoNombre(String productoNombre) {
        this.productoNombre = productoNombre;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
        calcularSubtotal();
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "ItemPedidoEntity{" +
                "id=" + id +
                ", productoNombre='" + productoNombre + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}