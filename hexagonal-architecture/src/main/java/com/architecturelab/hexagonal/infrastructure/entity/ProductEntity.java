// src/main/java/com/architecturelab/hexagonal/infrastructure/entity/ProductEntity.java
package com.architecturelab.hexagonal.infrastructure.entity;

import jakarta.persistence.*;

@Entity
//@Table(name = "products")
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = "name")) // pour eviter les doublons
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private double price;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public double getPrice() { return price; }
  public void setPrice(double price) { this.price = price; }
}
