package fr.esgi.avis.controller.Classification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClassificationDTO {
    private String nom;
    private String couleurRGB;
}
