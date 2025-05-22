package fr.esgi.avis.controller.Avis.rest;

import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.AvisDtoMapper;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Avis.dto.CreateAvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avis")
@RequiredArgsConstructor
public class AvisRestController {

    private final AvisController avisController;

    @PostMapping
    public ResponseEntity<AvisDTO> create(@RequestBody CreateAvisDTO request) {
        Avis domain = AvisDtoMapper.toDomain(request);
        Avis created = avisController.createAvis(domain);
        AvisDTO dto = AvisDtoMapper.toDto(created);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisDTO> getById(@PathVariable Long id) {
        return avisController.getAvisById(id)
                .map(AvisDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        avisController.deleteAvis(id);
        return ResponseEntity.noContent().build();
    }
}