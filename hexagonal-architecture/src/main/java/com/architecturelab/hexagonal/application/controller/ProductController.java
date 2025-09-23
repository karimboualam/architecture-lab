// src/main/java/com/architecturelab/hexagonal/application/controller/ProductController.java
package com.architecturelab.hexagonal.application.controller;

import com.architecturelab.hexagonal.application.dto.ProductDTO;
import com.architecturelab.hexagonal.application.mapper.ProductMapper;
import com.architecturelab.hexagonal.application.service.ProductApplicationService;
import com.architecturelab.hexagonal.domain.model.Product;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductApplicationService service;
  private final ProductMapper mapper;

  public ProductController(ProductApplicationService service, ProductMapper mapper) {
    this.service = service;
    this.mapper = mapper;
  }

  @GetMapping
  public List<ProductDTO> getAll() {
    return mapper.toDtoList(service.getAll());
  }

  @GetMapping("/{id}")
  public ProductDTO getOne(@PathVariable Long id) {
    return mapper.toDto(service.getById(id));
  }

  @PostMapping
  public ProductDTO create(@Valid @RequestBody ProductDTO dto) {
    Product created = service.create(mapper.toDomain(dto));
    return mapper.toDto(created);
  }

  @PutMapping("/{id}")
  public ProductDTO update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
    Product updated = service.update(id, mapper.toDomain(dto));
    return mapper.toDto(updated);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}
