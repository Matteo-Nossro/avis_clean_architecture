package fr.esgi.avis.controller.Avis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAvisDTO {
    private String description;
    private Long joueurId;
    private Long jeuId;
    private Float note;
    private Long moderateurId;
}