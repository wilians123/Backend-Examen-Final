package com.logistica.proveedores.model;

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
 * ItemFactura
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-10T14:44:36.607023700-06:00[America/Guatemala]")
public class ItemFactura implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("cantidad")
  private Integer cantidad;

  @JsonProperty("precioUnitario")
  private Double precioUnitario;

  @JsonProperty("subtotal")
  private Double subtotal;

  public ItemFactura id(Long id) {
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

  public ItemFactura descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Descripción del producto o servicio
   * @return descripcion
  */
  
  @Schema(name = "descripcion", example = "Monitor LED 24 pulgadas", description = "Descripción del producto o servicio", required = false)
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public ItemFactura cantidad(Integer cantidad) {
    this.cantidad = cantidad;
    return this;
  }

  /**
   * Get cantidad
   * minimum: 1
   * @return cantidad
  */
  @Min(1) 
  @Schema(name = "cantidad", example = "5", required = false)
  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public ItemFactura precioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
    return this;
  }

  /**
   * Get precioUnitario
   * @return precioUnitario
  */
  
  @Schema(name = "precioUnitario", example = "400.0", required = false)
  public Double getPrecioUnitario() {
    return precioUnitario;
  }

  public void setPrecioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
  }

  public ItemFactura subtotal(Double subtotal) {
    this.subtotal = subtotal;
    return this;
  }

  /**
   * Get subtotal
   * @return subtotal
  */
  
  @Schema(name = "subtotal", example = "2000.0", required = false)
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
    ItemFactura itemFactura = (ItemFactura) o;
    return Objects.equals(this.id, itemFactura.id) &&
        Objects.equals(this.descripcion, itemFactura.descripcion) &&
        Objects.equals(this.cantidad, itemFactura.cantidad) &&
        Objects.equals(this.precioUnitario, itemFactura.precioUnitario) &&
        Objects.equals(this.subtotal, itemFactura.subtotal);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, descripcion, cantidad, precioUnitario, subtotal);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemFactura {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
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

