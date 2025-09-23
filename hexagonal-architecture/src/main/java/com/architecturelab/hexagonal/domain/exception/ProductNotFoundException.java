// src/main/java/com/architecturelab/hexagonal/domain/exception/ProductNotFoundException.java
package com.architecturelab.hexagonal.domain.exception;

public class ProductNotFoundException extends RuntimeException {
  public ProductNotFoundException(Long id) {
    super("Product not found with id " + id);
  }
}
