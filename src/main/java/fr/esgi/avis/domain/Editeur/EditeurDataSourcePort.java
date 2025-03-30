package fr.esgi.avis.domain.Editeur;

import fr.esgi.avis.domain.Editeur.model.Editeur;

import java.util.List;
import java.util.Optional;

public interface EditeurDataSourcePort {
    Editeur save(Editeur editeur);
    Optional<Editeur> findById(Long id);
    void deleteById(Long id);
    List<Editeur> findAll();
}

// Ce port d'accès aux données pour Editeur respecte la Clean Architecture en définissant
// une interface qui isole le domaine de la technique de persistance.
// Il permet de rendre l'implémentation concrète interchangeable sans impacter le domaine.