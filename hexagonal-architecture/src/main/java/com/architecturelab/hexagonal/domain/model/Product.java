// src/main/java/com/architecturelab/hexagonal/domain/model/Product.java
package com.architecturelab.hexagonal.domain.model;

import java.util.Objects;

public class Product {
  private Long id;
  private String name;
  private double price;

  public Product() {}

  public Product(Long id, String name, double price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }

  public static Product of(String name, double price) {
    return new Product(null, name, price);
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Product)) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id);
  }
  @Override public int hashCode() { return Objects.hash(id); }
}
