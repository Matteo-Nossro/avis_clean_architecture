package fr.esgi.avis.domain.Classification;

import fr.esgi.avis.domain.Classification.model.Classification;

import java.util.List;
import java.util.Optional;

public interface ClassificationDataSourcePort {
    Classification save(Classification classification);
    Optional<Classification> findById(Long id);
    void deleteById(Long id);
    List<Classification> findAll();
}

// Ce port respecte la Clean Architecture en définissant une interface pour l'accès aux données de l'entité Classification.
// Il permet d'isoler le domaine de la technique de persistance et facilite ainsi la maintenabilité et l'évolutivité.
