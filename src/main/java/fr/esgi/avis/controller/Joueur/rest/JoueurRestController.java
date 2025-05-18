package fr.esgi.avis.controller.Joueur.rest;

import fr.esgi.avis.controller.Joueur.JoueurController;
import fr.esgi.avis.controller.Joueur.dto.CreateJoueurDto;
import fr.esgi.avis.controller.Joueur.dto.JoueurDto;
import fr.esgi.avis.controller.Joueur.dto.UpdateAvatarDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/joueurs")
public class JoueurRestController {

    private final JoueurController joueurController;

    public JoueurRestController(JoueurController joueurController) {
        this.joueurController = joueurController;
    }

    @PostMapping
    public ResponseEntity<JoueurDto> create(@RequestBody CreateJoueurDto dto) {
        JoueurDto created = joueurController.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<JoueurDto> findAll() {
        return joueurController.getAll();
    }

    @GetMapping("/{id}")
    public JoueurDto findById(@PathVariable Long id) {
        return joueurController.getById(id);
    }

    @GetMapping("/pseudo/{pseudo}")
    public JoueurDto findByPseudo(@PathVariable String pseudo) {
        return joueurController.getByPseudo(pseudo);
    }

    @GetMapping("/count")
    public long count() {
        return joueurController.count();
    }

    @PutMapping("/{id}/avatar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAvatar(
            @PathVariable Long id,
            @RequestBody UpdateAvatarDto dto
    ) {
        joueurController.updateAvatar(id, dto);
    }
}