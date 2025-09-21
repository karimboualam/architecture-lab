package com.architecturelab.layered.dto;

import com.architecturelab.layered.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDto(Product product) {
        if (product == null) return null;
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        return entity;
    }

    @Override
    public List<ProductDTO> toDtoList(List<Product> products) {
        return products.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
