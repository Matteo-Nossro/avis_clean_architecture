package fr.esgi.avis.domain.Moderateur;

import fr.esgi.avis.domain.Moderateur.model.Moderateur;

import java.util.List;
import java.util.Optional;

public interface ModerateurDataSourcePort {
    Moderateur save(Moderateur moderateur);
    Optional<Moderateur> findById(Long id);
    Optional<Moderateur> findByPseudo(String pseudo);
    Optional<Moderateur> findByEmail(String email);
    Optional<Moderateur> findByNumeroDeTelephone(String numeroDeTelephone);
    void deleteById(Long id);
    void deleteByPseudo(String pseudo);
    long count();
    List<Moderateur> findAll();
}

// Ce fichier respecte la Clean Architecture en exposant un port d'accès aux données pour l'entité Moderateur,
// permettant de découpler la logique métier des détails techniques de la persistance.