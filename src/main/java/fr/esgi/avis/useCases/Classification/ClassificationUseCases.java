package fr.esgi.avis.useCases.Classification;

import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;

import java.util.List;
import java.util.Optional;

public class ClassificationUseCases {

    private final ClassificationDataSourcePort classificationDataSourcePort;

    public ClassificationUseCases(ClassificationDataSourcePort classificationDataSourcePort) {
        this.classificationDataSourcePort = classificationDataSourcePort;
    }

    public Classification createClassification(String nom, String couleurRGB) {
        Classification classification = Classification.builder()
                .nom(nom)
                .couleurRGB(couleurRGB)
                .build();
        return classificationDataSourcePort.save(classification);
    }

    public Optional<Classification> getClassificationById(Long id) {
        return classificationDataSourcePort.findById(id);
    }

    public List<Classification> getAllClassifications() {
        return classificationDataSourcePort.findAll();
    }

    public void deleteClassification(Long id) {
        classificationDataSourcePort.deleteById(id);
    }
}