package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.domain.Moderateur.model.Moderateur;

public class ModerateurMapper {

    public static Moderateur toDomain(ModerateurEntity moderateurEntity) {
        if (moderateurEntity == null) {
            return null;
        }
        return Moderateur.builder()
                .id(moderateurEntity.getId())
                .pseudo(moderateurEntity.getPseudo())
                .motDePasse(moderateurEntity.getMotDePasse())
                .email(moderateurEntity.getEmail())
                .numeroDeTelephone(moderateurEntity.getNumeroDeTelephone())
                .build();
    }

    public static ModerateurEntity toEntity(Moderateur moderateur) {
        if (moderateur == null) {
            return null;
        }
        return ModerateurEntity.builder()
                .id(moderateur.getId())
                .pseudo(moderateur.getPseudo())
                .motDePasse(moderateur.getMotDePasse())
                .email(moderateur.getEmail())
                .numeroDeTelephone(moderateur.getNumeroDeTelephone())
                .build();
    }
}