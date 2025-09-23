// src/main/java/com/architecturelab/hexagonal/domain/port/ProductRepositoryPort.java
package com.architecturelab.hexagonal.domain.port;

import com.architecturelab.hexagonal.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {
  List<Product> findAll();
  Optional<Product> findById(Long id);
  Product save(Product product);
  void deleteById(Long id);
}
