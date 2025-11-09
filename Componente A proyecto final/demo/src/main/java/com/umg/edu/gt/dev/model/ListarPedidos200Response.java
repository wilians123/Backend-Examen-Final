package com.umg.edu.gt.dev.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.umg.edu.gt.dev.model.Pedido;
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
 * ListarPedidos200Response
 */

@JsonTypeName("listarPedidos_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T01:40:33.368685200-06:00[America/Guatemala]")
public class ListarPedidos200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("content")
  @Valid
  private List<Pedido> content = null;

  @JsonProperty("totalElements")
  private Integer totalElements;

  @JsonProperty("totalPages")
  private Integer totalPages;

  @JsonProperty("currentPage")
  private Integer currentPage;

  public ListarPedidos200Response content(List<Pedido> content) {
    this.content = content;
    return this;
  }

  public ListarPedidos200Response addContentItem(Pedido contentItem) {
    if (this.content == null) {
      this.content = new ArrayList<>();
    }
    this.content.add(contentItem);
    return this;
  }

  /**
   * Get content
   * @return content
  */
  @Valid 
  @Schema(name = "content", required = false)
  public List<Pedido> getContent() {
    return content;
  }

  public void setContent(List<Pedido> content) {
    this.content = content;
  }

  public ListarPedidos200Response totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  
  @Schema(name = "totalElements", required = false)
  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public ListarPedidos200Response totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  
  @Schema(name = "totalPages", required = false)
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public ListarPedidos200Response currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * Get currentPage
   * @return currentPage
  */
  
  @Schema(name = "currentPage", required = false)
  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListarPedidos200Response listarPedidos200Response = (ListarPedidos200Response) o;
    return Objects.equals(this.content, listarPedidos200Response.content) &&
        Objects.equals(this.totalElements, listarPedidos200Response.totalElements) &&
        Objects.equals(this.totalPages, listarPedidos200Response.totalPages) &&
        Objects.equals(this.currentPage, listarPedidos200Response.currentPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalElements, totalPages, currentPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListarPedidos200Response {\n");
    sb.append("    content: ").append(toIndentedString(content)).append("\n");
    sb.append("    totalElements: ").append(toIndentedString(totalElements)).append("\n");
    sb.append("    totalPages: ").append(toIndentedString(totalPages)).append("\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
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

