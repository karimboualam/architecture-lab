// src/main/java/com/architecturelab/hexagonal/domain/port/ProductServicePort.java
package com.architecturelab.hexagonal.domain.port;

import com.architecturelab.hexagonal.domain.model.Product;

import java.util.List;

public interface ProductServicePort {
  List<Product> getAll();
  Product getById(Long id);
  Product create(Product product);
  Product update(Long id, Product product);
  void delete(Long id);

  // Ajout
  //List<Product> saveAll(List<Product> products);
}
