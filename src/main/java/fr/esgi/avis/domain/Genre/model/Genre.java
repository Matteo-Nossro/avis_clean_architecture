package fr.esgi.avis.domain.Genre.model;

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
public class Genre {
    private Long id;

    @NonNull
    private String nom;

    @Builder.Default
    private List<Jeu> jeux = new ArrayList<>();
}

// Ce modèle métier respecte la Clean Architecture en encapsulant la logique liée à un Genre
// et en le rendant indépendant de toute considération technique de persistance.
// Il permet ainsi de concentrer les règles métier et facilite les tests et la maintenabilité.