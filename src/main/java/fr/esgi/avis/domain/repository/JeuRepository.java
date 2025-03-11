package fr.esgi.avis.domain.repository;

import fr.esgi.avis.domain.models.Jeu;

import java.util.List;
import java.util.Optional;

/**
 * Interface définissant les opérations d'accès aux données pour l'entité Jeu.
 * Le domaine reste indépendant de la technologie de persistance.
 */
public interface JeuRepository {
    List<Jeu> findAll();
    Optional<Jeu> findById(Long id);
    Jeu save(Jeu jeu);
}
