package com.umg.edu.gt.dev.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.umg.edu.gt.dev.model.ItemPedidoInput;
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
 * PedidoInput
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:53.306505300-06:00[America/Guatemala]")
public class PedidoInput implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("clienteId")
  private Long clienteId;

  @JsonProperty("items")
  @Valid
  private List<ItemPedidoInput> items = new ArrayList<>();

  public PedidoInput clienteId(Long clienteId) {
    this.clienteId = clienteId;
    return this;
  }

  /**
   * Get clienteId
   * @return clienteId
  */
  @NotNull 
  @Schema(name = "clienteId", required = true)
  public Long getClienteId() {
    return clienteId;
  }

  public void setClienteId(Long clienteId) {
    this.clienteId = clienteId;
  }

  public PedidoInput items(List<ItemPedidoInput> items) {
    this.items = items;
    return this;
  }

  public PedidoInput addItemsItem(ItemPedidoInput itemsItem) {
    this.items.add(itemsItem);
    return this;
  }

  /**
   * Get items
   * @return items
  */
  @NotNull @Valid @Size(min = 1) 
  @Schema(name = "items", required = true)
  public List<ItemPedidoInput> getItems() {
    return items;
  }

  public void setItems(List<ItemPedidoInput> items) {
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PedidoInput pedidoInput = (PedidoInput) o;
    return Objects.equals(this.clienteId, pedidoInput.clienteId) &&
        Objects.equals(this.items, pedidoInput.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clienteId, items);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PedidoInput {\n");
    sb.append("    clienteId: ").append(toIndentedString(clienteId)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
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

