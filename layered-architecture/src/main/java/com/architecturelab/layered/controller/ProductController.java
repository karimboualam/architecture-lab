/*package com.architecturelab.layered.controller;

import com.architecturelab.layered.model.Product;
import com.architecturelab.layered.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
*/

package com.architecturelab.layered.controller;

import com.architecturelab.layered.dto.ProductDTO;
import com.architecturelab.layered.dto.ProductMapper;
import com.architecturelab.layered.model.Product;
import com.architecturelab.layered.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service, ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return mapper.toDtoList(service.findAll());
    }

    @GetMapping("/{id}")
    public ProductDTO getOne(@PathVariable Long id) {
        return mapper.toDto(service.findById(id));
    }

    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductDTO dto) {
        Product entity = mapper.toEntity(dto);
        return mapper.toDto(service.create(entity));
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        Product entity = mapper.toEntity(dto);
        return mapper.toDto(service.update(id, entity));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
