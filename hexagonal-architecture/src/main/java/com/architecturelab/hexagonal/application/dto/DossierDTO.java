// src/main/java/com/architecturelab/hexagonal/application/dto/DossierDTO.java
package com.architecturelab.hexagonal.application.dto;

import com.architecturelab.hexagonal.domain.model.StatutDossier;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class DossierDTO {
    private Long id;

    @NotBlank(message = "reference is required")
    private String reference;

    @NotBlank(message = "titre is required")
    private String titre;

    private String description;
    private StatutDossier statut;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

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
}
