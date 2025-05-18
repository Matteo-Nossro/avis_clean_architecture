package fr.esgi.avis.controller.Editeur;

import fr.esgi.avis.controller.Editeur.dto.CreateEditeurDto;
import fr.esgi.avis.controller.Editeur.dto.EditeurDto;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EditeurController {

    private final EditeurUseCases editeurUseCases;

    public EditeurController(EditeurUseCases editeurUseCases) {
        this.editeurUseCases = editeurUseCases;
    }

    public EditeurDto create(CreateEditeurDto dto) {
        Editeur created = editeurUseCases.createEditeur(dto.getNom());
        return EditeurDtoMapper.toDto(created);
    }

    public List<EditeurDto> getAll() {
        return editeurUseCases.getAllEditeurs()
                .stream()
                .map(EditeurDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public EditeurDto getById(Long id) {
        return editeurUseCases.getEditeurById(id)
                .map(EditeurDtoMapper::toDto)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Éditeur introuvable"));
    }

    public void delete(Long id) {
        // optionnel : vérifier existence avant suppression
        editeurUseCases.deleteEditeur(id);
    }
}