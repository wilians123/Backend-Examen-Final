package com.umg.edu.gt.dev.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.umg.edu.gt.dev.model.Cliente;
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
 * ListarClientes200Response
 */

@JsonTypeName("listarClientes_200_response")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-11-08T01:40:33.368685200-06:00[America/Guatemala]")
public class ListarClientes200Response implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonProperty("content")
  @Valid
  private List<Cliente> content = null;

  @JsonProperty("totalElements")
  private Integer totalElements;

  @JsonProperty("totalPages")
  private Integer totalPages;

  @JsonProperty("currentPage")
  private Integer currentPage;

  public ListarClientes200Response content(List<Cliente> content) {
    this.content = content;
    return this;
  }

  public ListarClientes200Response addContentItem(Cliente contentItem) {
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
  public List<Cliente> getContent() {
    return content;
  }

  public void setContent(List<Cliente> content) {
    this.content = content;
  }

  public ListarClientes200Response totalElements(Integer totalElements) {
    this.totalElements = totalElements;
    return this;
  }

  /**
   * Get totalElements
   * @return totalElements
  */
  
  @Schema(name = "totalElements", example = "50", required = false)
  public Integer getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Integer totalElements) {
    this.totalElements = totalElements;
  }

  public ListarClientes200Response totalPages(Integer totalPages) {
    this.totalPages = totalPages;
    return this;
  }

  /**
   * Get totalPages
   * @return totalPages
  */
  
  @Schema(name = "totalPages", example = "5", required = false)
  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public ListarClientes200Response currentPage(Integer currentPage) {
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
    ListarClientes200Response listarClientes200Response = (ListarClientes200Response) o;
    return Objects.equals(this.content, listarClientes200Response.content) &&
        Objects.equals(this.totalElements, listarClientes200Response.totalElements) &&
        Objects.equals(this.totalPages, listarClientes200Response.totalPages) &&
        Objects.equals(this.currentPage, listarClientes200Response.currentPage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, totalElements, totalPages, currentPage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListarClientes200Response {\n");
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

