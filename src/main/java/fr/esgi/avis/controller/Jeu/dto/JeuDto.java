package fr.esgi.avis.controller.Jeu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JeuDto {
    private Long id;
    private String nom;
    private Long editeurId;
    private Long genreId;
    private String description;
    private LocalDate dateDeSortie;
    private List<Long> plateformeIds;
    private String image;
    private float prix;
}