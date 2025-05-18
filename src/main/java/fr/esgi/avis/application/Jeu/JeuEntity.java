package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.application.Classification.ClassificationEntity;
import fr.esgi.avis.application.Editeur.EditeurEntity;
import fr.esgi.avis.application.Genre.GenreEntity;
import fr.esgi.avis.application.Plateforme.PlateformeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "jeu")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JeuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "jeu_seq")
    @SequenceGenerator(name = "jeu_seq")
    private Long id;

    @Column(nullable = false)
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY)
    private EditeurEntity editeur;

    @ManyToOne(fetch = FetchType.LAZY)
    private GenreEntity genre;

    @Lob
    private String description;

    private LocalDate dateDeSortie;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "jeu_plateforme",
            joinColumns = @JoinColumn(name = "jeu_id"),
            inverseJoinColumns = @JoinColumn(name = "plateforme_id")
    )
    private List<PlateformeEntity> plateformes;

    private String image;

    private float prix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_id")
    private ClassificationEntity classification;
}