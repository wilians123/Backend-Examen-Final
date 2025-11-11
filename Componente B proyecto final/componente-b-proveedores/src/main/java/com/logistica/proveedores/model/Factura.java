package com.logistica.proveedores.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.logistica.proveedores.model.ItemFactura;
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
 * Factura
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:56.817647-06:00[America/Guatemala]")
public class Factura implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("proveedorId")
  private Long proveedorId;

  @JsonProperty("proveedorRazonSocial")
  private String proveedorRazonSocial;

  @JsonProperty("numeroFactura")
  private String numeroFactura;

  @JsonProperty("codigoFactura")
  private String codigoFactura;

  @JsonProperty("fechaEmision")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaEmision;

  @JsonProperty("fechaVencimiento")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaVencimiento;

  @JsonProperty("fechaPago")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDate fechaPago;

  /**
   * Gets or Sets estado
   */
  public enum EstadoEnum {
    EMITIDA("EMITIDA"),
    
    PAGADA("PAGADA"),
    
    VENCIDA("VENCIDA"),
    
    CANCELADA("CANCELADA");

    private String value;

    EstadoEnum(String value) {
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
    public static EstadoEnum fromValue(String value) {
      for (EstadoEnum b : EstadoEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("estado")
  private EstadoEnum estado;

  @JsonProperty("items")
  @Valid
  private List<ItemFactura> items = null;

  @JsonProperty("subtotal")
  private Double subtotal;

  @JsonProperty("impuestos")
  private Double impuestos;

  @JsonProperty("descuentos")
  private Double descuentos;

  @JsonProperty("total")
  private Double total;

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

  @JsonProperty("referenciaPago")
  private String referenciaPago;

  @JsonProperty("observaciones")
  private String observaciones;

  public Factura id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", example = "1", required = true)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Factura proveedorId(Long proveedorId) {
    this.proveedorId = proveedorId;
    return this;
  }

  /**
   * ID del proveedor que emitió la factura
   * @return proveedorId
  */
  @NotNull 
  @Schema(name = "proveedorId", example = "1", description = "ID del proveedor que emitió la factura", required = true)
  public Long getProveedorId() {
    return proveedorId;
  }

  public void setProveedorId(Long proveedorId) {
    this.proveedorId = proveedorId;
  }

  public Factura proveedorRazonSocial(String proveedorRazonSocial) {
    this.proveedorRazonSocial = proveedorRazonSocial;
    return this;
  }

  /**
   * Razón social del proveedor (para consultas)
   * @return proveedorRazonSocial
  */
  
  @Schema(name = "proveedorRazonSocial", example = "Distribuidora XYZ S.A.", description = "Razón social del proveedor (para consultas)", required = false)
  public String getProveedorRazonSocial() {
    return proveedorRazonSocial;
  }

  public void setProveedorRazonSocial(String proveedorRazonSocial) {
    this.proveedorRazonSocial = proveedorRazonSocial;
  }

  public Factura numeroFactura(String numeroFactura) {
    this.numeroFactura = numeroFactura;
    return this;
  }

  /**
   * Número único de la factura
   * @return numeroFactura
  */
  @NotNull @Size(max = 50) 
  @Schema(name = "numeroFactura", example = "FAC-2024-0001", description = "Número único de la factura", required = true)
  public String getNumeroFactura() {
    return numeroFactura;
  }

  public void setNumeroFactura(String numeroFactura) {
    this.numeroFactura = numeroFactura;
  }

  public Factura codigoFactura(String codigoFactura) {
    this.codigoFactura = codigoFactura;
    return this;
  }

  /**
   * Código único generado por componente C
   * @return codigoFactura
  */
  
  @Schema(name = "codigoFactura", example = "FACT-20241104-001", description = "Código único generado por componente C", required = false)
  public String getCodigoFactura() {
    return codigoFactura;
  }

  public void setCodigoFactura(String codigoFactura) {
    this.codigoFactura = codigoFactura;
  }

  public Factura fechaEmision(LocalDate fechaEmision) {
    this.fechaEmision = fechaEmision;
    return this;
  }

  /**
   * Fecha de emisión de la factura
   * @return fechaEmision
  */
  @NotNull @Valid 
  @Schema(name = "fechaEmision", example = "Sun Nov 03 18:00:00 CST 2024", description = "Fecha de emisión de la factura", required = true)
  public LocalDate getFechaEmision() {
    return fechaEmision;
  }

  public void setFechaEmision(LocalDate fechaEmision) {
    this.fechaEmision = fechaEmision;
  }

  public Factura fechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
    return this;
  }

  /**
   * Fecha de vencimiento para pago
   * @return fechaVencimiento
  */
  @Valid 
  @Schema(name = "fechaVencimiento", example = "Tue Dec 03 18:00:00 CST 2024", description = "Fecha de vencimiento para pago", required = false)
  public LocalDate getFechaVencimiento() {
    return fechaVencimiento;
  }

  public void setFechaVencimiento(LocalDate fechaVencimiento) {
    this.fechaVencimiento = fechaVencimiento;
  }

  public Factura fechaPago(LocalDate fechaPago) {
    this.fechaPago = fechaPago;
    return this;
  }

  /**
   * Fecha en que se realizó el pago (si aplica)
   * @return fechaPago
  */
  @Valid 
  @Schema(name = "fechaPago", example = "Sat Nov 09 18:00:00 CST 2024", description = "Fecha en que se realizó el pago (si aplica)", required = false)
  public LocalDate getFechaPago() {
    return fechaPago;
  }

  public void setFechaPago(LocalDate fechaPago) {
    this.fechaPago = fechaPago;
  }

  public Factura estado(EstadoEnum estado) {
    this.estado = estado;
    return this;
  }

  /**
   * Get estado
   * @return estado
  */
  @NotNull 
  @Schema(name = "estado", example = "EMITIDA", required = true)
  public EstadoEnum getEstado() {
    return estado;
  }

  public void setEstado(EstadoEnum estado) {
    this.estado = estado;
  }

  public Factura items(List<ItemFactura> items) {
    this.items = items;
    return this;
  }

  public Factura addItemsItem(ItemFactura itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @Valid 
  @Schema(name = "items", required = false)
  public List<ItemFactura> getItems() {
    return items;
  }

  public void setItems(List<ItemFactura> items) {
    this.items = items;
  }

  public Factura subtotal(Double subtotal) {
    this.subtotal = subtotal;
    return this;
  }

  /**
   * Subtotal sin impuestos
   * @return subtotal
  */
  
  @Schema(name = "subtotal", example = "2000.0", description = "Subtotal sin impuestos", required = false)
  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public Factura impuestos(Double impuestos) {
    this.impuestos = impuestos;
    return this;
  }

  /**
   * Total de impuestos
   * @return impuestos
  */
  
  @Schema(name = "impuestos", example = "240.0", description = "Total de impuestos", required = false)
  public Double getImpuestos() {
    return impuestos;
  }

  public void setImpuestos(Double impuestos) {
    this.impuestos = impuestos;
  }

  public Factura descuentos(Double descuentos) {
    this.descuentos = descuentos;
    return this;
  }

  /**
   * Total de descuentos aplicados
   * @return descuentos
  */
  
  @Schema(name = "descuentos", example = "100.0", description = "Total de descuentos aplicados", required = false)
  public Double getDescuentos() {
    return descuentos;
  }

  public void setDescuentos(Double descuentos) {
    this.descuentos = descuentos;
  }

  public Factura total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Total a pagar
   * @return total
  */
  
  @Schema(name = "total", example = "2140.0", description = "Total a pagar", required = false)
  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Factura metodoPago(MetodoPagoEnum metodoPago) {
    this.metodoPago = metodoPago;
    return this;
  }

  /**
   * Get metodoPago
   * @return metodoPago
  */
  
  @Schema(name = "metodoPago", example = "TRANSFERENCIA", required = false)
  public MetodoPagoEnum getMetodoPago() {
    return metodoPago;
  }

  public void setMetodoPago(MetodoPagoEnum metodoPago) {
    this.metodoPago = metodoPago;
  }

  public Factura referenciaPago(String referenciaPago) {
    this.referenciaPago = referenciaPago;
    return this;
  }

  /**
   * Referencia del pago realizado
   * @return referenciaPago
  */
  @Size(max = 100) 
  @Schema(name = "referenciaPago", description = "Referencia del pago realizado", required = false)
  public String getReferenciaPago() {
    return referenciaPago;
  }

  public void setReferenciaPago(String referenciaPago) {
    this.referenciaPago = referenciaPago;
  }

  public Factura observaciones(String observaciones) {
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
    Factura factura = (Factura) o;
    return Objects.equals(this.id, factura.id) &&
        Objects.equals(this.proveedorId, factura.proveedorId) &&
        Objects.equals(this.proveedorRazonSocial, factura.proveedorRazonSocial) &&
        Objects.equals(this.numeroFactura, factura.numeroFactura) &&
        Objects.equals(this.codigoFactura, factura.codigoFactura) &&
        Objects.equals(this.fechaEmision, factura.fechaEmision) &&
        Objects.equals(this.fechaVencimiento, factura.fechaVencimiento) &&
        Objects.equals(this.fechaPago, factura.fechaPago) &&
        Objects.equals(this.estado, factura.estado) &&
        Objects.equals(this.items, factura.items) &&
        Objects.equals(this.subtotal, factura.subtotal) &&
        Objects.equals(this.impuestos, factura.impuestos) &&
        Objects.equals(this.descuentos, factura.descuentos) &&
        Objects.equals(this.total, factura.total) &&
        Objects.equals(this.metodoPago, factura.metodoPago) &&
        Objects.equals(this.referenciaPago, factura.referenciaPago) &&
        Objects.equals(this.observaciones, factura.observaciones);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, proveedorId, proveedorRazonSocial, numeroFactura, codigoFactura, fechaEmision, fechaVencimiento, fechaPago, estado, items, subtotal, impuestos, descuentos, total, metodoPago, referenciaPago, observaciones);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Factura {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    proveedorId: ").append(toIndentedString(proveedorId)).append("\n");
    sb.append("    proveedorRazonSocial: ").append(toIndentedString(proveedorRazonSocial)).append("\n");
    sb.append("    numeroFactura: ").append(toIndentedString(numeroFactura)).append("\n");
    sb.append("    codigoFactura: ").append(toIndentedString(codigoFactura)).append("\n");
    sb.append("    fechaEmision: ").append(toIndentedString(fechaEmision)).append("\n");
    sb.append("    fechaVencimiento: ").append(toIndentedString(fechaVencimiento)).append("\n");
    sb.append("    fechaPago: ").append(toIndentedString(fechaPago)).append("\n");
    sb.append("    estado: ").append(toIndentedString(estado)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    subtotal: ").append(toIndentedString(subtotal)).append("\n");
    sb.append("    impuestos: ").append(toIndentedString(impuestos)).append("\n");
    sb.append("    descuentos: ").append(toIndentedString(descuentos)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    metodoPago: ").append(toIndentedString(metodoPago)).append("\n");
    sb.append("    referenciaPago: ").append(toIndentedString(referenciaPago)).append("\n");
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

