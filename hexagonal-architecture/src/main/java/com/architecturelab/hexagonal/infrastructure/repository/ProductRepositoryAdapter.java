// src/main/java/com/architecturelab/hexagonal/infrastructure/repository/ProductRepositoryAdapter.java
package com.architecturelab.hexagonal.infrastructure.repository;

import com.architecturelab.hexagonal.domain.model.Product;
import com.architecturelab.hexagonal.domain.port.ProductRepositoryPort;
import com.architecturelab.hexagonal.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

  private final ProductJpaRepository jpa;

  public ProductRepositoryAdapter(ProductJpaRepository jpa) {
    this.jpa = jpa;
  }

 @Override
public List<Product> findAll() {
    return jpa.findAll()
              .stream()
              .map(this::toDomain)
              .collect(Collectors.toList());
}

  @Override public Optional<Product> findById(Long id) {
    return jpa.findById(id).map(this::toDomain);
  }

  @Override public Product save(Product product) {
    ProductEntity entity = toEntity(product);
    ProductEntity saved = jpa.save(entity);
    return toDomain(saved);
  }

  @Override public void deleteById(Long id) {
    jpa.deleteById(id);
  }

  // Mapping simple (infra ↔ domain)
  private Product toDomain(ProductEntity e) {
    return new Product(e.getId(), e.getName(), e.getPrice());
  }

  private ProductEntity toEntity(Product p) {
    ProductEntity e = new ProductEntity();
    e.setId(p.getId());
    e.setName(p.getName());
    e.setPrice(p.getPrice());
    return e;
  }
}
