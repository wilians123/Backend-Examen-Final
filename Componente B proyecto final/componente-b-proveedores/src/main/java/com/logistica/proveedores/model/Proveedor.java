package com.logistica.proveedores.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Proveedor
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:56.817647-06:00[America/Guatemala]")
public class Proveedor implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

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

  @JsonProperty("codigoUnico")
  private String codigoUnico;

  @JsonProperty("categoriaProductos")
  private String categoriaProductos;

  @JsonProperty("fechaRegistro")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime fechaRegistro;

  @JsonProperty("activo")
  private Boolean activo = true;

  public Proveedor id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Identificador único del proveedor
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "1", description = "Identificador único del proveedor", required = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Proveedor razonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
    return this;
  }

  /**
   * Razón social o nombre de la empresa
   * @return razonSocial
  */
  @NotNull @Size(min = 3, max = 200) 
  @Schema(name = "razonSocial", example = "Distribuidora XYZ S.A.", description = "Razón social o nombre de la empresa", required = true)
  public String getRazonSocial() {
    return razonSocial;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public Proveedor rfc(String rfc) {
    this.rfc = rfc;
    return this;
  }

  /**
   * Registro Federal de Contribuyentes o NIT
   * @return rfc
  */
  @NotNull @Pattern(regexp = "^[A-Z0-9]+$") @Size(min = 10, max = 20) 
  @Schema(name = "rfc", example = "ABC123456DEF", description = "Registro Federal de Contribuyentes o NIT", required = true)
  public String getRfc() {
    return rfc;
  }

  public void setRfc(String rfc) {
    this.rfc = rfc;
  }

  public Proveedor email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Correo electrónico del proveedor
   * @return email
  */
  @NotNull @Email
  @Schema(name = "email", example = "contacto@distribuidoraxyz.com", description = "Correo electrónico del proveedor", required = true)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Proveedor telefono(String telefono) {
    this.telefono = telefono;
    return this;
  }

  /**
   * Número de teléfono
   * @return telefono
  */
  
  @Schema(name = "telefono", example = "+502 2345-6789", description = "Número de teléfono", required = false)
  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public Proveedor direccion(String direccion) {
    this.direccion = direccion;
    return this;
  }

  /**
   * Dirección física del proveedor
   * @return direccion
  */
  @Size(max = 255) 
  @Schema(name = "direccion", description = "Dirección física del proveedor", required = false)
  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Proveedor contactoNombre(String contactoNombre) {
    this.contactoNombre = contactoNombre;
    return this;
  }

  /**
   * Nombre del contacto principal
   * @return contactoNombre
  */
  @Size(max = 100) 
  @Schema(name = "contactoNombre", example = "María García", description = "Nombre del contacto principal", required = false)
  public String getContactoNombre() {
    return contactoNombre;
  }

  public void setContactoNombre(String contactoNombre) {
    this.contactoNombre = contactoNombre;
  }

  public Proveedor codigoUnico(String codigoUnico) {
    this.codigoUnico = codigoUnico;
    return this;
  }

  /**
   * Código único generado por el componente C
   * @return codigoUnico
  */
  
  @Schema(name = "codigoUnico", example = "PROV-20241104-001", description = "Código único generado por el componente C", required = false)
  public String getCodigoUnico() {
    return codigoUnico;
  }

  public void setCodigoUnico(String codigoUnico) {
    this.codigoUnico = codigoUnico;
  }

  public Proveedor categoriaProductos(String categoriaProductos) {
    this.categoriaProductos = categoriaProductos;
    return this;
  }

  /**
   * Categoría principal de productos que suministra
   * @return categoriaProductos
  */
  @Size(max = 100) 
  @Schema(name = "categoriaProductos", example = "Electrónicos", description = "Categoría principal de productos que suministra", required = false)
  public String getCategoriaProductos() {
    return categoriaProductos;
  }

  public void setCategoriaProductos(String categoriaProductos) {
    this.categoriaProductos = categoriaProductos;
  }

  public Proveedor fechaRegistro(OffsetDateTime fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
    return this;
  }

  /**
   * Fecha y hora de registro
   * @return fechaRegistro
  */
  @Valid 
  @Schema(name = "fechaRegistro", example = "2024-11-04T10:30Z", description = "Fecha y hora de registro", required = false)
  public OffsetDateTime getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(OffsetDateTime fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public Proveedor activo(Boolean activo) {
    this.activo = activo;
    return this;
  }

  /**
   * Indica si el proveedor está activo
   * @return activo
  */
  
  @Schema(name = "activo", description = "Indica si el proveedor está activo", required = false)
  public Boolean getActivo() {
    return activo;
  }

  public void setActivo(Boolean activo) {
    this.activo = activo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Proveedor proveedor = (Proveedor) o;
    return Objects.equals(this.id, proveedor.id) &&
        Objects.equals(this.razonSocial, proveedor.razonSocial) &&
        Objects.equals(this.rfc, proveedor.rfc) &&
        Objects.equals(this.email, proveedor.email) &&
        Objects.equals(this.telefono, proveedor.telefono) &&
        Objects.equals(this.direccion, proveedor.direccion) &&
        Objects.equals(this.contactoNombre, proveedor.contactoNombre) &&
        Objects.equals(this.codigoUnico, proveedor.codigoUnico) &&
        Objects.equals(this.categoriaProductos, proveedor.categoriaProductos) &&
        Objects.equals(this.fechaRegistro, proveedor.fechaRegistro) &&
        Objects.equals(this.activo, proveedor.activo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, razonSocial, rfc, email, telefono, direccion, contactoNombre, codigoUnico, categoriaProductos, fechaRegistro, activo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Proveedor {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    razonSocial: ").append(toIndentedString(razonSocial)).append("\n");
    sb.append("    rfc: ").append(toIndentedString(rfc)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    telefono: ").append(toIndentedString(telefono)).append("\n");
    sb.append("    direccion: ").append(toIndentedString(direccion)).append("\n");
    sb.append("    contactoNombre: ").append(toIndentedString(contactoNombre)).append("\n");
    sb.append("    codigoUnico: ").append(toIndentedString(codigoUnico)).append("\n");
    sb.append("    categoriaProductos: ").append(toIndentedString(categoriaProductos)).append("\n");
    sb.append("    fechaRegistro: ").append(toIndentedString(fechaRegistro)).append("\n");
    sb.append("    activo: ").append(toIndentedString(activo)).append("\n");
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

