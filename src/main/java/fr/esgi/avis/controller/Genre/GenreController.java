package fr.esgi.avis.controller.Genre;

import fr.esgi.avis.controller.Genre.dto.CreateGenreDto;
import fr.esgi.avis.controller.Genre.dto.GenreDto;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenreController {

    private final GenreUseCases genreUseCases;

    public GenreController(GenreUseCases genreUseCases) {
        this.genreUseCases = genreUseCases;
    }

    public GenreDto create(CreateGenreDto dto) {
        Genre created = genreUseCases.createGenre(dto.getNom());
        return GenreDtoMapper.toDto(created);
    }

    public List<GenreDto> getAll() {
        return genreUseCases.getAllGenres()
                .stream()
                .map(GenreDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public GenreDto getById(Long id) {
        return genreUseCases.getGenreById(id)
                .map(GenreDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre introuvable"));
    }

    public void delete(Long id) {
        genreUseCases.deleteGenre(id);
    }
}