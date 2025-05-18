package fr.esgi.avis.controller.Utilisateur;

import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDto;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;

public class UtilisateurDtoMapper {

    private UtilisateurDtoMapper() { }

    public static UtilisateurDto toDto(Utilisateur u) {
        if (u == null) return null;
        UtilisateurDto.UtilisateurDtoBuilder b = UtilisateurDto.builder()
                .id(u.getId())
                .pseudo(u.getPseudo())
                .email(u.getEmail());
        if (u instanceof Joueur joueur) {
            b.type("joueur")
                    .dateDeNaissance(joueur.getDateDeNaissance().toString())
                    .avatarId(joueur.getAvatarId());
        } else if (u instanceof Moderateur m) {
            b.type("moderateur")
                    .numeroDeTelephone(m.getNumeroDeTelephone());
        }
        return b.build();
    }
}