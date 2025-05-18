package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Joueur.dto.JoueurDto;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Avis.model.Avis;

import java.util.stream.Collectors;

public class JoueurDtoMapper {

    private JoueurDtoMapper() { }

    public static JoueurDto toDto(Joueur joueur) {
        if (joueur == null) {
            return null;
        }
        return JoueurDto.builder()
                .id(joueur.getId())
                .pseudo(joueur.getPseudo())
                .email(joueur.getEmail())
                .dateDeNaissance(joueur.getDateDeNaissance())
                .avatarId(joueur.getAvatarId())
                .avisIds(
                        joueur.getAvis().stream()
                                .map(Avis::getId)
                                .collect(Collectors.toList())
                )
                .build();
    }
}