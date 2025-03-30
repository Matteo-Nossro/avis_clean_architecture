package fr.esgi.avis.application.Editeur;

import fr.esgi.avis.domain.Editeur.model.Editeur;

public class EditeurMapper {

    public static Editeur toDomain(EditeurEntity editeurEntity) {
        if (editeurEntity == null) {
            return null;
        }
        return Editeur.builder()
                .id(editeurEntity.getId())
                .nom(editeurEntity.getNom())
                .build();
    }

    public static EditeurEntity toEntity(Editeur editeur) {
        if (editeur == null) {
            return null;
        }
        EditeurEntity editeurEntity = new EditeurEntity();
        editeurEntity.setId(editeur.getId());
        editeurEntity.setNom(editeur.getNom());
        return editeurEntity;
    }
}