package fr.esgi.avis.domain.models;

public class Avatar {
    private final Long id;
    private final String nom;
    private Joueur joueur; // Relation avec Joueur

    public Avatar(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public Joueur getJoueur() { return joueur; }
}
