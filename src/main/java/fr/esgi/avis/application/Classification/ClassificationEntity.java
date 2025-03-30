package fr.esgi.avis.application.Classification;

import fr.esgi.avis.application.Jeu.JeuEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "classification")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String couleurRGB;

    @OneToMany(mappedBy = "classification", cascade = CascadeType.ALL)
    private List<JeuEntity> jeux;
}