package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Jeu.JeuMapper;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.application.Jeu.JeuEntity;
import java.util.List;
import java.util.stream.Collectors;

public class ClassificationMapper {

    public static Classification toDomain(ClassificationEntity classificationEntity) {
        if (classificationEntity == null) {
            return null;
        }
        return Classification.builder()
                .id(classificationEntity.getId())
                .nom(classificationEntity.getNom())
                .couleurRGB(classificationEntity.getCouleurRGB())
                .jeux(classificationEntity.getJeux() != null ?
                        classificationEntity.getJeux().stream()
                                .map(JeuMapper::toDomain)
                                .collect(Collectors.toList())
                        : null)
                .build();
    }

    public static ClassificationEntity toEntity(Classification classification) {
        if (classification == null) {
            return null;
        }
        ClassificationEntity classificationEntity = new ClassificationEntity();
        classificationEntity.setId(classification.getId());
        classificationEntity.setNom(classification.getNom());
        classificationEntity.setCouleurRGB(classification.getCouleurRGB());
        // Optionnel : mapper les jeux de façon minimale (uniquement l'ID)
        if (classification.getJeux() != null) {
            List<JeuEntity> jeux = classification.getJeux().stream().map(jeu -> {
                JeuEntity jeuEntity = new JeuEntity();
                jeuEntity.setId(jeu.getId());
                return jeuEntity;
            }).collect(Collectors.toList());
            classificationEntity.setJeux(jeux);
        }
        return classificationEntity;
    }
}