// src/main/java/com/architecturelab/hexagonal/domain/model/Dossier.java
package com.architecturelab.hexagonal.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Dossier {
    private Long id;
    private String reference;        // ex: DOS-2025-0001 (unique logique)
    private String titre;
    private String description;
    private StatutDossier statut = StatutDossier.EN_COURS;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Dossier() {}

    public Dossier(Long id, String reference, String titre, String description,
                   StatutDossier statut, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.reference = reference;
        this.titre = titre;
        this.description = description;
        this.statut = statut;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Dossier of(String reference, String titre, String description) {
        return new Dossier(null, reference, titre, description, StatutDossier.EN_COURS, LocalDateTime.now(), LocalDateTime.now());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public StatutDossier getStatut() { return statut; }
    public void setStatut(StatutDossier statut) { this.statut = statut; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dossier)) return false;
        Dossier dossier = (Dossier) o;
        return Objects.equals(id, dossier.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
