package fr.esgi.avis.controller.Classification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import fr.esgi.avis.domain.Jeu.model.Jeu;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassificationDTO {
    private Long id;
    private String nom;
    private String couleurRGB;
    private List<Jeu> jeux; // TODO ou List<JeuDTO> si DTO pour Jeu existant
}
