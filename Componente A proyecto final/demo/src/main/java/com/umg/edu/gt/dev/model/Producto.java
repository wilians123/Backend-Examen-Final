package com.umg.edu.gt.dev.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.math.BigDecimal;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Producto
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T01:31:00.554755100-06:00[America/Guatemala]")
public class Producto implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("precio")
  private BigDecimal precio;

  public Producto nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  */
  
  @Schema(name = "nombre", required = false)
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Producto precio(BigDecimal precio) {
    this.precio = precio;
    return this;
  }

  /**
   * Get precio
   * @return precio
  */
  @Valid 
  @Schema(name = "precio", required = false)
  public BigDecimal getPrecio() {
    return precio;
  }

  public void setPrecio(BigDecimal precio) {
    this.precio = precio;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Producto producto = (Producto) o;
    return Objects.equals(this.nombre, producto.nombre) &&
        Objects.equals(this.precio, producto.precio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, precio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Producto {\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    precio: ").append(toIndentedString(precio)).append("\n");
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

