package fr.esgi.avis.domain.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;

import java.util.Optional;

/**
 * Interface définissant les opérations de persistance pour l'entité Avatar.
 */
public interface AvatarDataSourcePort {

    /**
     * Enregistre un avatar dans la source de données.
     * @param avatar L'avatar à sauvegarder.
     * @return L'avatar enregistré.
     */
    Avatar save(Avatar avatar);

    /**
     * Recherche un avatar en fonction de son identifiant.
     * @param id L'identifiant de l'avatar.
     * @return Un Optional contenant l'avatar trouvé, ou vide sinon.
     */
    Optional<Avatar> findById(Long id);

    /**
     * Supprime un avatar de la source de données via son identifiant.
     * @param id L'identifiant de l'avatar à supprimer.
     */
    void deleteById(Long id);
}

/*
Ce fichier respecte la Clean Architecture car il définit un port d'accès aux données pour le domaine Avatar,
permettant d'abstraire la couche de persistance et de conserver une logique métier indépendante des frameworks.
*/