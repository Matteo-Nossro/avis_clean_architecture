package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Jeu.dto.CreateJeuDto;
import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import fr.esgi.avis.useCases.Joueur.JoueurUseCases;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JeuControllerTest {

    @Mock private JeuUseCases jeuUseCases;
    @Mock private EditeurUseCases editeurUseCases;
    @Mock private GenreUseCases genreUseCases;
    @Mock private PlateformeUseCases plateformeUseCases;
    @Mock private AvisUseCases avisUseCases;
    @Mock private AvisController avisController;
    @Mock private JoueurUseCases joueurUseCases;
    @Mock private Model model;
    @Mock private Principal principal;

    private JeuController jeuController;

    @BeforeEach
    void setUp() {
        JeuController real = new JeuController(
                jeuUseCases,
                editeurUseCases,
                genreUseCases,
                plateformeUseCases,
                avisUseCases,
                avisController,
                joueurUseCases
        );
        jeuController = spy(real);
    }

    @Test
    void afficherTousLesJeux_ShouldAddJeuxToModelAndReturnCorrectView() {
        List<Jeu> domainJeux = Arrays.asList(
                Jeu.builder()
                        .id(1L).nom("Jeu 1")
                        .editeur(Editeur.builder().id(10L).nom("Ed1").build())
                        .genre(Genre.builder().id(20L).nom("Gr1").build())
                        .plateformes(Arrays.asList(Plateforme.builder().id(100L).nom("P1").build()))
                        .dateDeSortie(LocalDate.now()).prix(49.99f)
                        .build()
        );
        when(jeuUseCases.getAllJeux()).thenReturn(domainJeux);

        String viewName = jeuController.afficherTousLesJeux(model);

        verify(model).addAttribute(eq("jeux"), any(List.class));
        assertEquals("jeux", viewName);
    }

    @Test
    void afficherDetailsJeu_WithValidId_ShouldAddJeuToModelAndReturnCorrectView() {
        Long jeuId = 1L;
        Jeu domainJeu = Jeu.builder()
                .id(jeuId).nom("Test Jeu")
                .editeur(Editeur.builder().id(5L).nom("EdTest").build())
                .genre(Genre.builder().id(7L).nom("GrTest").build())
                .plateformes(Arrays.asList(Plateforme.builder().id(50L).nom("PTest").build()))
                .dateDeSortie(LocalDate.of(2020,1,1)).prix(39.99f)
                .build();

        when(jeuUseCases.getJeuById(jeuId)).thenReturn(Optional.of(domainJeu));
        when(avisUseCases.getAvisByJeuId(jeuId)).thenReturn(Arrays.asList());

        String viewName = jeuController.afficherDetailsJeu(jeuId, model);

        verify(model).addAttribute(eq("jeu"), any(JeuDto.class));
        verify(model).addAttribute(eq("avis"), any(List.class));
        assertEquals("jeu-avis", viewName);
    }

    @Test
    void ajouterAvis_ShouldCreateAvisAndRedirect() {
        // Arrange
        Long jeuId = 1L;
        Float note = 4.5f;
        String description = "Très bon jeu!";
        String username = "user1";

        when(principal.getName()).thenReturn(username);
        Joueur joueur = Joueur.builder()
                .id(42L)
                .pseudo(username)
                .motDePasse("secret")
                .email("test@mail.fr")
                .build();
        when(joueurUseCases.getJoueurByPseudo(username)).thenReturn(Optional.of(joueur));

        // Act
        String viewName = jeuController.ajouterAvis(jeuId, note, description, principal);

        // Assert
        verify(avisController).createAvis(argThat((Avis a) ->
                a.getJeuId().equals(jeuId) &&
                        a.getJoueurId().equals(42L) &&
                        a.getNote().equals(note) &&
                        a.getDescription().equals(description)
        ));
        assertEquals("redirect:/jeux/" + jeuId, viewName);
    }

    @Test
    void afficherFormulaireAjoutJeu_ShouldAddAttributesToModelAndReturnCorrectView() {
        when(editeurUseCases.getAllEditeurs()).thenReturn(List.of());
        when(genreUseCases.getAllGenres()).thenReturn(List.of());
        when(plateformeUseCases.getAllPlateformes()).thenReturn(List.of());

        String viewName = jeuController.afficherFormulaireAjoutJeu(model);

        verify(model).addAttribute(eq("editeurs"), any(List.class));
        verify(model).addAttribute(eq("genres"), any(List.class));
        verify(model).addAttribute(eq("plateformes"), any(List.class));
        assertEquals("moderateur/ajout-jeu", viewName);
    }

    @Test
    void traiterAjoutJeu_ShouldCallCreateAndRedirectWithNewId() {
        CreateJeuDto dto = CreateJeuDto.builder()
                .nom("Nouveau Jeu")
                .editeurId(1L)
                .genreId(2L)
                .description("desc")
                .dateDeSortie(LocalDate.of(2025,5,1))
                .plateformeIds(List.of(3L,4L))
                .image("img.png")
                .prix(59.99f)
                .build();

        JeuDto fake = JeuDto.builder().id(99L).build();
        doReturn(fake).when(jeuController).create(dto);

        String viewName = jeuController.traiterAjoutJeu(dto);

        verify(jeuController).create(dto);
        assertEquals("redirect:/jeux/99", viewName);
    }
}