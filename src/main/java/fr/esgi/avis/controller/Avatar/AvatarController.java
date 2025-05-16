package fr.esgi.avis.controller.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.useCases.Avatar.AvatarUseCases;
import java.util.Optional;

public class AvatarController {
    private final AvatarUseCases avatarUseCases;

    public AvatarController(AvatarUseCases avatarUseCases) {
        this.avatarUseCases = avatarUseCases;
    }

    public Avatar createAvatar(Avatar avatar) {
        return avatarUseCases.createAvatar(avatar.getNom(), avatar.getJoueurId());
    }

    public Optional<Avatar> getAvatarById(Long id) {
        return avatarUseCases.getAvatarById(id);
    }

    public void deleteAvatar(Long id) {
        avatarUseCases.deleteAvatar(id);
    }
}