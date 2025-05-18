package fr.esgi.avis.domain.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;

import java.util.Optional;

/**
 * Interface de port pour la gestion de l'entité Avis.
 */
public interface AvisDataSourcePort {

    /**
     * Sauvegarde un avis dans la source de données.
     * @param avis L'avis à enregistrer.
     * @return L'avis sauvegardé.
     */
    Avis save(Avis avis);

    /**
     * Recherche un avis à partir de son identifiant.
     * @param id L'identifiant de l'avis.
     * @return Un Optional contenant l'avis trouvé, ou vide sinon.
     */
    Optional<Avis> findById(Long id);

    /**
     * Supprime un avis de la source de données en fonction de son identifiant.
     * @param id L'identifiant de l'avis à supprimer.
     */
    void deleteById(Long id);

    long count();
}

/*
Ce port garantit une abstraction complète de la persistance pour l'entité Avis, permettant au domaine de rester indépendant
des choix technologiques.
*/