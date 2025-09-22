package com.architecturelab.layered.service;

import com.architecturelab.layered.dto.ProductDTO;
import com.architecturelab.layered.dto.ProductMapper;
import com.architecturelab.layered.exception.ProductNotFoundException;
import com.architecturelab.layered.model.Product;
import com.architecturelab.layered.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    // Récupérer tous les produits en DTO
    public List<ProductDTO> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    // Récupérer par id
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return mapper.toDto(product);
    }

    // Créer un produit
    public ProductDTO create(ProductDTO dto) {
        Product product = mapper.toEntity(dto);
        Product saved = repository.save(product);
        return mapper.toDto(saved);
    }

    // Mettre à jour
    public ProductDTO update(Long id, ProductDTO dto) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());

        Product updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    // Supprimer
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
