package fr.esgi.avis.application.Avis;

import fr.esgi.avis.application.Jeu.JeuEntity;
import fr.esgi.avis.application.Joueur.JoueurEntity;
import fr.esgi.avis.application.Moderateur.ModerateurEntity;
import fr.esgi.avis.domain.Avis.model.Avis;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AvisMapperTest {

    @Test
    void toDomain_WithNullEntity_ShouldReturnNull() {
        // Act
        Avis result = AvisMapper.toDomain(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toDomain_WithValidEntity_ShouldMapCorrectly() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();

        JeuEntity jeuEntity = new JeuEntity();
        jeuEntity.setId(2L);

        JoueurEntity joueurEntity = new JoueurEntity();
        joueurEntity.setId(3L);

        ModerateurEntity moderateurEntity = new ModerateurEntity();
        moderateurEntity.setId(5L);

        AvisEntity entity = new AvisEntity();
        entity.setId(1L);
        entity.setDescription("Très bon jeu");
        entity.setJeu(jeuEntity);
        entity.setJoueur(joueurEntity);
        entity.setNote(4.5F);
        entity.setDateDEnvoi(now);
        entity.setModerateur(moderateurEntity);

        // Act
        Avis result = AvisMapper.toDomain(entity);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Très bon jeu", result.getDescription());
        assertEquals(2L, result.getJeuId());
        assertEquals(3L, result.getJoueurId());
        assertEquals(4.5F, result.getNote());
        assertEquals(now, result.getDateDEnvoi());
        assertEquals(5L, result.getModerateurId());
    }

    @Test
    void toEntity_WithNullDomain_ShouldReturnNull() {
        // Act
        AvisEntity result = AvisMapper.toEntity(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toEntity_WithValidDomain_ShouldMapCorrectly() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();

        Avis domain = Avis.builder()
                .id(1L)
                .description("Très bon jeu")
                .jeuId(2L)
                .joueurId(3L)
                .note(4.5F)
                .dateDEnvoi(now)
                .moderateurId(5L)
                .build();

        // Act
        AvisEntity result = AvisMapper.toEntity(domain);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Très bon jeu", result.getDescription());
        assertEquals(2L, result.getJeu().getId());
        assertEquals(3L, result.getJoueur().getId());
        assertEquals(4.5F, result.getNote());
        assertEquals(now, result.getDateDEnvoi());
        assertEquals(5L, result.getModerateur().getId());
    }
}