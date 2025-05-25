package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.application.Editeur.EditeurEntity;
import fr.esgi.avis.application.Genre.GenreEntity;
import fr.esgi.avis.application.Plateforme.PlateformeEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JeuMapperTest {

    @Test
    void toDomain_WithNullEntity_ShouldReturnNull() {
        // Act
        Jeu result = JeuMapper.toDomain(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toDomain_WithValidEntity_ShouldMapCorrectly() {
        // Arrange
        EditeurEntity editeurEntity = new EditeurEntity();
        editeurEntity.setId(1L);
        editeurEntity.setNom("Editeur Test");

        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setId(1L);
        genreEntity.setNom("Action");

        PlateformeEntity plateformeEntity1 = new PlateformeEntity();
        plateformeEntity1.setId(1L);
        plateformeEntity1.setNom("PS5");

        PlateformeEntity plateformeEntity2 = new PlateformeEntity();
        plateformeEntity2.setId(2L);
        plateformeEntity2.setNom("PC");

        JeuEntity jeuEntity = new JeuEntity();
        jeuEntity.setId(1L);
        jeuEntity.setNom("Test Jeu");
        jeuEntity.setEditeur(editeurEntity);
        jeuEntity.setGenre(genreEntity);
        jeuEntity.setDescription("Description test");
        jeuEntity.setDateDeSortie(LocalDate.of(2023, 1, 1));
        jeuEntity.setPlateformes(Arrays.asList(plateformeEntity1, plateformeEntity2));
        jeuEntity.setImage("image-url.jpg");
        jeuEntity.setPrix(59.99f);

        // Act
        Jeu result = JeuMapper.toDomain(jeuEntity);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Test Jeu", result.getNom());
        assertEquals("Editeur Test", result.getEditeur().getNom());
        assertEquals("Action", result.getGenre().getNom());
        assertEquals("Description test", result.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), result.getDateDeSortie());
        assertEquals(2, result.getPlateformes().size());
        assertEquals("PS5", result.getPlateformes().get(0).getNom());
        assertEquals("PC", result.getPlateformes().get(1).getNom());
        assertEquals("image-url.jpg", result.getImage());
        assertEquals(59.99f, result.getPrix());
    }

    @Test
    void toEntity_WithNullDomain_ShouldReturnNull() {
        // Act
        JeuEntity result = JeuMapper.toEntity(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toEntity_WithValidDomain_ShouldMapCorrectly() {
        // Arrange
        Editeur editeur = Editeur.builder()
                .id(1L)
                .nom("Editeur Test")
                .build();

        Genre genre = Genre.builder()
                .id(1L)
                .nom("Action")
                .build();

        List<Plateforme> plateformes = Arrays.asList(
                Plateforme.builder().id(1L).nom("PS5").build(),
                Plateforme.builder().id(2L).nom("PC").build()
        );

        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Test Jeu")
                .editeur(editeur)
                .genre(genre)
                .description("Description test")
                .dateDeSortie(LocalDate.of(2023, 1, 1))
                .plateformes(plateformes)
                .image("image-url.jpg")
                .prix(59.99f)
                .build();

        // Act
        JeuEntity result = JeuMapper.toEntity(jeu);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Test Jeu", result.getNom());
        assertEquals("Editeur Test", result.getEditeur().getNom());
        assertEquals("Action", result.getGenre().getNom());
        assertEquals("Description test", result.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), result.getDateDeSortie());
        assertEquals(2, result.getPlateformes().size());
        assertEquals("PS5", result.getPlateformes().get(0).getNom());
        assertEquals("PC", result.getPlateformes().get(1).getNom());
        assertEquals("image-url.jpg", result.getImage());
        assertEquals(59.99f, result.getPrix());
    }

    @Test
    void toDomainShallow_WithNullEntity_ShouldReturnNull() {
        // Act
        Jeu result = JeuMapper.toDomainShallow(null);

        // Assert
        assertNull(result);
    }
}