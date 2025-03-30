package fr.esgi.avis.domain.Plateforme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.esgi.avis.domain.Jeu.model.Jeu;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plateforme {
    private Long id;

    @NonNull
    private String nom;

    @Builder.Default
    private List<Jeu> jeux = new ArrayList<>();

    private LocalDate dateDeSortie;
}

// Ce modèle métier respecte la Clean Architecture en encapsulant la logique liée à une Plateforme
// et en la rendant indépendante de toute considération technique (persistance, frameworks, etc.).
