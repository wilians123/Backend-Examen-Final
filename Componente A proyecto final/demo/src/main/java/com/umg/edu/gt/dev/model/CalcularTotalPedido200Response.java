package com.umg.edu.gt.dev.model;

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
 * CalcularTotalPedido200Response
 */

@JsonTypeName("calcularTotalPedido_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T01:40:33.368685200-06:00[America/Guatemala]")
public class CalcularTotalPedido200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("pedidoId")
  private Long pedidoId;

  @JsonProperty("subtotal")
  private Double subtotal;

  @JsonProperty("impuestos")
  private Double impuestos;

  @JsonProperty("total")
  private Double total;

  public CalcularTotalPedido200Response pedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
    return this;
  }

  /**
   * Get pedidoId
   * @return pedidoId
  */
  
  @Schema(name = "pedidoId", required = false)
  public Long getPedidoId() {
    return pedidoId;
  }

  public void setPedidoId(Long pedidoId) {
    this.pedidoId = pedidoId;
  }

  public CalcularTotalPedido200Response subtotal(Double subtotal) {
    this.subtotal = subtotal;
    return this;
  }

  /**
   * Get subtotal
   * @return subtotal
  */
  
  @Schema(name = "subtotal", example = "1000.0", required = false)
  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public CalcularTotalPedido200Response impuestos(Double impuestos) {
    this.impuestos = impuestos;
    return this;
  }

  /**
   * Get impuestos
   * @return impuestos
  */
  
  @Schema(name = "impuestos", example = "120.0", required = false)
  public Double getImpuestos() {
    return impuestos;
  }

  public void setImpuestos(Double impuestos) {
    this.impuestos = impuestos;
  }

  public CalcularTotalPedido200Response total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  
  @Schema(name = "total", example = "1120.0", required = false)
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
    CalcularTotalPedido200Response calcularTotalPedido200Response = (CalcularTotalPedido200Response) o;
    return Objects.equals(this.pedidoId, calcularTotalPedido200Response.pedidoId) &&
        Objects.equals(this.subtotal, calcularTotalPedido200Response.subtotal) &&
        Objects.equals(this.impuestos, calcularTotalPedido200Response.impuestos) &&
        Objects.equals(this.total, calcularTotalPedido200Response.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pedidoId, subtotal, impuestos, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CalcularTotalPedido200Response {\n");
    sb.append("    pedidoId: ").append(toIndentedString(pedidoId)).append("\n");
    sb.append("    subtotal: ").append(toIndentedString(subtotal)).append("\n");
    sb.append("    impuestos: ").append(toIndentedString(impuestos)).append("\n");
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

