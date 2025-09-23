// src/main/java/com/architecturelab/hexagonal/application/service/ProductApplicationService.java
package com.architecturelab.hexagonal.application.service;

import com.architecturelab.hexagonal.domain.model.Product;
import com.architecturelab.hexagonal.domain.port.ProductServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductApplicationService {

  private final ProductServicePort productService;

  public ProductApplicationService(ProductServicePort productService) {
    this.productService = productService;
  }

  public List<Product> getAll() { return productService.getAll(); }
  public Product getById(Long id) { return productService.getById(id); }
  public Product create(Product p) { return productService.create(p); }
  public Product update(Long id, Product p) { return productService.update(id, p); }
  public void delete(Long id) { productService.delete(id); }
}
