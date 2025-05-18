package fr.esgi.avis.controller.Moderateur;

import fr.esgi.avis.controller.Moderateur.dto.CreateModerateurDto;
import fr.esgi.avis.controller.Moderateur.dto.ModerateurDto;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.useCases.Moderateur.ModerateurUseCases;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ModerateurController {

    private final ModerateurUseCases moderateurUseCases;

    public ModerateurController(ModerateurUseCases moderateurUseCases) {
        this.moderateurUseCases = moderateurUseCases;
    }

    public ModerateurDto create(CreateModerateurDto dto) {
        Moderateur m = moderateurUseCases.createModerateur(
                dto.getPseudo(),
                dto.getMotDePasse(),
                dto.getEmail(),
                dto.getNumeroDeTelephone()
        );
        return ModerateurDtoMapper.toDto(m);
    }

    public List<ModerateurDto> getAll() {
        return moderateurUseCases.getAllModerateurs().stream()
                .map(ModerateurDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public ModerateurDto getById(Long id) {
        return moderateurUseCases.getModerateurById(id)
                .map(ModerateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Modérateur introuvable"));
    }

    public ModerateurDto getByPseudo(String pseudo) {
        return moderateurUseCases.getModerateurByPseudo(pseudo)
                .map(ModerateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Modérateur introuvable"));
    }

    public ModerateurDto getByEmail(String email) {
        return moderateurUseCases.getModerateurByEmail(email)
                .map(ModerateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Modérateur introuvable"));
    }

    public ModerateurDto getByNumero(String numero) {
        return moderateurUseCases.getModerateurByNumeroDeTelephone(numero)
                .map(ModerateurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Modérateur introuvable"));
    }

    public long count() {
        return moderateurUseCases.countModerateurs();
    }

    public void deleteById(Long id) {
        moderateurUseCases.deleteModerateurById(id);
    }

    public void deleteByPseudo(String pseudo) {
        moderateurUseCases.deleteModerateurByPseudo(pseudo);
    }
}