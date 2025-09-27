// src/main/java/com/architecturelab/hexagonal/application/controller/DossierController.java
package com.architecturelab.hexagonal.application.controller;

import com.architecturelab.hexagonal.application.dto.DossierDTO;
import com.architecturelab.hexagonal.application.mapper.DossierMapper;
import com.architecturelab.hexagonal.application.service.DossierApplicationService;
import com.architecturelab.hexagonal.domain.model.Dossier;
import com.architecturelab.hexagonal.domain.model.StatutDossier;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dossiers")
public class DossierController {

    private final DossierApplicationService service;
    private final DossierMapper mapper;

    public DossierController(DossierApplicationService service, DossierMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public List<DossierDTO> getAll() { return mapper.toDtoList(service.getAll()); }

    @GetMapping("/{id}")
    public DossierDTO getOne(@PathVariable Long id) { return mapper.toDto(service.getById(id)); }

    @PostMapping
    public DossierDTO create(@Valid @RequestBody DossierDTO dto) {
        Dossier created = service.create(mapper.toDomain(dto));
        return mapper.toDto(created);
    }

    @PutMapping("/{id}")
    public DossierDTO update(@PathVariable Long id, @Valid @RequestBody DossierDTO dto) {
        Dossier updated = service.update(id, mapper.toDomain(dto));
        return mapper.toDto(updated);
    }

    @PatchMapping("/{id}/statut")
    public DossierDTO changerStatut(@PathVariable Long id, @RequestParam("value") StatutDossier statut) {
        return mapper.toDto(service.changerStatut(id, statut));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
