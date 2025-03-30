package fr.esgi.avis.domain.Moderateur.model;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Moderateur extends Utilisateur {
    private String numeroDeTelephone;

    // Constructeur simplifié pour une initialisation rapide
    public Moderateur(String pseudo, String motDePasse, String email, String numeroDeTelephone) {
        super(pseudo, motDePasse, email);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}

// Ce fichier respecte la Clean Architecture en encapsulant le modèle métier Moderateur,
// en isolant les règles métier des aspects techniques (persistance, frameworks, etc.), ce qui améliore la testabilité et la maintenabilité.