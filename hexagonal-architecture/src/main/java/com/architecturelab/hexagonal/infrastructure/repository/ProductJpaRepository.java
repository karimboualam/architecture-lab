// src/main/java/com/architecturelab/hexagonal/infrastructure/repository/ProductJpaRepository.java
package com.architecturelab.hexagonal.infrastructure.repository;

import com.architecturelab.hexagonal.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {}
