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
 * ProveedorInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-09T15:32:00.964758700-06:00[America/Guatemala]")
public class ProveedorInput implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("razonSocial")
  private String razonSocial;

  @JsonProperty("rfc")
  private String rfc;

  @JsonProperty("email")
  private String email;

  @JsonProperty("telefono")
  private String telefono;

  @JsonProperty("direccion")
  private String direccion;

  @JsonProperty("contactoNombre")
  private String contactoNombre;

  @JsonProperty("categoriaProductos")
  private String categoriaProductos;

  public ProveedorInput razonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
    return this;
  }

  /**
   * Get razonSocial
   * @return razonSocial
  */
  @NotNull @Size(min = 3, max = 200) 
  @Schema(name = "razonSocial", example = "Distribuidora XYZ S.A.", required = true)
  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public ProveedorInput rfc(String rfc) {
    this.rfc = rfc;
    return this;
  }

  /**
   * Get rfc
   * @return rfc
  */
  @NotNull @Pattern(regexp = "^[A-Z0-9]+$") @Size(min = 10, max = 20) 
  @Schema(name = "rfc", example = "ABC123456DEF", required = true)
  public String getRfc() {
    return rfc;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }

  public ProveedorInput email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @NotNull @Email
  @Schema(name = "email", example = "contacto@distribuidoraxyz.com", required = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ProveedorInput telefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  /**
   * Get telefono
   * @return telefono
  */
  
  @Schema(name = "telefono", example = "+502 2345-6789", required = false)
  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public ProveedorInput direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * Get direccion
   * @return direccion
  */
  @Size(max = 255) 
  @Schema(name = "direccion", required = false)
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public ProveedorInput contactoNombre(String contactoNombre) {
    this.contactoNombre = contactoNombre;
    return this;
  }

  /**
   * Get contactoNombre
   * @return contactoNombre
  */
  @Size(max = 100) 
  @Schema(name = "contactoNombre", example = "María García", required = false)
  public String getContactoNombre() {
    return contactoNombre;
  }

  public void setContactoNombre(String contactoNombre) {
    this.contactoNombre = contactoNombre;
  }

  public ProveedorInput categoriaProductos(String categoriaProductos) {
    this.categoriaProductos = categoriaProductos;
    return this;
  }

  /**
   * Get categoriaProductos
   * @return categoriaProductos
  */
  @Size(max = 100) 
  @Schema(name = "categoriaProductos", example = "Electrónicos", required = false)
  public String getCategoriaProductos() {
    return categoriaProductos;
  }

  public void setCategoriaProductos(String categoriaProductos) {
    this.categoriaProductos = categoriaProductos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProveedorInput proveedorInput = (ProveedorInput) o;
    return Objects.equals(this.razonSocial, proveedorInput.razonSocial) &&
        Objects.equals(this.rfc, proveedorInput.rfc) &&
        Objects.equals(this.email, proveedorInput.email) &&
        Objects.equals(this.telefono, proveedorInput.telefono) &&
        Objects.equals(this.direccion, proveedorInput.direccion) &&
        Objects.equals(this.contactoNombre, proveedorInput.contactoNombre) &&
        Objects.equals(this.categoriaProductos, proveedorInput.categoriaProductos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(razonSocial, rfc, email, telefono, direccion, contactoNombre, categoriaProductos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProveedorInput {\n");
    sb.append("    razonSocial: ").append(toIndentedString(razonSocial)).append("\n");
    sb.append("    rfc: ").append(toIndentedString(rfc)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    contactoNombre: ").append(toIndentedString(contactoNombre)).append("\n");
    sb.append("    categoriaProductos: ").append(toIndentedString(categoriaProductos)).append("\n");
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

