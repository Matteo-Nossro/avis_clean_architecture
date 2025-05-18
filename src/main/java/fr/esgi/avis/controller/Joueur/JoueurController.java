package fr.esgi.avis.controller.Joueur;

import fr.esgi.avis.controller.Joueur.dto.CreateJoueurDto;
import fr.esgi.avis.controller.Joueur.dto.JoueurDto;
import fr.esgi.avis.controller.Joueur.dto.UpdateAvatarDto;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.useCases.Joueur.JoueurUseCases;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JoueurController {

    private final JoueurUseCases joueurUseCases;

    public JoueurController(JoueurUseCases joueurUseCases) {
        this.joueurUseCases = joueurUseCases;
    }

    public JoueurDto create(CreateJoueurDto dto) {
        Joueur created = joueurUseCases.createJoueur(
                dto.getPseudo(),
                dto.getMotDePasse(),
                dto.getEmail(),
                dto.getDateDeNaissance(),
                dto.getAvatarId()
        );
        return JoueurDtoMapper.toDto(created);
    }

    public List<JoueurDto> getAll() {
        return joueurUseCases.getAllJoueurs()
                .stream()
                .map(JoueurDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public JoueurDto getById(Long id) {
        return joueurUseCases.getJoueurById(id)
                .map(JoueurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable"));
    }

    public JoueurDto getByPseudo(String pseudo) {
        return joueurUseCases.getJoueurByPseudo(pseudo)
                .map(JoueurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Joueur introuvable"));
    }

    public long count() {
        return joueurUseCases.countJoueurs();
    }

    public void updateAvatar(Long joueurId, UpdateAvatarDto dto) {
        try {
            joueurUseCases.updateAvatar(joueurId, dto.getAvatarId());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}