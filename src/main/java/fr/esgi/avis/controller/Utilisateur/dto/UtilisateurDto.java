package fr.esgi.avis.controller.Utilisateur.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurDto {
    private Long id;
    private String type; // "joueur" ou "moderateur"
    private String pseudo;
    private String email;
    private String numeroDeTelephone; // pour moderateur
    private String dateDeNaissance;   // pour joueur
    private Long avatarId;            // pour joueur
}