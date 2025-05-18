package fr.esgi.avis.controller.Utilisateur;

import fr.esgi.avis.controller.Utilisateur.dto.CreateUtilisateurDto;
import fr.esgi.avis.controller.Utilisateur.dto.UtilisateurDto;
import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.useCases.Utilisateur.UtilisateurUseCases;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class UtilisateurController {

    private final UtilisateurUseCases utilisateurUseCases;

    public UtilisateurController(UtilisateurUseCases utilisateurUseCases) {
        this.utilisateurUseCases = utilisateurUseCases;
    }

    public UtilisateurDto create(CreateUtilisateurDto dto) {
        Utilisateur toCreate;
        if ("joueur".equalsIgnoreCase(dto.getType())) {
            toCreate = fr.esgi.avis.domain.Joueur.model.Joueur.builder()
                    .pseudo(dto.getPseudo())
                    .motDePasse(dto.getMotDePasse())
                    .email(dto.getEmail())
                    .dateDeNaissance(LocalDate.parse(dto.getDateDeNaissance()))
                    .avatarId(dto.getAvatarId())
                    .build();
        } else if ("moderateur".equalsIgnoreCase(dto.getType())) {
            toCreate = fr.esgi.avis.domain.Moderateur.model.Moderateur.builder()
                    .pseudo(dto.getPseudo())
                    .motDePasse(dto.getMotDePasse())
                    .email(dto.getEmail())
                    .numeroDeTelephone(dto.getNumeroDeTelephone())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Type utilisateur invalide");
        }
        Utilisateur created = utilisateurUseCases.createUtilisateur(toCreate);
        return UtilisateurDtoMapper.toDto(created);
    }

    public UtilisateurDto getById(Long id) {
        return utilisateurUseCases.getUtilisateurById(id)
                .map(UtilisateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    }

    public UtilisateurDto getByPseudo(String pseudo) {
        return utilisateurUseCases.getUtilisateurByPseudo(pseudo)
                .map(UtilisateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    }

    public UtilisateurDto getByEmail(String email) {
        return utilisateurUseCases.getUtilisateurByEmail(email)
                .map(UtilisateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur introuvable"));
    }
}