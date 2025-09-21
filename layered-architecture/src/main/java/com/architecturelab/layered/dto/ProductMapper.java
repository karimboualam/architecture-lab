package com.architecturelab.layered.dto;

import com.architecturelab.layered.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
  //  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO dto);

    List<ProductDTO> toDtoList(List<Product> products);
}
