package fr.esgi.avis.domain.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;

import java.util.Optional;

public interface UtilisateurDataSourcePort {
    Utilisateur save(Utilisateur utilisateur);
    Optional<Utilisateur> findById(Long id);
    Optional<Utilisateur> findByPseudo(String pseudo);
    Optional<Utilisateur> findByEmail(String email);
    void deleteById(Long id);
}

// Ce fichier respecte la Clean Architecture en définissant un port pour l'accès aux données des Utilisateurs,
// ce qui permet de dissocier la logique métier des mécanismes techniques de persistance.
