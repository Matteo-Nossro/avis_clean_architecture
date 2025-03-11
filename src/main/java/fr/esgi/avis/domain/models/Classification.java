package fr.esgi.avis.domain.models;

import java.util.List;

public class Classification {
    private final Long id;
    private final String nom;
    private final String couleurRGB;
    private List<Jeu> jeux;

    public Classification(Long id, String nom, String couleurRGB) {
        this.id = id;
        this.nom = nom;
        this.couleurRGB = couleurRGB;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
}
