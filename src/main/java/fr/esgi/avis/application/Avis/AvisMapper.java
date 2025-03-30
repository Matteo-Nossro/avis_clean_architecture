package fr.esgi.avis.application.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.application.Jeu.JeuEntity;
import fr.esgi.avis.application.Joueur.JoueurEntity;

public class AvisMapper {

    public static Avis toDomain(AvisEntity avisEntity) {
        if (avisEntity == null) {
            throw new IllegalArgumentException("AvisEntity cannot be null");
        }
        return new Avis(
                avisEntity.getId(),
                avisEntity.getDescription(),
                avisEntity.getJoueur().getId(),
                avisEntity.getJeu().getId(),
                avisEntity.getNote(),
                avisEntity.getDateDEnvoi()
        );
    }

    public static AvisEntity toEntity(Avis avis) {
        if (avis == null) {
            throw new IllegalArgumentException("Avis cannot be null");
        }
        AvisEntity avisEntity = new AvisEntity();
        avisEntity.setId(avis.getId());
        avisEntity.setDescription(avis.getDescription());
        avisEntity.setNote(avis.getNote());
        avisEntity.setDateDEnvoi(avis.getDateDEnvoi());

        // Mapping du joueur via son identifiant
        JoueurEntity joueurEntity = new JoueurEntity();
        joueurEntity.setId(avis.getJoueurId());
        avisEntity.setJoueur(joueurEntity);

        // Mapping du jeu via son identifiant
        JeuEntity jeuEntity = new JeuEntity();
        jeuEntity.setId(avis.getJeuId());
        avisEntity.setJeu(jeuEntity);

        return avisEntity;
    }
}