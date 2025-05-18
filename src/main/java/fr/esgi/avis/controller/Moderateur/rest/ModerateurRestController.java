package fr.esgi.avis.controller.Moderateur.rest;

import fr.esgi.avis.controller.Moderateur.ModerateurController;
import fr.esgi.avis.controller.Moderateur.dto.CreateModerateurDto;
import fr.esgi.avis.controller.Moderateur.dto.ModerateurDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderateurs")
public class ModerateurRestController {

    private final ModerateurController ctrl;

    public ModerateurRestController(ModerateurController ctrl) {
        this.ctrl = ctrl;
    }

    @PostMapping
    public ResponseEntity<ModerateurDto> create(@RequestBody CreateModerateurDto dto) {
        ModerateurDto created = ctrl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<ModerateurDto> findAll() {
        return ctrl.getAll();
    }

    @GetMapping("/{id}")
    public ModerateurDto findById(@PathVariable Long id) {
        return ctrl.getById(id);
    }

    @GetMapping("/pseudo/{pseudo}")
    public ModerateurDto findByPseudo(@PathVariable String pseudo) {
        return ctrl.getByPseudo(pseudo);
    }

    @GetMapping("/email/{email}")
    public ModerateurDto findByEmail(@PathVariable String email) {
        return ctrl.getByEmail(email);
    }

    @GetMapping("/telephone/{numero}")
    public ModerateurDto findByNumero(@PathVariable("numero") String numero) {
        return ctrl.getByNumero(numero);
    }

    @GetMapping("/count")
    public long count() {
        return ctrl.count();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        ctrl.deleteById(id);
    }

    @DeleteMapping("/pseudo/{pseudo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByPseudo(@PathVariable String pseudo) {
        ctrl.deleteByPseudo(pseudo);
    }
}