// src/main/java/com/architecturelab/hexagonal/infrastructure/config/PersistenceConfig.java
package com.architecturelab.hexagonal.infrastructure.config;

import com.architecturelab.hexagonal.domain.port.ProductRepositoryPort;
import com.architecturelab.hexagonal.domain.port.ProductServicePort;
import com.architecturelab.hexagonal.domain.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

  @Bean
  public ProductServicePort productServicePort(ProductRepositoryPort repositoryPort) {
    // Domain service is pure Java; we expose it as a Spring bean here
    return new ProductServiceImpl(repositoryPort);
  }
}
