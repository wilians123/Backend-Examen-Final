package com.logistica.proveedores.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.logistica.proveedores.model.Proveedor;
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
 * ListarProveedores200Response
 */

@JsonTypeName("listarProveedores_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-11T15:47:56.817647-06:00[America/Guatemala]")
public class ListarProveedores200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("content")
  @Valid
  private List<Proveedor> content = null;

  @JsonProperty("totalElements")
  private Integer totalElements;

  @JsonProperty("totalPages")
  private Integer totalPages;

  @JsonProperty("currentPage")
  private Integer currentPage;

  public ListarProveedores200Response content(List<Proveedor> content) {
    this.content = content;
    return this;
  }

  public ListarProveedores200Response addContentItem(Proveedor contentItem) {
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
  public List<Proveedor> getContent() {
    return content;
  }

  public void setContent(List<Proveedor> content) {
    this.content = content;
  }

  public ListarProveedores200Response totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  
  @Schema(name = "totalElements", example = "30", required = false)
  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public ListarProveedores200Response totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  
  @Schema(name = "totalPages", example = "3", required = false)
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public ListarProveedores200Response currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

  /**
   * Get currentPage
   * @return currentPage
  */
  
  @Schema(name = "currentPage", example = "0", required = false)
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
    ListarProveedores200Response listarProveedores200Response = (ListarProveedores200Response) o;
    return Objects.equals(this.content, listarProveedores200Response.content) &&
        Objects.equals(this.totalElements, listarProveedores200Response.totalElements) &&
        Objects.equals(this.totalPages, listarProveedores200Response.totalPages) &&
        Objects.equals(this.currentPage, listarProveedores200Response.currentPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalElements, totalPages, currentPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListarProveedores200Response {\n");
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

