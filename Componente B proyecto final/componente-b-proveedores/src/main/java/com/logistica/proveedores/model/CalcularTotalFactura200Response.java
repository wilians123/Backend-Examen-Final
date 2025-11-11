package com.logistica.proveedores.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * CalcularTotalFactura200Response
 */

@JsonTypeName("calcularTotalFactura_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:56.817647-06:00[America/Guatemala]")
public class CalcularTotalFactura200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("facturaId")
  private Long facturaId;

  @JsonProperty("subtotal")
  private Double subtotal;

  @JsonProperty("impuestos")
  private Double impuestos;

  @JsonProperty("descuentos")
  private Double descuentos;

  @JsonProperty("total")
  private Double total;

  public CalcularTotalFactura200Response facturaId(Long facturaId) {
    this.facturaId = facturaId;
    return this;
  }

  /**
   * Get facturaId
   * @return facturaId
  */
  
  @Schema(name = "facturaId", required = false)
  public Long getFacturaId() {
    return facturaId;
  }

  public void setFacturaId(Long facturaId) {
    this.facturaId = facturaId;
  }

  public CalcularTotalFactura200Response subtotal(Double subtotal) {
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

  public CalcularTotalFactura200Response impuestos(Double impuestos) {
    this.impuestos = impuestos;
    return this;
  }

  /**
   * Get impuestos
   * @return impuestos
  */
  
  @Schema(name = "impuestos", example = "240.0", required = false)
  public Double getImpuestos() {
    return impuestos;
  }

  public void setImpuestos(Double impuestos) {
    this.impuestos = impuestos;
  }

  public CalcularTotalFactura200Response descuentos(Double descuentos) {
    this.descuentos = descuentos;
    return this;
  }

  /**
   * Get descuentos
   * @return descuentos
  */
  
  @Schema(name = "descuentos", example = "100.0", required = false)
  public Double getDescuentos() {
    return descuentos;
  }

  public void setDescuentos(Double descuentos) {
    this.descuentos = descuentos;
  }

  public CalcularTotalFactura200Response total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  
  @Schema(name = "total", example = "2140.0", required = false)
  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CalcularTotalFactura200Response calcularTotalFactura200Response = (CalcularTotalFactura200Response) o;
    return Objects.equals(this.facturaId, calcularTotalFactura200Response.facturaId) &&
        Objects.equals(this.subtotal, calcularTotalFactura200Response.subtotal) &&
        Objects.equals(this.impuestos, calcularTotalFactura200Response.impuestos) &&
        Objects.equals(this.descuentos, calcularTotalFactura200Response.descuentos) &&
        Objects.equals(this.total, calcularTotalFactura200Response.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(facturaId, subtotal, impuestos, descuentos, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalcularTotalFactura200Response {\n");
    sb.append("    facturaId: ").append(toIndentedString(facturaId)).append("\n");
    sb.append("    subtotal: ").append(toIndentedString(subtotal)).append("\n");
    sb.append("    impuestos: ").append(toIndentedString(impuestos)).append("\n");
    sb.append("    descuentos: ").append(toIndentedString(descuentos)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

