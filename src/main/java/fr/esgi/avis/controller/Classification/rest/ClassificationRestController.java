package fr.esgi.avis.controller.Classification.rest;

import fr.esgi.avis.controller.Classification.ClassificationController;
import fr.esgi.avis.controller.Classification.ClassificationDtoMapper;
import fr.esgi.avis.controller.Classification.dto.ClassificationDTO;
import fr.esgi.avis.controller.Classification.dto.CreateClassificationDTO;
import fr.esgi.avis.domain.Classification.model.Classification;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/classifications")
@RequiredArgsConstructor
public class ClassificationRestController {

    private final ClassificationController classificationController;

    @PostMapping
    public ResponseEntity<ClassificationDTO> create(@RequestBody CreateClassificationDTO request) {
        Classification domain = ClassificationDtoMapper.toDomain(request);
        Classification created = classificationController.createClassification(domain);
        ClassificationDTO dto = ClassificationDtoMapper.toDto(created);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassificationDTO> getById(@PathVariable Long id) {
        return classificationController.getClassificationById(id)
                .map(ClassificationDtoMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ClassificationDTO>> getAll() {
        List<Classification> list = classificationController.getAllClassifications();
        List<ClassificationDTO> dtos = list.stream()
                .map(ClassificationDtoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classificationController.deleteClassification(id);
        return ResponseEntity.noContent().build();
    }
}
