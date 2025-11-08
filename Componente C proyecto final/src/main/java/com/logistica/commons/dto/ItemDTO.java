package com.logistica.commons.dto;

import java.io.Serializable;

/**
 * Data Transfer Object para items/productos
 * Usado tanto en Pedidos (Componente A) como en Facturas (Componente B)
 */
public class ItemDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
    
    // Constructor vacío
    public ItemDTO() {
    }
    
    // Constructor completo
    public ItemDTO(String nombre, Integer cantidad, Double precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    // Método privado para recalcular subtotal automáticamente
    private void calcularSubtotal() {
        if (cantidad != null && precioUnitario != null) {
            this.subtotal = cantidad * precioUnitario;
        }
    }
    
    @Override
    public String toString() {
        return "ItemDTO{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}