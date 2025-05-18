package fr.esgi.avis.controller.Genre.rest;

import fr.esgi.avis.controller.Genre.GenreController;
import fr.esgi.avis.controller.Genre.dto.CreateGenreDto;
import fr.esgi.avis.controller.Genre.dto.GenreDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreRestController {

    private final GenreController genreController;

    public GenreRestController(GenreController genreController) {
        this.genreController = genreController;
    }

    @PostMapping
    public ResponseEntity<GenreDto> create(@RequestBody CreateGenreDto dto) {
        GenreDto created = genreController.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<GenreDto> findAll() {
        return genreController.getAll();
    }

    @GetMapping("/{id}")
    public GenreDto findById(@PathVariable Long id) {
        return genreController.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        genreController.delete(id);
    }
}