package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.controller.Jeu.JeuDtoMapper;
import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JeuDtoMapperTest {

    @Test
    void toDto_WithNullJeu_ShouldReturnNull() {
        // Act
        JeuDto result = JeuDtoMapper.toDto(null);

        // Assert
        assertNull(result);
    }

    @Test
    void toDto_WithValidJeu_ShouldMapCorrectly() {
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
        JeuDto result = JeuDtoMapper.toDto(jeu);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Test Jeu", result.getNom());
        assertEquals("Editeur Test", result.getEditeur().getNom());
        assertEquals("Action", result.getGenre().getNom());
        assertEquals("Description test", result.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), result.getDateDeSortie());
        assertEquals(2, result.getPlateformeIds().size());
        assertEquals(1L, result.getPlateformeIds().get(0));
        assertEquals(2L, result.getPlateformeIds().get(1));
        assertEquals("image-url.jpg", result.getImage());
        assertEquals(59.99f, result.getPrix());
    }

    @Test
    void toDto_WithNullGenre_ShouldHandleCorrectly() {
        // Arrange
        Editeur editeur = Editeur.builder()
                .id(1L)
                .nom("Editeur Test")
                .build();

        Jeu jeu = Jeu.builder()
                .id(1L)
                .nom("Test Jeu")
                .editeur(editeur)
                .genre(null)
                .plateformes(List.of())
                .build();

        // Act
        JeuDto result = JeuDtoMapper.toDto(jeu);

        // Assert
        assertEquals(1L, result.getId());
        assertEquals("Test Jeu", result.getNom());
        assertEquals("Editeur Test", result.getEditeur().getNom());
        assertNull(result.getGenre());
    }
}