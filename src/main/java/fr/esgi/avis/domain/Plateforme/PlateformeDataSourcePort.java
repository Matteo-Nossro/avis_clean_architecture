package fr.esgi.avis.domain.Plateforme;

import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.util.List;
import java.util.Optional;

public interface PlateformeDataSourcePort {
    Plateforme save(Plateforme plateforme);
    Optional<Plateforme> findById(Long id);
    Optional<Plateforme> findByNom(String nom);
    void deleteById(Long id);
    List<Plateforme> findAll();
    long count();
}

// Ce port définit les opérations de persistance pour l'entité Plateforme.
// Il permet d'isoler le domaine des détails techniques liés à la persistance.