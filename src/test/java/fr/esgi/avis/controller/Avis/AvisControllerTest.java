package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AvisControllerTest {

    @Mock
    private AvisUseCases avisUseCases;

    private AvisController avisController;

    @BeforeEach
    void setUp() {
        avisController = new AvisController(avisUseCases);
    }

    @Test
    void createAvis_ShouldDelegateToUseCases() {
        // Arrange
        Avis avis = Avis.builder()
                .description("Super jeu")
                .jeuId(1L)
                .joueurId(2L)
                .note(4.5f)
                .moderateurId(10L)
                .build();

        Avis expected = Avis.builder()
                .id(1L)
                .description("Super jeu")
                .jeuId(1L)
                .joueurId(2L)
                .note(4.5f)
                .moderateurId(10L)
                .build();

        when(avisUseCases.createAvis(
                eq("Super jeu"),
                eq(1L),
                eq(2L),
                eq(4.5f),
                eq(10L)
        )).thenReturn(expected);

        // Act
        Avis result = avisController.createAvis(avis);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getAvisById_ShouldReturnAvisFromUseCases() {
        // Arrange
        Long avisId = 1L;
        Avis expected = Avis.builder()
                .id(avisId)
                .description("Super jeu")
                .jeuId(1L)
                .joueurId(2L)
                .moderateurId(10L)
                .note(4.5f)
                .build();

        when(avisUseCases.getAvisById(avisId)).thenReturn(Optional.of(expected));

        // Act
        Optional<Avis> result = avisController.getAvisById(avisId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void getAvisByJeuId_ShouldReturnListFromUseCases() {
        // Arrange
        Long jeuId = 1L;
        List<Avis> expected = Arrays.asList(
                Avis.builder().id(1L).jeuId(jeuId).joueurId(5L).moderateurId(10L).description("description").note(4.5f).build(),
                Avis.builder().id(2L).jeuId(jeuId).joueurId(6L).moderateurId(11L).description("description").note(3.0f).build()
        );

        when(avisUseCases.getAvisByJeuId(jeuId)).thenReturn(expected);

        // Act
        List<Avis> result = avisController.getAvisByJeuId(jeuId);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void deleteAvis_ShouldDelegateToUseCases() {
        // Arrange
        Long avisId = 1L;

        // Act
        avisController.deleteAvis(avisId);

        // Assert
        verify(avisUseCases).deleteAvis(avisId);
    }
}