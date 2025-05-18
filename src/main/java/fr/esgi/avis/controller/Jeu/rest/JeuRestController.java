package fr.esgi.avis.controller.Jeu.rest;

import fr.esgi.avis.controller.Jeu.JeuController;
import fr.esgi.avis.controller.Jeu.dto.CreateJeuDto;
import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuRestController {

    private final JeuController jeuController;

    public JeuRestController(JeuController jeuController) {
        this.jeuController = jeuController;
    }

    @PostMapping
    public ResponseEntity<JeuDto> create(@RequestBody CreateJeuDto dto) {
        JeuDto created = jeuController.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<JeuDto> findAll() {
        return jeuController.getAll();
    }

    @GetMapping("/{id}")
    public JeuDto findById(@PathVariable Long id) {
        return jeuController.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        jeuController.delete(id);
    }
}