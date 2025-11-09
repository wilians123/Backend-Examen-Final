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
 * Cliente
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T23:39:27.831609400-06:00[America/Guatemala]")
public class Cliente implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("email")
  private String email;

  @JsonProperty("telefono")
  private String telefono;

  @JsonProperty("direccion")
  private String direccion;

  @JsonProperty("codigoUnico")
  private String codigoUnico;

  public Cliente id(Long id) {
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

  public Cliente nombre(String nombre) {
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

  public Cliente email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  
  @Schema(name = "email", required = false)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Cliente telefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  /**
   * Get telefono
   * @return telefono
  */
  
  @Schema(name = "telefono", required = false)
  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Cliente direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * Get direccion
   * @return direccion
  */
  
  @Schema(name = "direccion", required = false)
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Cliente codigoUnico(String codigoUnico) {
    this.codigoUnico = codigoUnico;
    return this;
  }

  /**
   * Get codigoUnico
   * @return codigoUnico
  */
  
  @Schema(name = "codigoUnico", required = false)
  public String getCodigoUnico() {
    return codigoUnico;
  }

  public void setCodigoUnico(String codigoUnico) {
    this.codigoUnico = codigoUnico;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cliente cliente = (Cliente) o;
    return Objects.equals(this.id, cliente.id) &&
        Objects.equals(this.nombre, cliente.nombre) &&
        Objects.equals(this.email, cliente.email) &&
        Objects.equals(this.telefono, cliente.telefono) &&
        Objects.equals(this.direccion, cliente.direccion) &&
        Objects.equals(this.codigoUnico, cliente.codigoUnico);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, nombre, email, telefono, direccion, codigoUnico);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Cliente {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    codigoUnico: ").append(toIndentedString(codigoUnico)).append("\n");
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

