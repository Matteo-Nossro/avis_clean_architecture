package fr.esgi.avis.controller.Avis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvisDTO {
    private Long id;
    private String description;
    private Long joueurId;
    private Long jeuId;
    private Float note;
    private LocalDateTime dateDEnvoi;
}