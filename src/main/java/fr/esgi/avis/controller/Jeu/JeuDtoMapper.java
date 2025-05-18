package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import fr.esgi.avis.domain.Jeu.model.Jeu;

import java.util.stream.Collectors;

public class JeuDtoMapper {

    private JeuDtoMapper() { }

    public static JeuDto toDto(Jeu jeu) {
        if (jeu == null) {
            return null;
        }
        return JeuDto.builder()
                .id(jeu.getId())
                .nom(jeu.getNom())
                .editeurId(jeu.getEditeur().getId())
                .genreId(jeu.getGenre() != null ? jeu.getGenre().getId() : null)
                .description(jeu.getDescription())
                .dateDeSortie(jeu.getDateDeSortie())
                .plateformeIds(
                        jeu.getPlateformes()
                                .stream()
                                .map(p -> p.getId())
                                .collect(Collectors.toList())
                )
                .image(jeu.getImage())
                .prix(jeu.getPrix())
                .build();
    }
}