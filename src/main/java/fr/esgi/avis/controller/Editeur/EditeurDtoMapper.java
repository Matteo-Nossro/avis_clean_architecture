package fr.esgi.avis.controller.Editeur;

import fr.esgi.avis.controller.Editeur.dto.EditeurDto;
import fr.esgi.avis.domain.Editeur.model.Editeur;

public class EditeurDtoMapper {

    private EditeurDtoMapper() { }

    public static EditeurDto toDto(Editeur editeur) {
        if (editeur == null) {
            return null;
        }
        return EditeurDto.builder()
                .id(editeur.getId())
                .nom(editeur.getNom())
                .build();
    }
}