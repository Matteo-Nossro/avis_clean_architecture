package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Editeur.EditeurDtoMapper;
import fr.esgi.avis.controller.Editeur.dto.EditeurDto;
import fr.esgi.avis.controller.Genre.GenreDtoMapper;
import fr.esgi.avis.controller.Genre.dto.GenreDto;
import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import fr.esgi.avis.domain.Jeu.model.Jeu;

import java.util.stream.Collectors;

public class JeuDtoMapper {

    private JeuDtoMapper() { }

    public static JeuDto toDto(Jeu jeu) {
        if (jeu == null) {
            return null;
        }

        EditeurDto editeurDto = null;
        editeurDto = EditeurDtoMapper.toDto(jeu.getEditeur());

        GenreDto genreDto = null;
        if (jeu.getGenre() != null) {
            genreDto = GenreDtoMapper.toDto(jeu.getGenre());
        }


        return JeuDto.builder()
                .id(jeu.getId())
                .nom(jeu.getNom())
                .editeur(editeurDto)
                .genre(genreDto)
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