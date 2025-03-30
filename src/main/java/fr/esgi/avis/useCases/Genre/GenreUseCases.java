package fr.esgi.avis.useCases.Genre;

import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Genre.GenreDataSourcePort;

import java.util.List;
import java.util.Optional;

public class GenreUseCases {

    private final GenreDataSourcePort genreDataSourcePort;

    public GenreUseCases(GenreDataSourcePort genreDataSourcePort) {
        this.genreDataSourcePort = genreDataSourcePort;
    }

    public Genre createGenre(String nom) {
        Genre genre = Genre.builder()
                .nom(nom)
                .build();
        return genreDataSourcePort.save(genre);
    }

    public Optional<Genre> getGenreById(Long id) {
        return genreDataSourcePort.findById(id);
    }

    public List<Genre> getAllGenres() {
        return genreDataSourcePort.findAll();
    }

    public void deleteGenre(Long id) {
        genreDataSourcePort.deleteById(id);
    }
}