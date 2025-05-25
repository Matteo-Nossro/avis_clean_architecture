package fr.esgi.avis.useCases.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvisUseCasesTest {

    @Mock
    private AvisDataSourcePort avisDataSourcePort;

    private AvisUseCases avisUseCases;

    @BeforeEach
    void setUp() {
        avisUseCases = new AvisUseCases(avisDataSourcePort);
    }

    @Test
    void createAvis_ShouldCreateAndReturnAvis() {
        // Arrange
        String description = "Super jeu";
        Long jeuId = 1L;
        Long joueurId = 2L;
        Float note = 4.5f;
        Long moderateurId = 99L;

        Avis expected = Avis.builder()
                .id(1L)
                .description(description)
                .jeuId(jeuId)
                .joueurId(joueurId)
                .note(note)
                .moderateurId(moderateurId)
                .dateDEnvoi(LocalDateTime.now())
                .build();

        when(avisDataSourcePort.save(any(Avis.class))).thenReturn(expected);

        // Act
        Avis result = avisUseCases.createAvis(description, jeuId, joueurId, note, moderateurId);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void getAvisById_ShouldReturnAvis() {
        // Arrange
        Long avisId = 1L;
        Avis expected = Avis.builder()
                .id(avisId)
                .description("Super jeu")
                .jeuId(1L)
                .joueurId(2L)
                .note(4.5f)
                .moderateurId(1L)            // obligatoire
                .dateDEnvoi(LocalDateTime.now())
                .build();

        when(avisDataSourcePort.findById(avisId)).thenReturn(Optional.of(expected));

        // Act
        Optional<Avis> result = avisUseCases.getAvisById(avisId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    void getAvisByJeuId_ShouldReturnAvisForJeu() {
        // Arrange
        Long jeuId = 1L;
        Avis a1 = Avis.builder()
                .id(1L)
                .description("Avis 1")
                .jeuId(jeuId)
                .joueurId(10L)
                .note(4.5f)
                .moderateurId(1L)
                .dateDEnvoi(LocalDateTime.now())
                .build();
        Avis a2 = Avis.builder()
                .id(2L)
                .description("Avis 2")
                .jeuId(jeuId)
                .joueurId(11L)
                .note(3.0f)
                .moderateurId(1L)
                .dateDEnvoi(LocalDateTime.now())
                .build();
        List<Avis> expected = Arrays.asList(a1, a2);

        when(avisDataSourcePort.findByJeuId(jeuId)).thenReturn(expected);

        // Act
        List<Avis> result = avisUseCases.getAvisByJeuId(jeuId);

        // Assert
        assertEquals(expected, result);
    }

    @Test
    void deleteAvis_ShouldDeleteAvis() {
        // Arrange
        Long avisId = 1L;

        // Act
        avisUseCases.deleteAvis(avisId);

        // Assert
        verify(avisDataSourcePort).deleteById(avisId);
    }
}