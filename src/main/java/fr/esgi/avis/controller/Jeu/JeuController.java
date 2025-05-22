package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Avis.AvisController;
import fr.esgi.avis.controller.Avis.AvisDtoMapper;
import fr.esgi.avis.controller.Avis.dto.AvisDTO;
import fr.esgi.avis.controller.Editeur.EditeurDtoMapper;
import fr.esgi.avis.controller.Genre.GenreDtoMapper;
import fr.esgi.avis.controller.Jeu.dto.CreateJeuDto;
import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import fr.esgi.avis.controller.Plateforme.PlateformeDtoMapper;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import fr.esgi.avis.useCases.Joueur.JoueurUseCases;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jeux")
public class JeuController {

    private final JeuUseCases jeuUseCases;
    private final EditeurUseCases editeurUseCases;
    private final GenreUseCases genreUseCases;
    private final PlateformeUseCases plateformeUseCases;
    private final AvisUseCases avisUseCases;
    private final AvisController avisController;
    private final JoueurUseCases joueurUseCases;

    public JeuController(
            JeuUseCases jeuUseCases,
            EditeurUseCases editeurUseCases,
            GenreUseCases genreUseCases,
            PlateformeUseCases plateformeUseCases,
            AvisUseCases avisUseCases,
            AvisController avisController,
            JoueurUseCases joueurUseCases
    ) {
        this.jeuUseCases = jeuUseCases;
        this.editeurUseCases = editeurUseCases;
        this.genreUseCases = genreUseCases;
        this.plateformeUseCases = plateformeUseCases;
        this.avisUseCases = avisUseCases;
        this.avisController = avisController;
        this.joueurUseCases = joueurUseCases;
    }

    @GetMapping
    public String afficherTousLesJeux(Model model) {
        List<JeuDto> jeux = getAll();
        model.addAttribute("jeux", jeux);
        return "jeux";
    }

    @GetMapping("/{id}")
    public String afficherDetailsJeu(@PathVariable Long id, Model model) {
        JeuDto jeu = getById(id);
        // on récupère la liste des avis du use case, on mappe en DTO
        List<AvisDTO> avis = avisUseCases
                .getAvisByJeuId(id)
                .stream()
                .map(AvisDtoMapper::toDto)
                .collect(Collectors.toList());
        model.addAttribute("jeu", jeu);
        model.addAttribute("avis", avis);  // ← ici
        return "jeu-avis";
    }

    @PostMapping("/{id}/avis")
    public String ajouterAvis(
            @PathVariable Long id,
            @RequestParam("note") Float note,
            @RequestParam("description") String description,
            Principal principal
    ) {
        // 1) récupérer le pseudo
        String pseudo = principal.getName();

        // 2) lookup du Joueur en base via le use case
        Long joueurId = joueurUseCases
                .getJoueurByPseudo(pseudo)
                .orElseThrow(() -> new UsernameNotFoundException("Joueur introuvable : " + pseudo))
                .getId();

        // 3) construire l’objet Avis domaine
        Avis nouveauAvis = Avis.builder()
                .jeuId(id)
                .joueurId(joueurId)
                .note(note)
                .description(description)
                .build();

        // 4) déléguer à votre composant AvisController
        avisController.createAvis(nouveauAvis);

        // 5) redirect pour éviter le repost
        return "redirect:/jeux/" + id;
    }

    @GetMapping("/ajouter")
    @PreAuthorize("hasRole('MODERATEUR')")
    public String afficherFormulaireAjoutJeu(Model model) {
        // Récupérer les données nécessaires pour le formulaire
        model.addAttribute("editeurs", editeurUseCases.getAllEditeurs().stream()
                .map(EditeurDtoMapper::toDto)
                .collect(Collectors.toList()));

        model.addAttribute("genres", genreUseCases.getAllGenres().stream()
                .map(GenreDtoMapper::toDto)
                .collect(Collectors.toList()));

        model.addAttribute("plateformes", plateformeUseCases.getAllPlateformes().stream()
                .map(PlateformeDtoMapper::toDto)
                .collect(Collectors.toList()));

        return "moderateur/ajout-jeu";
    }

    @PostMapping("/ajouter")
    @PreAuthorize("hasRole('MODERATEUR')")
    public String traiterAjoutJeu(@ModelAttribute CreateJeuDto createJeuDto) {
        JeuDto nouveauJeu = create(createJeuDto);
        return "redirect:/jeux/" + nouveauJeu.getId();
    }

    public JeuDto create(CreateJeuDto dto) {
        Editeur editeur = editeurUseCases.getEditeurById(dto.getEditeurId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Éditeur introuvable"));
        Genre genre = genreUseCases.getGenreById(dto.getGenreId())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Genre introuvable"));
        List<Plateforme> plateformes = dto.getPlateformeIds().stream()
                .map(id -> plateformeUseCases.getPlateformeById(id)
                        .orElseThrow(() ->
                                new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                        "Plateforme introuvable : " + id)))
                .collect(Collectors.toList());

        Jeu created = jeuUseCases.createJeu(
                dto.getNom(),
                editeur,
                genre,
                dto.getDescription(),
                dto.getDateDeSortie(),
                plateformes,
                dto.getImage(),
                dto.getPrix()
        );
        return JeuDtoMapper.toDto(created);
    }

    public List<JeuDto> getAll() {
        return jeuUseCases.getAllJeux()
                .stream()
                .map(JeuDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public JeuDto getById(Long id) {
        return jeuUseCases.getJeuById(id)
                .map(JeuDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Jeu introuvable"));
    }

    public void delete(Long id) {
        // si tu veux vérifier l'existence avant suppression, tu peux faire un findById
        jeuUseCases.deleteJeu(id);
    }
}