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
 * ClienteInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T19:12:21.095389900-06:00[America/Guatemala]")
public class ClienteInput implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("nombre")
  private String nombre;

  @JsonProperty("email")
  private String email;

  @JsonProperty("telefono")
  private String telefono;

  @JsonProperty("direccion")
  private String direccion;

  public ClienteInput nombre(String nombre) {
    this.nombre = nombre;
    return this;
  }

  /**
   * Get nombre
   * @return nombre
  */
  @NotNull @Size(min = 3, max = 100) 
  @Schema(name = "nombre", required = true)
  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public ClienteInput email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull @Email
  @Schema(name = "email", required = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ClienteInput telefono(String telefono) {
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

  public ClienteInput direccion(String direccion) {
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ClienteInput clienteInput = (ClienteInput) o;
    return Objects.equals(this.nombre, clienteInput.nombre) &&
        Objects.equals(this.email, clienteInput.email) &&
        Objects.equals(this.telefono, clienteInput.telefono) &&
        Objects.equals(this.direccion, clienteInput.direccion);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nombre, email, telefono, direccion);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ClienteInput {\n");
    sb.append("    nombre: ").append(toIndentedString(nombre)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
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

