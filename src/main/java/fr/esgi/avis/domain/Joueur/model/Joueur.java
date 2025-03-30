package fr.esgi.avis.domain.Joueur.model;

import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joueur extends Utilisateur {
    private LocalDate dateDeNaissance;

    @Builder.Default
    private List<Avis> avis = new ArrayList<>();

    private Avatar avatar;
}

// Ce fichier respecte la Clean Architecture en encapsulant la logique métier de Joueur,
// tout en isolant le modèle des préoccupations techniques (persistance, frameworks, etc.).