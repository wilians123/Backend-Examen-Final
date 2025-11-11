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
 * ItemFacturaInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-10T14:44:36.607023700-06:00[America/Guatemala]")
public class ItemFacturaInput implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("descripcion")
  private String descripcion;

  @JsonProperty("cantidad")
  private Integer cantidad;

  @JsonProperty("precioUnitario")
  private Double precioUnitario;

  public ItemFacturaInput descripcion(String descripcion) {
    this.descripcion = descripcion;
    return this;
  }

  /**
   * Get descripcion
   * @return descripcion
  */
  @NotNull @Size(min = 1, max = 200) 
  @Schema(name = "descripcion", example = "Monitor LED 24 pulgadas", required = true)
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public ItemFacturaInput cantidad(Integer cantidad) {
    this.cantidad = cantidad;
    return this;
  }

  /**
   * Get cantidad
   * minimum: 1
   * @return cantidad
  */
  @NotNull @Min(1) 
  @Schema(name = "cantidad", example = "5", required = true)
  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public ItemFacturaInput precioUnitario(Double precioUnitario) {
    this.precioUnitario = precioUnitario;
    return this;
  }

  /**
   * Get precioUnitario
   * minimum: 0.01
   * @return precioUnitario
  */
  @NotNull @DecimalMin("0.01") 
  @Schema(name = "precioUnitario", example = "400.0", required = true)
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
    ItemFacturaInput itemFacturaInput = (ItemFacturaInput) o;
    return Objects.equals(this.descripcion, itemFacturaInput.descripcion) &&
        Objects.equals(this.cantidad, itemFacturaInput.cantidad) &&
        Objects.equals(this.precioUnitario, itemFacturaInput.precioUnitario);
  }

  @Override
  public int hashCode() {
    return Objects.hash(descripcion, cantidad, precioUnitario);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemFacturaInput {\n");
    sb.append("    descripcion: ").append(toIndentedString(descripcion)).append("\n");
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

