package fr.esgi.avis.controller.Jeu;

import fr.esgi.avis.controller.Jeu.dto.CreateJeuDto;
import fr.esgi.avis.controller.Jeu.dto.JeuDto;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jeux")
public class JeuController {

    private final JeuUseCases jeuUseCases;
    private final EditeurUseCases editeurUseCases;
    private final GenreUseCases genreUseCases;
    private final PlateformeUseCases plateformeUseCases;

    public JeuController(
            JeuUseCases jeuUseCases,
            EditeurUseCases editeurUseCases,
            GenreUseCases genreUseCases,
            PlateformeUseCases plateformeUseCases
    ) {
        this.jeuUseCases = jeuUseCases;
        this.editeurUseCases = editeurUseCases;
        this.genreUseCases = genreUseCases;
        this.plateformeUseCases = plateformeUseCases;
    }

    @GetMapping
    public String afficherTousLesJeux(Model model) {
        List<JeuDto> jeux = getAll();
        model.addAttribute("jeux", jeux);
        return "jeux"; // Va utiliser le template jeux.html
    }

    @GetMapping("/{id}")
    public String afficherDetailsJeu(@PathVariable Long id, Model model) {
        JeuDto jeu = getById(id);
        model.addAttribute("jeu", jeu);
        return "jeu-avis"; // Va utiliser le template jeu-avis.html
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