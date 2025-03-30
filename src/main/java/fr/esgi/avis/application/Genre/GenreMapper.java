package fr.esgi.avis.application.Genre;

import fr.esgi.avis.domain.Genre.model.Genre;

public class GenreMapper {

    public static Genre toDomain(GenreEntity genreEntity) {
        if (genreEntity == null) {
            return null;
        }
        return Genre.builder()
                .id(genreEntity.getId())
                .nom(genreEntity.getNom())
                .build();
    }

    public static GenreEntity toEntity(Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(genre.getId());
        genreEntity.setNom(genre.getNom());
        return genreEntity;
    }
}