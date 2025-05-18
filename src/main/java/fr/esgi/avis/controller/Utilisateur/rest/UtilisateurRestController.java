package fr.esgi.avis.controller.Utilisateur.rest;

import fr.esgi.avis.controller.Utilisateur.UtilisateurController;
import fr.esgi.avis.controller.Utilisateur.dto.CreateUtilisateurDto;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurRestController {

    private final UtilisateurController ctrl;

    public UtilisateurRestController(UtilisateurController ctrl) {
        this.ctrl = ctrl;
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> create(@RequestBody CreateUtilisateurDto dto) {
        UtilisateurDto created = ctrl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public UtilisateurDto findById(@PathVariable Long id) {
        return ctrl.getById(id);
    }

    @GetMapping("/pseudo/{pseudo}")
    public UtilisateurDto findByPseudo(@PathVariable String pseudo) {
        return ctrl.getByPseudo(pseudo);
    }

    @GetMapping("/email/{email}")
    public UtilisateurDto findByEmail(@PathVariable String email) {
        return ctrl.getByEmail(email);
    }
}