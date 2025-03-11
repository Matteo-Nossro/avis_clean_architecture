package fr.esgi.avis.domain.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Plateforme {
    private final Long id;
    private final String nom;
    private final LocalDate dateDeSortie;
    private List<Jeu> jeux;

    public Plateforme(Long id, String nom, LocalDate dateDeSortie) {
        this.id = id;
        this.nom = nom;
        this.dateDeSortie = dateDeSortie;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
}
