package com.umg.edu.gt.dev.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad JPA que representa la tabla 'pedidos' en MariaDB
 * Relación: Un Pedido tiene Muchos Items
 */
@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id", nullable = false)
    private Long clienteId;

    @Column(name = "codigo_pedido", unique = true, length = 50)
    private String codigoPedido;

    @Column(name = "fecha_pedido", nullable = false, updatable = false)
    private LocalDateTime fechaPedido;

    @Column(name = "fecha_entrega_estimada")
    private LocalDateTime fechaEntregaEstimada;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double impuestos;

    @Column(nullable = false)
    private Double total;

    @Column(length = 500)
    private String observaciones;

    // Relación: Un pedido tiene muchos items
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ItemPedidoEntity> items = new ArrayList<>();

    // Constructor vacío
    public PedidoEntity() {
    }

    // Constructor con parámetros básicos
    public PedidoEntity(Long clienteId, String codigoPedido) {
        this.clienteId = clienteId;
        this.codigoPedido = codigoPedido;
        this.fechaPedido = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (fechaPedido == null) {
            fechaPedido = LocalDateTime.now();
        }
    }

    // Método helper para agregar items
    public void agregarItem(ItemPedidoEntity item) {
        items.add(item);
        item.setPedido(this);
    }

    // Método helper para remover items
    public void removerItem(ItemPedidoEntity item) {
        items.remove(item);
        item.setPedido(null);
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public String getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(String codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public LocalDateTime getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDateTime fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Double impuestos) {
        this.impuestos = impuestos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ItemPedidoEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemPedidoEntity> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id=" + id +
                ", clienteId=" + clienteId +
                ", codigoPedido='" + codigoPedido + '\'' +
                ", total=" + total +
                '}';
    }
}