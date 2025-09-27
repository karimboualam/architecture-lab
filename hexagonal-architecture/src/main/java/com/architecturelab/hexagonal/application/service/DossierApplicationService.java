// src/main/java/com/architecturelab/hexagonal/application/service/DossierApplicationService.java
package com.architecturelab.hexagonal.application.service;

import com.architecturelab.hexagonal.domain.model.Dossier;
import com.architecturelab.hexagonal.domain.model.StatutDossier;
import com.architecturelab.hexagonal.domain.port.DossierServicePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DossierApplicationService {

    private final DossierServicePort service;

    public DossierApplicationService(DossierServicePort service) {
        this.service = service;
    }

    public List<Dossier> getAll() { return service.getAll(); }
    public Dossier getById(Long id) { return service.getById(id); }
    public Dossier create(Dossier d) { return service.create(d); }
    public Dossier update(Long id, Dossier d) { return service.update(id, d); }
    public void delete(Long id) { service.delete(id); }
    public Dossier changerStatut(Long id, StatutDossier s) { return service.changerStatut(id, s); }
}
