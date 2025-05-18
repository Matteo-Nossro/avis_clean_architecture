package fr.esgi.avis.controller.Classification;

import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
import fr.esgi.avis.controller.Classification.dto.CreateClassificationDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import java.util.List;

public class ClassificationDtoMapper {

    public static ClassificationDTO toDto(Classification classification) {
        if (classification == null) return null;
        // TODO Si nécessaire, mapper les jeux vers des DTO dédiés
        List<Jeu> jeux = classification.getJeux();
        return new ClassificationDTO(
                classification.getId(),
                classification.getNom(),
                classification.getCouleurRGB(),
                jeux
        );
    }

    public static Classification toDomain(CreateClassificationDTO dto) {
        if (dto == null) return null;
        return Classification.builder()
                .nom(dto.getNom())
                .couleurRGB(dto.getCouleurRGB())
                .build();
    }
}