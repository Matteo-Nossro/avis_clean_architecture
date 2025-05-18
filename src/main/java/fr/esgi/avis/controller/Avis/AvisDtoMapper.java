package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Avis.dto.CreateAvisDTO;
import fr.esgi.avis.domain.Avis.model.Avis;

public class AvisDtoMapper {

    public static AvisDTO toDto(Avis avis) {
        if (avis == null) return null;
        return new AvisDTO(
                avis.getId(),
                avis.getDescription(),
                avis.getJoueurId(),
                avis.getJeuId(),
                avis.getNote(),
                avis.getDateDEnvoi(),
                avis.getModerateurId()
        );
    }

    public static Avis toDomain(CreateAvisDTO dto) {
        if (dto == null) return null;
        return Avis.builder()
                .description(dto.getDescription())
                .joueurId(dto.getJoueurId())
                .jeuId(dto.getJeuId())
                .note(dto.getNote())
                .dateDEnvoi(null)
                .moderateurId(dto.getModerateurId())
                .build();
    }
}