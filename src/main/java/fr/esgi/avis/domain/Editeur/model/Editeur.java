package fr.esgi.avis.domain.Editeur.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;
import fr.esgi.avis.domain.Jeu.model.Jeu;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Editeur {
    private Long id;

    @NonNull
    private String nom;

    @Builder.Default
    private List<Jeu> jeux = new ArrayList<>();
}

// Ce modèle métier pour Editeur respecte la Clean Architecture en encapsulant
// les données et règles associées à un éditeur, tout en dissociant le domaine
// des considérations techniques (comme la persistance), facilitant ainsi son évolution et sa testabilité.