package fr.esgi.avis.domain.models;

import java.time.LocalDateTime;

public class Avis {
    private final Long id;
    private final String description;
    private final Jeu jeu;
    private final Joueur joueur;
    private final Float note;
    private final LocalDateTime dateDEnvoi;
    private Moderateur moderateur;

    public Avis(Long id, String description, Jeu jeu, Joueur joueur, Float note, LocalDateTime dateDEnvoi) {
        this.id = id;
        this.description = description;
        this.jeu = jeu;
        this.joueur = joueur;
        this.note = note;
        this.dateDEnvoi = dateDEnvoi;
    }

    public Long getId() { return id; }
    public String getDescription() { return description; }
    public Jeu getJeu() { return jeu; }
    public Joueur getJoueur() { return joueur; }
    public Float getNote() { return note; }
}
