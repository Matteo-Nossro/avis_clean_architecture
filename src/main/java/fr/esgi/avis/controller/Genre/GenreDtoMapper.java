package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.GenreDto;
import fr.esgi.avis.domain.Genre.model.Genre;

public class GenreDtoMapper {

    private GenreDtoMapper() { }

    public static GenreDto toDto(Genre genre) {
        if (genre == null) {
            return null;
        }
        return GenreDto.builder()
                .id(genre.getId())
                .nom(genre.getNom())
                .build();
    }
}