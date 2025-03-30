package fr.esgi.avis.domain.Avis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/**
 * Modèle de domaine représentant un Avis.
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Avis {

    private Long id;

    @NonNull
    private String description;

    // Identifiant du joueur qui a publié l'avis
    @NonNull
    private Long joueurId;

    private Float note;

    private LocalDateTime dateDEnvoi;

    /**
     * Constructeur complet permettant d'initialiser tous les attributs.
     * @param id l'identifiant de l'avis
     * @param description la description de l'avis
     * @param joueurId l'identifiant du joueur ayant posté l'avis
     * @param note la note attribuée
     * @param dateDEnvoi la date d'envoi de l'avis
     */
    public Avis(Long id, String description, Long joueurId, Float note, LocalDateTime dateDEnvoi) {
        this.id = id;
        this.description = description;
        this.joueurId = joueurId;
        this.note = note;
        this.dateDEnvoi = dateDEnvoi;
    }
}
