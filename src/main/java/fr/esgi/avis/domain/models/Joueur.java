package fr.esgi.avis.domain.models;

import java.time.LocalDate;
import java.util.List;

public class Joueur extends Utilisateur {
    private final LocalDate dateDeNaissance;
    private List<Avis> avis;
    private Avatar avatar;

    public Joueur(Long id, String pseudo, String motDePasse, String email, LocalDate dateDeNaissance) {
        super(id, pseudo, motDePasse, email);
        this.dateDeNaissance = dateDeNaissance;
    }

    public LocalDate getDateDeNaissance() { return dateDeNaissance; }
}
