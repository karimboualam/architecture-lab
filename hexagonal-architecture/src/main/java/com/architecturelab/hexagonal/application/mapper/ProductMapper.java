// src/main/java/com/architecturelab/hexagonal/application/mapper/ProductMapper.java
package com.architecturelab.hexagonal.application.mapper;

import com.architecturelab.hexagonal.application.dto.ProductDTO;
import com.architecturelab.hexagonal.domain.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  ProductDTO toDto(Product product);
  Product toDomain(ProductDTO dto);
  List<ProductDTO> toDtoList(List<Product> products);
}
