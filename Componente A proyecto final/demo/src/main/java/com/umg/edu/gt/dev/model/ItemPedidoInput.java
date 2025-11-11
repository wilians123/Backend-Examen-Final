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
 * ItemPedidoInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:53.306505300-06:00[America/Guatemala]")
public class ItemPedidoInput implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("productoNombre")
  private String productoNombre;

  @JsonProperty("cantidad")
  private Integer cantidad;

  @JsonProperty("precioUnitario")
  private Double precioUnitario;

  public ItemPedidoInput productoNombre(String productoNombre) {
    this.productoNombre = productoNombre;
    return this;
  }

  /**
   * Get productoNombre
   * @return productoNombre
  */
  @NotNull 
  @Schema(name = "productoNombre", required = true)
  public String getProductoNombre() {
    return productoNombre;
  }

  public void setProductoNombre(String productoNombre) {
    this.productoNombre = productoNombre;
  }

  public ItemPedidoInput cantidad(Integer cantidad) {
    this.cantidad = cantidad;
    return this;
  }

  /**
   * Get cantidad
   * minimum: 1
   * @return cantidad
  */
  @NotNull @Min(1) 
  @Schema(name = "cantidad", required = true)
  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public ItemPedidoInput precioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
    return this;
  }

  /**
   * Get precioUnitario
   * minimum: 0.01
   * @return precioUnitario
  */
  @NotNull @DecimalMin("0.01") 
  @Schema(name = "precioUnitario", required = true)
  public Double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemPedidoInput itemPedidoInput = (ItemPedidoInput) o;
    return Objects.equals(this.productoNombre, itemPedidoInput.productoNombre) &&
        Objects.equals(this.cantidad, itemPedidoInput.cantidad) &&
        Objects.equals(this.precioUnitario, itemPedidoInput.precioUnitario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productoNombre, cantidad, precioUnitario);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemPedidoInput {\n");
    sb.append("    productoNombre: ").append(toIndentedString(productoNombre)).append("\n");
    sb.append("    cantidad: ").append(toIndentedString(cantidad)).append("\n");
    sb.append("    precioUnitario: ").append(toIndentedString(precioUnitario)).append("\n");
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

