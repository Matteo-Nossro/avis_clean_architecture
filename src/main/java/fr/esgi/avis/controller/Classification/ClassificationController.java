package fr.esgi.avis.controller.Classification;

import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.useCases.Classification.ClassificationUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClassificationController {

    private final ClassificationUseCases classificationUseCases;

    public Classification createClassification(Classification classification) {
        return classificationUseCases.createClassification(
                classification.getNom(),
                classification.getCouleurRGB()
        );
    }

    public Optional<Classification> getClassificationById(Long id) {
        return classificationUseCases.getClassificationById(id);
    }

    public List<Classification> getAllClassifications() {
        return classificationUseCases.getAllClassifications();
    }

    public void deleteClassification(Long id) {
        classificationUseCases.deleteClassification(id);
    }
}