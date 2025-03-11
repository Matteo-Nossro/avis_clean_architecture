package fr.esgi.avis.domain.models;

import java.util.List;

public class Genre {
    private final Long id;
    private final String nom;
    private List<Jeu> jeux;

    public Genre(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
}
