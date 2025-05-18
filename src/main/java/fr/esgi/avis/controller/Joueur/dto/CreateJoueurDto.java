package fr.esgi.avis.controller.Joueur.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateJoueurDto {
    private String pseudo;
    private String motDePasse;
    private String email;
    private LocalDate dateDeNaissance;
    private Long avatarId;
}