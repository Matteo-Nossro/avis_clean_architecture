package fr.esgi.avis.domain.Avatar.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Modèle de domaine représentant un Avatar.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avatar {

    private Long id;

    @NonNull
    private String nom;

    // Identifiant du joueur associé (sans lien direct vers un objet Joueur pour rester indépendant)
    private Long joueurId;

    /**
     * Constructeur complet permettant d'initialiser tous les attributs.
     * @param id l'identifiant de l'avatar
     * @param nom le nom de l'avatar
     * @param joueurId l'identifiant du joueur associé
     */
    public Avatar(Long id, String nom, Long joueurId) {
        this.id = id;
        this.nom = nom;
        this.joueurId = joueurId;
    }
}

/*
Ce modèle respecte la Clean Architecture car il représente le cœur du métier sans dépendre de bibliothèques techniques,
ce qui facilite le test unitaire et la maintenance.
*/