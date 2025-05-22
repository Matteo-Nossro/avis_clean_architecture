package fr.esgi.avis.controller.Jeu.dto;

import fr.esgi.avis.controller.Editeur.dto.EditeurDto;
import fr.esgi.avis.controller.Genre.dto.GenreDto;
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
    private EditeurDto editeur;
    private GenreDto genre;
    private String description;
    private LocalDate dateDeSortie;
    private List<Long> plateformeIds;
    private String image;
    private float prix;
}