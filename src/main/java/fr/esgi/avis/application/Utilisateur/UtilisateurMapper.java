package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;

public class UtilisateurMapper {

    public static Utilisateur toDomain(UtilisateurEntity utilisateurEntity) {
        if (utilisateurEntity == null) {
            return null;
        }
        // Délégation au mapper spécifique selon le type
        if (utilisateurEntity instanceof fr.esgi.avis.application.Joueur.JoueurEntity joueurEntity) {
            return fr.esgi.avis.application.Joueur.JoueurMapper.toDomain(joueurEntity);
        } else if (utilisateurEntity instanceof fr.esgi.avis.application.Moderateur.ModerateurEntity moderateurEntity) {
            return fr.esgi.avis.application.Moderateur.ModerateurMapper.toDomain(moderateurEntity);
        }
        throw new IllegalArgumentException("Type d'utilisateur non géré : "
                + utilisateurEntity.getClass().getSimpleName());
    }

    public static UtilisateurEntity toEntity(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }
        if (utilisateur instanceof Joueur joueur) {
            return fr.esgi.avis.application.Joueur.JoueurMapper.toEntity(joueur);
        } else if (utilisateur instanceof Moderateur moderateur) {
            return fr.esgi.avis.application.Moderateur.ModerateurMapper.toEntity(moderateur);
        }
        throw new IllegalArgumentException("Type d'utilisateur non géré : "
                + utilisateur.getClass().getSimpleName());
    }
}