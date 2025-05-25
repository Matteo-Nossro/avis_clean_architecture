package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Jeu.JeuMapper;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.application.Jeu.JeuEntity;

import java.util.stream.Collectors;

public class ClassificationMapper {

    /**
     * Mappe une entité ClassificationEntity vers le modèle de domaine Classification,
     * en utilisant un mapping "shallow" pour les jeux afin d'éviter la récursion.
     */
    public static Classification toDomain(ClassificationEntity e) {
        if (e == null) {
            return null;
        }
        Classification classification = Classification.builder()
                .id(e.getId())
                .nom(e.getNom())
                .couleurRGB(e.getCouleurRGB())
                .build();

        if (e.getJeux() != null) {
            classification.setJeux(
                    e.getJeux().stream()
                            .map(JeuMapper::toDomainShallow)
                            .collect(Collectors.toList())
            );
        }

        return classification;
    }

    /**
     * Mappe un modèle de domaine Classification vers l'entité ClassificationEntity,
     * ne remplissant que l'ID des jeux liés pour éviter les dépendances circulaires.
     */
    public static ClassificationEntity toEntity(Classification c) {
        if (c == null) {
            return null;
        }
        ClassificationEntity e = new ClassificationEntity();
        e.setId(c.getId());
        e.setNom(c.getNom());
        e.setCouleurRGB(c.getCouleurRGB());

        if (c.getJeux() != null) {
            e.setJeux(
                    c.getJeux().stream()
                            .map(j -> {
                                JeuEntity je = new JeuEntity();
                                je.setId(j.getId());
                                return je;
                            })
                            .collect(Collectors.toList())
            );
        }

        return e;
    }
}
