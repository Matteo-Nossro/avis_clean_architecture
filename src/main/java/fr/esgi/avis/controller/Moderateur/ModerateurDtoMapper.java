package fr.esgi.avis.controller.Moderateur;

import fr.esgi.avis.controller.Moderateur.dto.ModerateurDto;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;

public class ModerateurDtoMapper {

    private ModerateurDtoMapper() { }

    public static ModerateurDto toDto(Moderateur m) {
        if (m == null) return null;
        return ModerateurDto.builder()
                .id(m.getId())
                .pseudo(m.getPseudo())
                .email(m.getEmail())
                .numeroDeTelephone(m.getNumeroDeTelephone())
                .build();
    }
}