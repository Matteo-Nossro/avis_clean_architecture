package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Classification.ClassificationMapper;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.application.Editeur.EditeurMapper;
import fr.esgi.avis.application.Genre.GenreMapper;
import fr.esgi.avis.application.Plateforme.PlateformeMapper;
import java.util.stream.Collectors;

public class JeuMapper {

    public static Jeu toDomain(JeuEntity jeuEntity) {
        if (jeuEntity == null) {
            return null;
        }
        return Jeu.builder()
                .id(jeuEntity.getId())
                .nom(jeuEntity.getNom())
                .editeur(EditeurMapper.toDomain(jeuEntity.getEditeur()))
                .genre(GenreMapper.toDomain(jeuEntity.getGenre()))
                .description(jeuEntity.getDescription())
                .dateDeSortie(jeuEntity.getDateDeSortie())
                .plateformes(jeuEntity.getPlateformes().stream()
                        .map(PlateformeMapper::toDomain)
                        .collect(Collectors.toList()))
                .image(jeuEntity.getImage())
                .prix(jeuEntity.getPrix())
                .classification(ClassificationMapper.toDomain(jeuEntity.getClassification()))
                .build();
    }

    public static JeuEntity toEntity(Jeu jeu) {
        if (jeu == null) {
            return null;
        }
        JeuEntity jeuEntity = new JeuEntity();
        jeuEntity.setId(jeu.getId());
        jeuEntity.setNom(jeu.getNom());
        jeuEntity.setEditeur(EditeurMapper.toEntity(jeu.getEditeur()));
        jeuEntity.setGenre(GenreMapper.toEntity(jeu.getGenre()));
        jeuEntity.setDescription(jeu.getDescription());
        jeuEntity.setDateDeSortie(jeu.getDateDeSortie());
        jeuEntity.setPlateformes(jeu.getPlateformes().stream()
                .map(PlateformeMapper::toEntity)
                .collect(Collectors.toList()));
        jeuEntity.setImage(jeu.getImage());
        jeuEntity.setPrix(jeu.getPrix());
        jeuEntity.setClassification(ClassificationMapper.toEntity(jeu.getClassification()));
        return jeuEntity;
    }
}