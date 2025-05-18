package fr.esgi.avis.controller.Plateforme.rest;

import fr.esgi.avis.controller.Plateforme.PlateformeController;
import fr.esgi.avis.controller.Plateforme.dto.CreatePlateformeDto;
import fr.esgi.avis.controller.Plateforme.dto.PlateformeDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plateformes")
public class PlateformeRestController {

    private final PlateformeController ctrl;

    public PlateformeRestController(PlateformeController ctrl) {
        this.ctrl = ctrl;
    }

    @PostMapping
    public ResponseEntity<PlateformeDto> create(@RequestBody CreatePlateformeDto dto) {
        PlateformeDto created = ctrl.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<PlateformeDto> findAll() {
        return ctrl.getAll();
    }

    @GetMapping("/{id}")
    public PlateformeDto findById(@PathVariable Long id) {
        return ctrl.getById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ctrl.delete(id);
    }
}