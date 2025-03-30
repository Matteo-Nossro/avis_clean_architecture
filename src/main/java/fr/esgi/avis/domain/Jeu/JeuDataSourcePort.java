package fr.esgi.avis.domain.Jeu;

import fr.esgi.avis.domain.Jeu.model.Jeu;

import java.util.List;
import java.util.Optional;

public interface JeuDataSourcePort {
    Jeu save(Jeu jeu);
    Optional<Jeu> findById(Long id);
    void deleteById(Long id);
    List<Jeu> findAll();
}

// Ce port définit les méthodes de persistance pour l'entité Jeu,
// assurant ainsi la séparation entre la logique métier et la technique de stockage.
