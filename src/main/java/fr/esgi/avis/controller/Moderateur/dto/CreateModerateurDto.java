package fr.esgi.avis.controller.Moderateur.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateModerateurDto {
    private String pseudo;
    private String motDePasse;
    private String email;
    private String numeroDeTelephone;
}