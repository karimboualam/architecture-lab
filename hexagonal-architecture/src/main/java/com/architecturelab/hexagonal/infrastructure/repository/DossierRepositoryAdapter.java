// src/main/java/com/architecturelab/hexagonal/infrastructure/repository/DossierRepositoryAdapter.java
package com.architecturelab.hexagonal.infrastructure.repository;

import com.architecturelab.hexagonal.domain.model.Dossier;
import com.architecturelab.hexagonal.domain.port.DossierRepositoryPort;
import com.architecturelab.hexagonal.infrastructure.entity.DossierEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DossierRepositoryAdapter implements DossierRepositoryPort {

    private final DossierJpaRepository jpa;

    public DossierRepositoryAdapter(DossierJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override public List<Dossier> findAll() {
        return jpa.findAll().stream().map(this::toDomain).toList();
    }

    @Override public Optional<Dossier> findById(Long id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override public Optional<Dossier> findByReference(String reference) {
        return jpa.findByReference(reference).map(this::toDomain);
    }

    @Override public Dossier save(Dossier dossier) {
        DossierEntity saved = jpa.save(toEntity(dossier));
        return toDomain(saved);
    }

    @Override public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    private Dossier toDomain(DossierEntity e) {
        return new Dossier(
                e.getId(),
                e.getReference(),
                e.getTitre(),
                e.getDescription(),
                e.getStatut(),
                e.getCreatedAt(),
                e.getUpdatedAt()
        );
    }

    private DossierEntity toEntity(Dossier d) {
        DossierEntity e = new DossierEntity();
        e.setId(d.getId());
        e.setReference(d.getReference());
        e.setTitre(d.getTitre());
        e.setDescription(d.getDescription());
        e.setStatut(d.getStatut());
        e.setCreatedAt(d.getCreatedAt());
        e.setUpdatedAt(d.getUpdatedAt());
        return e;
    }
}
