package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.application.Avatar.AvatarEntity;
import fr.esgi.avis.application.Avis.AvisMapper;
import java.util.stream.Collectors;

public class JoueurMapper {

    public static Joueur toDomain(JoueurEntity joueurEntity) {
        if (joueurEntity == null) {
            return null;
        }
        // Extraction de l'identifiant de l'Avatar (relation unidirectionnelle)
        Long avatarId = (joueurEntity.getAvatar() != null) ? joueurEntity.getAvatar().getId() : null;
        return Joueur.builder()
                .id(joueurEntity.getId())
                .dateDeNaissance(joueurEntity.getDateDeNaissance())
                .avatarId(avatarId)
                .avis(joueurEntity.getAvis().stream()
                        .map(AvisMapper::toDomain)
                        .collect(Collectors.toList()))
                .pseudo(joueurEntity.getPseudo())
                .email(joueurEntity.getEmail())
                .motDePasse(joueurEntity.getMotDePasse())
                .build();
    }

    public static JoueurEntity toEntity(Joueur joueur) {
        if (joueur == null) {
            return null;
        }
        AvatarEntity avatarEntity = null;
        if(joueur.getAvatarId() != null) {
            avatarEntity = new AvatarEntity();
            avatarEntity.setId(joueur.getAvatarId());
        }
        return JoueurEntity.builder()
                .id(joueur.getId())
                .dateDeNaissance(joueur.getDateDeNaissance())
                .avatar(avatarEntity)
                .avis(joueur.getAvis().stream()
                        .map(AvisMapper::toEntity)
                        .collect(Collectors.toList()))
                .pseudo(joueur.getPseudo())
                .email(joueur.getEmail())
                .motDePasse(joueur.getMotDePasse())
                .build();
    }
}