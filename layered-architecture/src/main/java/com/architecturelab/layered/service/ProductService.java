package com.architecturelab.layered.service;

import com.architecturelab.layered.exception.ProductNotFoundException;
import com.architecturelab.layered.model.Product;
import com.architecturelab.layered.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
      //  return repository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = findById(id);
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
