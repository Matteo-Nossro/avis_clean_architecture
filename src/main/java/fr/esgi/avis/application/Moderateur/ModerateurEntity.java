package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.application.Utilisateur.UtilisateurEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ModerateurEntity extends UtilisateurEntity {
    private String numeroDeTelephone;

    public ModerateurEntity(String pseudo, String motDePasse, String email, String numeroDeTelephone) {
        super(pseudo, motDePasse, email);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}