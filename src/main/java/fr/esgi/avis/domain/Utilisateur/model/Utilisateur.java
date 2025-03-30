package fr.esgi.avis.domain.Utilisateur.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;

@Data
@SuperBuilder
@NoArgsConstructor
@RequiredArgsConstructor
public abstract class Utilisateur {
    protected Long id;

    @NonNull
    @Column(length = 80)
    @Size(max = 80)
    protected String pseudo;

    @NonNull
    protected String motDePasse;

    @NonNull
    protected String email;
}

// Ce fichier respecte la Clean Architecture en encapsulant les attributs communs à tous les Utilisateurs
// dans un modèle métier abstrait, permettant une extension cohérente et une séparation nette entre la logique métier et les détails techniques.