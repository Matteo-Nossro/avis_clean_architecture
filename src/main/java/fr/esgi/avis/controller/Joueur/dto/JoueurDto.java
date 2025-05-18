package fr.esgi.avis.controller.Joueur.dto;

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
public class JoueurDto {
    private Long id;
    private String pseudo;
    private String email;
    private LocalDate dateDeNaissance;
    private Long avatarId;
    private List<Long> avisIds;
}