package fr.esgi.avis.useCases.Avatar;

import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;

import java.util.Optional;

public class AvatarUseCases {

    private final AvatarDataSourcePort avatarDataSourcePort;

    public AvatarUseCases(AvatarDataSourcePort avatarDataSourcePort) {
        this.avatarDataSourcePort = avatarDataSourcePort;
    }

    public Avatar createAvatar(String nom, Long joueurId) {
        Avatar avatar = new Avatar(null, nom, joueurId);
        return avatarDataSourcePort.save(avatar);
    }

    public Optional<Avatar> getAvatarById(Long id) {
        return avatarDataSourcePort.findById(id);
    }

    public void deleteAvatar(Long id) {
        avatarDataSourcePort.deleteById(id);
    }
}