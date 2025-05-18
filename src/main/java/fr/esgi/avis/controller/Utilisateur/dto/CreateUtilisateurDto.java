package fr.esgi.avis.controller.Utilisateur.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUtilisateurDto {
    private String type; // "joueur" ou "moderateur"
    private String pseudo;
    private String motDePasse;
    private String email;
    private String numeroDeTelephone; // null pour joueur
    private String dateDeNaissance;   // format ISO yyyy-MM-dd, null pour moderateur
    private Long avatarId;            // null pour moderateur
}