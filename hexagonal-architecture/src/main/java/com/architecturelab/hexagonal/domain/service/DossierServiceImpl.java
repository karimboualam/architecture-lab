// src/main/java/com/architecturelab/hexagonal/domain/service/DossierServiceImpl.java
package com.architecturelab.hexagonal.domain.service;

import com.architecturelab.hexagonal.domain.exception.DossierNotFoundException;
import com.architecturelab.hexagonal.domain.model.Dossier;
import com.architecturelab.hexagonal.domain.model.StatutDossier;
import com.architecturelab.hexagonal.domain.port.DossierRepositoryPort;
import com.architecturelab.hexagonal.domain.port.DossierServicePort;

import java.time.LocalDateTime;
import java.util.List;

public class DossierServiceImpl implements DossierServicePort {

    private final DossierRepositoryPort repo;

    public DossierServiceImpl(DossierRepositoryPort repo) {
        this.repo = repo;
    }

    @Override public List<Dossier> getAll() { return repo.findAll(); }

    @Override public Dossier getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new DossierNotFoundException(id));
    }

    @Override public Dossier create(Dossier dossier) {

        // Si aucun statut n’est fourni → valeur par défaut
        if (dossier.getStatut() == null) {
            dossier.setStatut(StatutDossier.EN_COURS);
        }
        dossier.setCreatedAt(LocalDateTime.now());
        dossier.setUpdatedAt(LocalDateTime.now());
        return repo.save(dossier);
    }

    @Override public Dossier update(Long id, Dossier input) {
        Dossier existing = getById(id);
        existing.setTitre(input.getTitre());
        existing.setDescription(input.getDescription());
        existing.setReference(input.getReference());
        existing.setUpdatedAt(LocalDateTime.now());
        return repo.save(existing);
    }

    @Override public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override public Dossier changerStatut(Long id, StatutDossier nouveauStatut) {
        Dossier d = getById(id);
        d.setStatut(nouveauStatut);
        d.setUpdatedAt(LocalDateTime.now());
        return repo.save(d);
    }


}
