// src/main/java/com/architecturelab/hexagonal/infrastructure/config/PersistenceConfig.java
package com.architecturelab.hexagonal.infrastructure.config;

import com.architecturelab.hexagonal.domain.port.DossierRepositoryPort;
import com.architecturelab.hexagonal.domain.port.DossierServicePort;
import com.architecturelab.hexagonal.domain.service.DossierServiceImpl;
import com.architecturelab.hexagonal.domain.port.ProductRepositoryPort;
import com.architecturelab.hexagonal.domain.port.ProductServicePort;
import com.architecturelab.hexagonal.domain.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

  @Bean
  public DossierServicePort dossierServicePort(DossierRepositoryPort repo) {
    return new DossierServiceImpl(repo);
  }

  @Bean
  public ProductServicePort productServicePort(ProductRepositoryPort repositoryPort) {
    return new ProductServiceImpl(repositoryPort);
  }
}
