package fr.esgi.avis.controller.Avatar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAvatarDTO {
    private String nom;
    private Long joueurId;
}