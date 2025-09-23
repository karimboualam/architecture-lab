// src/main/java/com/architecturelab/hexagonal/application/dto/ProductDTO.java
package com.architecturelab.hexagonal.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductDTO {
  private Long id;

  @NotBlank(message = "Name is required")
  private String name;

  @Positive(message = "Price must be positive")
  private double price;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }
}
