package com.umg.edu.gt.dev.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.umg.edu.gt.dev.model.ItemPedido;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.io.Serializable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Pedido
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:53.306505300-06:00[America/Guatemala]")
public class Pedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("clienteId")
  private Long clienteId;

  @JsonProperty("codigoPedido")
  private String codigoPedido;

  @JsonProperty("items")
  @Valid
  private List<ItemPedido> items = null;

  @JsonProperty("subtotal")
  private Double subtotal;

  @JsonProperty("impuestos")
  private Double impuestos;

  @JsonProperty("total")
  private Double total;

  public Pedido id(Long id) {
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

  public Pedido clienteId(Long clienteId) {
    this.clienteId = clienteId;
    return this;
  }

  /**
   * Get clienteId
   * @return clienteId
  */
  
  @Schema(name = "clienteId", required = false)
  public Long getClienteId() {
    return clienteId;
  }

  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
  }

  public Pedido codigoPedido(String codigoPedido) {
    this.codigoPedido = codigoPedido;
    return this;
  }

  /**
   * Get codigoPedido
   * @return codigoPedido
  */
  
  @Schema(name = "codigoPedido", required = false)
  public String getCodigoPedido() {
    return codigoPedido;
  }

  public void setCodigoPedido(String codigoPedido) {
    this.codigoPedido = codigoPedido;
  }

  public Pedido items(List<ItemPedido> items) {
    this.items = items;
    return this;
  }

  public Pedido addItemsItem(ItemPedido itemsItem) {
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
  public List<ItemPedido> getItems() {
    return items;
  }

  public void setItems(List<ItemPedido> items) {
    this.items = items;
  }

  public Pedido subtotal(Double subtotal) {
    this.subtotal = subtotal;
    return this;
  }

  /**
   * Get subtotal
   * @return subtotal
  */
  
  @Schema(name = "subtotal", required = false)
  public Double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(Double subtotal) {
    this.subtotal = subtotal;
  }

  public Pedido impuestos(Double impuestos) {
    this.impuestos = impuestos;
    return this;
  }

  /**
   * Get impuestos
   * @return impuestos
  */
  
  @Schema(name = "impuestos", required = false)
  public Double getImpuestos() {
    return impuestos;
  }

  public void setImpuestos(Double impuestos) {
    this.impuestos = impuestos;
  }

  public Pedido total(Double total) {
    this.total = total;
    return this;
  }

  /**
   * Get total
   * @return total
  */
  
  @Schema(name = "total", required = false)
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
    Pedido pedido = (Pedido) o;
    return Objects.equals(this.id, pedido.id) &&
        Objects.equals(this.clienteId, pedido.clienteId) &&
        Objects.equals(this.codigoPedido, pedido.codigoPedido) &&
        Objects.equals(this.items, pedido.items) &&
        Objects.equals(this.subtotal, pedido.subtotal) &&
        Objects.equals(this.impuestos, pedido.impuestos) &&
        Objects.equals(this.total, pedido.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, clienteId, codigoPedido, items, subtotal, impuestos, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Pedido {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    clienteId: ").append(toIndentedString(clienteId)).append("\n");
    sb.append("    codigoPedido: ").append(toIndentedString(codigoPedido)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

