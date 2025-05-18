package fr.esgi.avis.controller.Editeur.rest;

import fr.esgi.avis.controller.Editeur.EditeurController;
import fr.esgi.avis.controller.Editeur.dto.CreateEditeurDto;
import fr.esgi.avis.controller.Editeur.dto.EditeurDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editeurs")
public class EditeurRestController {

    private final EditeurController editeurController;

    public EditeurRestController(EditeurController editeurController) {
        this.editeurController = editeurController;
    }

    @PostMapping
    public ResponseEntity<EditeurDto> create(@RequestBody CreateEditeurDto dto) {
        EditeurDto created = editeurController.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<EditeurDto> findAll() {
        return editeurController.getAll();
    }

    @GetMapping("/{id}")
    public EditeurDto findById(@PathVariable Long id) {
        return editeurController.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        editeurController.delete(id);
    }
}