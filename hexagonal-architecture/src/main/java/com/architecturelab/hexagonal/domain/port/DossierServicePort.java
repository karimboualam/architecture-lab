// src/main/java/com/architecturelab/hexagonal/domain/port/DossierServicePort.java
package com.architecturelab.hexagonal.domain.port;

import com.architecturelab.hexagonal.domain.model.Dossier;
import com.architecturelab.hexagonal.domain.model.StatutDossier;

import java.util.List;

public interface DossierServicePort {
    List<Dossier> getAll();
    Dossier getById(Long id);
    Dossier create(Dossier dossier);
    Dossier update(Long id, Dossier dossier);
    void delete(Long id);

    // transitions
    Dossier changerStatut(Long id, StatutDossier nouveauStatut);
}
