package fr.esgi.avis.domain.Classification.model;

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
public class Classification {
    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private String couleurRGB;

    @Builder.Default
    private List<Jeu> jeux = new ArrayList<>();
}

// Ce modèle métier respecte la Clean Architecture en encapsulant les règles et données associées à une Classification,
// tout en la rendant indépendante des mécanismes techniques de persistance. Cette approche facilite les tests et l'évolution du code.