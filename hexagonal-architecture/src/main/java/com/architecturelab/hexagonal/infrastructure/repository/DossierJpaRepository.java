// src/main/java/com/architecturelab/hexagonal/infrastructure/repository/DossierJpaRepository.java
package com.architecturelab.hexagonal.infrastructure.repository;

import com.architecturelab.hexagonal.infrastructure.entity.DossierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DossierJpaRepository extends JpaRepository<DossierEntity, Long> {
    Optional<DossierEntity> findByReference(String reference);
}
