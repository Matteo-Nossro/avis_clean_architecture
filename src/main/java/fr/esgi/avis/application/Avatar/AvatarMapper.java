package fr.esgi.avis.application.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;

public class AvatarMapper {

    public static Avatar toDomain(AvatarEntity avatarEntity) {
        if (avatarEntity == null) {
            return null;
        }
        return new Avatar(
                avatarEntity.getId(),
                avatarEntity.getNom(),
                avatarEntity.getJoueurId()
        );
    }

    public static AvatarEntity toEntity(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        AvatarEntity avatarEntity = new AvatarEntity();
        avatarEntity.setId(avatar.getId());
        avatarEntity.setNom(avatar.getNom());
        avatarEntity.setJoueurId(avatar.getJoueurId());
        return avatarEntity;
    }
}