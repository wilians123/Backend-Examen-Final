package com.logistica.proveedores.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import java.time.LocalDate;
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
 * PagarFacturaRequest
 */

@JsonTypeName("pagarFactura_request")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-09T15:19:51.115170100-06:00[America/Guatemala]")
public class PagarFacturaRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("fechaPago")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaPago;

  /**
   * Gets or Sets metodoPago
   */
  public enum MetodoPagoEnum {
    EFECTIVO("EFECTIVO"),
    
    TRANSFERENCIA("TRANSFERENCIA"),
    
    CHEQUE("CHEQUE"),
    
    TARJETA("TARJETA");

    private String value;

    MetodoPagoEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MetodoPagoEnum fromValue(String value) {
      for (MetodoPagoEnum b : MetodoPagoEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("metodoPago")
  private MetodoPagoEnum metodoPago;

  @JsonProperty("referencia")
  private String referencia;

  public PagarFacturaRequest fechaPago(LocalDate fechaPago) {
    this.fechaPago = fechaPago;
    return this;
  }

  /**
   * Get fechaPago
   * @return fechaPago
  */
  @NotNull @Valid 
  @Schema(name = "fechaPago", example = "Sun Nov 03 18:00:00 CST 2024", required = true)
  public LocalDate getFechaPago() {
    return fechaPago;
  }

  public void setFechaPago(LocalDate fechaPago) {
    this.fechaPago = fechaPago;
  }

  public PagarFacturaRequest metodoPago(MetodoPagoEnum metodoPago) {
    this.metodoPago = metodoPago;
    return this;
  }

  /**
   * Get metodoPago
   * @return metodoPago
  */
  @NotNull 
  @Schema(name = "metodoPago", example = "TRANSFERENCIA", required = true)
  public MetodoPagoEnum getMetodoPago() {
    return metodoPago;
  }

  public void setMetodoPago(MetodoPagoEnum metodoPago) {
    this.metodoPago = metodoPago;
  }

  public PagarFacturaRequest referencia(String referencia) {
    this.referencia = referencia;
    return this;
  }

  /**
   * Get referencia
   * @return referencia
  */
  @Size(max = 100) 
  @Schema(name = "referencia", example = "REF-20241104-001", required = false)
  public String getReferencia() {
    return referencia;
  }

  public void setReferencia(String referencia) {
    this.referencia = referencia;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PagarFacturaRequest pagarFacturaRequest = (PagarFacturaRequest) o;
    return Objects.equals(this.fechaPago, pagarFacturaRequest.fechaPago) &&
        Objects.equals(this.metodoPago, pagarFacturaRequest.metodoPago) &&
        Objects.equals(this.referencia, pagarFacturaRequest.referencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fechaPago, metodoPago, referencia);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PagarFacturaRequest {\n");
    sb.append("    fechaPago: ").append(toIndentedString(fechaPago)).append("\n");
    sb.append("    metodoPago: ").append(toIndentedString(metodoPago)).append("\n");
    sb.append("    referencia: ").append(toIndentedString(referencia)).append("\n");
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

