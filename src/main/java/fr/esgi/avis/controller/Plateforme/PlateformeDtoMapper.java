package fr.esgi.avis.controller.Plateforme;

import fr.esgi.avis.controller.Plateforme.dto.PlateformeDto;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

public class PlateformeDtoMapper {

    private PlateformeDtoMapper() { }

    public static PlateformeDto toDto(Plateforme p) {
        if (p == null) return null;
        return PlateformeDto.builder()
                .id(p.getId())
                .nom(p.getNom())
                .build();
    }
}