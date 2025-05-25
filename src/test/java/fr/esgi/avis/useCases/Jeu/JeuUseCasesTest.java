package fr.esgi.avis.useCases.Jeu;

import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JeuUseCasesTest {

    @Mock
    private JeuDataSourcePort jeuDataSourcePort;

    private JeuUseCases jeuUseCases;

    // Utilitaires pour builder un Jeu minimal valide
    private Editeur dummyEditeur() {
        return Editeur.builder().id(0L).nom("Dummy Editeur").build();
    }
    private Genre dummyGenre() {
        return Genre.builder().id(0L).nom("Dummy Genre").build();
    }
    private List<Plateforme> dummyPlateformes() {
        return Arrays.asList(
                Plateforme.builder().id(0L).nom("DummyPlat1").build()
        );
    }

    @BeforeEach
    void setUp() {
        jeuUseCases = new JeuUseCases(jeuDataSourcePort);
    }

    @Test
    void createJeu_ShouldCreateAndReturnJeu() {
        // Arrange
        String nom = "Test Jeu";
        Editeur editeur = dummyEditeur();
        Genre genre = dummyGenre();
        String description = "Description test";
        LocalDate dateDeSortie = LocalDate.now();
        List<Plateforme> plateformes = Arrays.asList(
                Plateforme.builder().id(1L).nom("PS5").build(),
                Plateforme.builder().id(2L).nom("PC").build()
        );
        String image = "image-url.jpg";
        float prix = 59.99f;

        Jeu expected = Jeu.builder()
                .id(1L)
                .nom(nom)
                .editeur(editeur)
                .genre(genre)
                .description(description)
                .dateDeSortie(dateDeSortie)
                .plateformes(plateformes)
                .image(image)
                .prix(prix)
                .build();

        when(jeuDataSourcePort.save(any(Jeu.class))).thenReturn(expected);

        // Act
        Jeu result = jeuUseCases.createJeu(nom, editeur, genre, description, dateDeSortie, plateformes, image, prix);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getJeuById_ShouldReturnJeu() {
        // Arrange
        Long jeuId = 1L;
        Jeu expected = Jeu.builder()
                .id(jeuId)
                .nom("Test Jeu")
                .editeur(dummyEditeur())
                .genre(dummyGenre())
                .description("desc")
                .dateDeSortie(LocalDate.of(2020,1,1))
                .plateformes(dummyPlateformes())
                .image("img.png")
                .prix(0f)
                .build();

        when(jeuDataSourcePort.findById(jeuId)).thenReturn(Optional.of(expected));

        // Act
        Optional<Jeu> result = jeuUseCases.getJeuById(jeuId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void getAllJeux_ShouldReturnAllJeux() {
        // Arrange
        List<Jeu> expected = Arrays.asList(
                Jeu.builder()
                        .id(1L).nom("Jeu 1")
                        .editeur(dummyEditeur())
                        .genre(dummyGenre())
                        .description("d1")
                        .dateDeSortie(LocalDate.now())
                        .plateformes(dummyPlateformes())
                        .image("i1")
                        .prix(10f)
                        .build(),
                Jeu.builder()
                        .id(2L).nom("Jeu 2")
                        .editeur(dummyEditeur())
                        .genre(dummyGenre())
                        .description("d2")
                        .dateDeSortie(LocalDate.now())
                        .plateformes(dummyPlateformes())
                        .image("i2")
                        .prix(20f)
                        .build()
        );

        when(jeuDataSourcePort.findAll()).thenReturn(expected);

        // Act
        List<Jeu> result = jeuUseCases.getAllJeux();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void deleteJeu_ShouldDeleteJeu() {
        // Arrange
        Long jeuId = 1L;

        // Act
        jeuUseCases.deleteJeu(jeuId);

        // Assert
        verify(jeuDataSourcePort).deleteById(jeuId);
    }
}