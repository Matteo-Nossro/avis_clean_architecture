package fr.esgi.avis.domain.models;

import java.time.LocalDate;

public class Jeu {
    private final Long id;
    private final String nom;
    private final Editeur editeur;
    private final LocalDate dateDeSortie;
    private boolean estDisponible;

    public Jeu(Long id, String nom, Editeur editeur, LocalDate dateDeSortie) {
        this.id = id;
        this.nom = nom;
        this.editeur = editeur;
        this.dateDeSortie = dateDeSortie;
        this.estDisponible = true;
    }

    public void marquerCommeIndisponible() {
        this.estDisponible = false;
    }

    public boolean estDisponible() {
        return estDisponible;
    }
}
