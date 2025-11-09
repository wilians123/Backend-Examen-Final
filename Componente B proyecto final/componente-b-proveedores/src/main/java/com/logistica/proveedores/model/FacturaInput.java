package com.logistica.proveedores.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.logistica.proveedores.model.ItemFacturaInput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
 * FacturaInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-09T15:19:51.115170100-06:00[America/Guatemala]")
public class FacturaInput implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("proveedorId")
  private Long proveedorId;

  @JsonProperty("numeroFactura")
  private String numeroFactura;

  @JsonProperty("fechaEmision")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaEmision;

  @JsonProperty("fechaVencimiento")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaVencimiento;

  @JsonProperty("items")
  @Valid
  private List<ItemFacturaInput> items = new ArrayList<>();

  @JsonProperty("descuentos")
  private Double descuentos;

  @JsonProperty("observaciones")
  private String observaciones;

  public FacturaInput proveedorId(Long proveedorId) {
    this.proveedorId = proveedorId;
    return this;
  }

  /**
   * Get proveedorId
   * @return proveedorId
  */
  @NotNull 
  @Schema(name = "proveedorId", example = "1", required = true)
  public Long getProveedorId() {
    return proveedorId;
  }

  public void setProveedorId(Long proveedorId) {
    this.proveedorId = proveedorId;
  }

  public FacturaInput numeroFactura(String numeroFactura) {
    this.numeroFactura = numeroFactura;
    return this;
  }

  /**
   * Get numeroFactura
   * @return numeroFactura
  */
  @NotNull @Size(max = 50) 
  @Schema(name = "numeroFactura", example = "FAC-2024-0001", required = true)
  public String getNumeroFactura() {
    return numeroFactura;
  }

  public void setNumeroFactura(String numeroFactura) {
    this.numeroFactura = numeroFactura;
  }

  public FacturaInput fechaEmision(LocalDate fechaEmision) {
    this.fechaEmision = fechaEmision;
    return this;
  }

  /**
   * Get fechaEmision
   * @return fechaEmision
  */
  @NotNull @Valid 
  @Schema(name = "fechaEmision", example = "Sun Nov 03 18:00:00 CST 2024", required = true)
  public LocalDate getFechaEmision() {
    return fechaEmision;
  }

  public void setFechaEmision(LocalDate fechaEmision) {
    this.fechaEmision = fechaEmision;
  }

  public FacturaInput fechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
    return this;
  }

  /**
   * Get fechaVencimiento
   * @return fechaVencimiento
  */
  @NotNull @Valid 
  @Schema(name = "fechaVencimiento", example = "Tue Dec 03 18:00:00 CST 2024", required = true)
  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public FacturaInput items(List<ItemFacturaInput> items) {
    this.items = items;
    return this;
  }

  public FacturaInput addItemsItem(ItemFacturaInput itemsItem) {
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @NotNull @Valid @Size(min = 1) 
  @Schema(name = "items", required = true)
  public List<ItemFacturaInput> getItems() {
    return items;
  }

  public void setItems(List<ItemFacturaInput> items) {
    this.items = items;
  }

  public FacturaInput descuentos(Double descuentos) {
    this.descuentos = descuentos;
    return this;
  }

  /**
   * Get descuentos
   * minimum: 0
   * @return descuentos
  */
  @DecimalMin("0") 
  @Schema(name = "descuentos", example = "100.0", required = false)
  public Double getDescuentos() {
    return descuentos;
  }

  public void setDescuentos(Double descuentos) {
    this.descuentos = descuentos;
  }

  public FacturaInput observaciones(String observaciones) {
    this.observaciones = observaciones;
    return this;
  }

  /**
   * Get observaciones
   * @return observaciones
  */
  @Size(max = 500) 
  @Schema(name = "observaciones", required = false)
  public String getObservaciones() {
    return observaciones;
  }

  public void setObservaciones(String observaciones) {
    this.observaciones = observaciones;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FacturaInput facturaInput = (FacturaInput) o;
    return Objects.equals(this.proveedorId, facturaInput.proveedorId) &&
        Objects.equals(this.numeroFactura, facturaInput.numeroFactura) &&
        Objects.equals(this.fechaEmision, facturaInput.fechaEmision) &&
        Objects.equals(this.fechaVencimiento, facturaInput.fechaVencimiento) &&
        Objects.equals(this.items, facturaInput.items) &&
        Objects.equals(this.descuentos, facturaInput.descuentos) &&
        Objects.equals(this.observaciones, facturaInput.observaciones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(proveedorId, numeroFactura, fechaEmision, fechaVencimiento, items, descuentos, observaciones);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FacturaInput {\n");
    sb.append("    proveedorId: ").append(toIndentedString(proveedorId)).append("\n");
    sb.append("    numeroFactura: ").append(toIndentedString(numeroFactura)).append("\n");
    sb.append("    fechaEmision: ").append(toIndentedString(fechaEmision)).append("\n");
    sb.append("    fechaVencimiento: ").append(toIndentedString(fechaVencimiento)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    descuentos: ").append(toIndentedString(descuentos)).append("\n");
    sb.append("    observaciones: ").append(toIndentedString(observaciones)).append("\n");
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

