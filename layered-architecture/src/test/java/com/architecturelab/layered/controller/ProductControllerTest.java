package com.architecturelab.layered.controller;

import com.architecturelab.layered.dto.ProductDTO;
import com.architecturelab.layered.dto.ProductMapper;
import com.architecturelab.layered.model.Product;
import com.architecturelab.layered.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductService service;

    @Mock
    private ProductMapper mapper; // ✅ Mock simple, pas @MockBean

    @InjectMocks
    private ProductController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturnProducts() throws Exception {
        ProductDTO product = new ProductDTO();
        product.setId(1L);
        product.setName("Samsung S24");
        product.setPrice(399);

        // Service renvoie une liste vide
        when(service.findAll()).thenReturn(List.of());
        // Mapper renvoie une liste contenant notre produit DTO
        when(mapper.toDtoList(List.of())).thenReturn(List.of(product));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductDTO dto = new ProductDTO();
        dto.setName("iPhone 15");
        dto.setPrice(999);

        Product entity = new Product();
        entity.setId(1L);
        entity.setName("iPhone 15");
        entity.setPrice(999);

        // Mock conversion DTO → Entity
        when(mapper.toEntity(any(ProductDTO.class))).thenReturn(entity);
        // Mock conversion Entity → DTO
        when(mapper.toDto(any(Product.class))).thenReturn(dto);
        // Mock service.create
        when(service.create(any(Product.class))).thenReturn(entity);

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
