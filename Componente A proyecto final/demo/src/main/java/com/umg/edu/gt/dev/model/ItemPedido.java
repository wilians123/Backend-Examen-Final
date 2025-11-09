package com.umg.edu.gt.dev.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ItemPedido
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T23:39:27.831609400-06:00[America/Guatemala]")
public class ItemPedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("productoNombre")
  private String productoNombre;

  @JsonProperty("cantidad")
  private Integer cantidad;

  @JsonProperty("precioUnitario")
  private Double precioUnitario;

  @JsonProperty("subtotal")
  private Double subtotal;

  public ItemPedido id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ItemPedido productoNombre(String productoNombre) {
    this.productoNombre = productoNombre;
    return this;
  }

  /**
   * Get productoNombre
   * @return productoNombre
  */
  
  @Schema(name = "productoNombre", required = false)
  public String getProductoNombre() {
    return productoNombre;
  }

  public void setProductoNombre(String productoNombre) {
    this.productoNombre = productoNombre;
  }

  public ItemPedido cantidad(Integer cantidad) {
    this.cantidad = cantidad;
    return this;
  }

  /**
   * Get cantidad
   * minimum: 1
   * @return cantidad
  */
  @Min(1) 
  @Schema(name = "cantidad", required = false)
  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public ItemPedido precioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
    return this;
  }

  /**
   * Get precioUnitario
   * @return precioUnitario
  */
  
  @Schema(name = "precioUnitario", required = false)
  public Double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public ItemPedido subtotal(Double subtotal) {
    this.subtotal = subtotal;
    return this;
  }

  /**
   * Get subtotal
   * @return subtotal
  */
  
  @Schema(name = "subtotal", required = false)
  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemPedido itemPedido = (ItemPedido) o;
    return Objects.equals(this.id, itemPedido.id) &&
        Objects.equals(this.productoNombre, itemPedido.productoNombre) &&
        Objects.equals(this.cantidad, itemPedido.cantidad) &&
        Objects.equals(this.precioUnitario, itemPedido.precioUnitario) &&
        Objects.equals(this.subtotal, itemPedido.subtotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, productoNombre, cantidad, precioUnitario, subtotal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemPedido {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    productoNombre: ").append(toIndentedString(productoNombre)).append("\n");
    sb.append("    cantidad: ").append(toIndentedString(cantidad)).append("\n");
    sb.append("    precioUnitario: ").append(toIndentedString(precioUnitario)).append("\n");
    sb.append("    subtotal: ").append(toIndentedString(subtotal)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

