package com.architecturelab.layered.repository;

import com.architecturelab.layered.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
