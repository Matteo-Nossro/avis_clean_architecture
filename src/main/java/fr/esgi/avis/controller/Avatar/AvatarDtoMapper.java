package fr.esgi.avis.controller.Avatar;

import fr.esgi.avis.controller.Avatar.dto.AvatarDTO;
import fr.esgi.avis.controller.Avatar.dto.CreateAvatarDTO;
import fr.esgi.avis.domain.Avatar.model.Avatar;

public class AvatarDtoMapper {
    public static AvatarDTO toDto(Avatar avatar) {
        if (avatar == null) return null;
        return new AvatarDTO(
                avatar.getId(),
                avatar.getNom(),
                avatar.getJoueurId()
        );
    }

    public static Avatar toDomain(CreateAvatarDTO dto) {
        if (dto == null) return null;
        return new Avatar(null, dto.getNom(), dto.getJoueurId());
    }
}