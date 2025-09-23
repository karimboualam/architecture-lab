// src/main/java/com/architecturelab/hexagonal/domain/service/ProductServiceImpl.java
package com.architecturelab.hexagonal.domain.service;

import com.architecturelab.hexagonal.domain.exception.ProductNotFoundException;
import com.architecturelab.hexagonal.domain.model.Product;
import com.architecturelab.hexagonal.domain.port.ProductRepositoryPort;
import com.architecturelab.hexagonal.domain.port.ProductServicePort;

import java.util.List;

public class ProductServiceImpl implements ProductServicePort {

  private final ProductRepositoryPort repository;

  public ProductServiceImpl(ProductRepositoryPort repository) {
    this.repository = repository;
  }

  @Override public List<Product> getAll() { return repository.findAll(); }

  @Override public Product getById(Long id) {
    return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Override public Product create(Product product) {
    return repository.save(product);
  }

  @Override public Product update(Long id, Product updated) {
    Product existing = getById(id);
    existing.setName(updated.getName());
    existing.setPrice(updated.getPrice());
    return repository.save(existing);
  }

  @Override public void delete(Long id) { repository.deleteById(id); }
}
