package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Jeu.JeuEntity;
import fr.esgi.avis.application.Joueur.JoueurEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class AvisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @NonNull
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private JeuEntity jeu;

    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    private JoueurEntity joueur;

    private Float note;

    private LocalDateTime dateDEnvoi;
}