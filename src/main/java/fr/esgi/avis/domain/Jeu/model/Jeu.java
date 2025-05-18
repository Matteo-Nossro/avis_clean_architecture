package fr.esgi.avis.domain.Jeu.model;

import fr.esgi.avis.domain.Classification.model.Classification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Jeu {
    private Long id;

    @NonNull
    private String nom;

    @NonNull
    private Editeur editeur;

    private Genre genre;

    private Classification classification;

    private String description;

    private LocalDate dateDeSortie;

    @Builder.Default
    private List<Plateforme> plateformes = new ArrayList<>();

    private String image;

    private float prix;
}

// Ce modèle métier respecte la Clean Architecture en encapsulant la logique liée à un Jeu
// et en le séparant
