package fr.esgi.avis.domain.Joueur;

import fr.esgi.avis.domain.Joueur.model.Joueur;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JoueurDataSourcePort {
    Joueur save(Joueur joueur);
    Optional<Joueur> findByPseudo(String pseudo);
    Optional<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance);
    Optional<Joueur> findById(Long id);
    long count();
    List<Joueur> findAll();
    void updateAvatar(Long joueurId, Long avatarId);
}

// Ce fichier respecte la Clean Architecture en définissant un port d'accès aux données pour l'entité Joueur,
// permettant ainsi d'isoler les règles métier de la couche d'infrastructure.