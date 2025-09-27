// src/main/java/com/architecturelab/hexagonal/domain/port/DossierRepositoryPort.java
package com.architecturelab.hexagonal.domain.port;

import com.architecturelab.hexagonal.domain.model.Dossier;

import java.util.List;
import java.util.Optional;

public interface DossierRepositoryPort {
    List<Dossier> findAll();
    Optional<Dossier> findById(Long id);
    Optional<Dossier> findByReference(String reference);
    Dossier save(Dossier dossier);
    void deleteById(Long id);
}
