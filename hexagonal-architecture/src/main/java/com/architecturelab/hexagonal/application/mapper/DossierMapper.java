// src/main/java/com/architecturelab/hexagonal/application/mapper/DossierMapper.java
package com.architecturelab.hexagonal.application.mapper;

import com.architecturelab.hexagonal.application.dto.DossierDTO;
import com.architecturelab.hexagonal.domain.model.Dossier;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DossierMapper {
    DossierDTO toDto(Dossier dossier);
    Dossier toDomain(DossierDTO dto);
    List<DossierDTO> toDtoList(List<Dossier> dossiers);
}
