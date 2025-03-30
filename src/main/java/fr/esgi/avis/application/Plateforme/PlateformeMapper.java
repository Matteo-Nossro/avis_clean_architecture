package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.domain.Plateforme.model.Plateforme;

public class PlateformeMapper {

    public static Plateforme toDomain(PlateformeEntity plateformeEntity) {
        if (plateformeEntity == null) {
            return null;
        }
        return Plateforme.builder()
                .id(plateformeEntity.getId())
                .nom(plateformeEntity.getNom())
                .build();
    }

    public static PlateformeEntity toEntity(Plateforme plateforme) {
        if (plateforme == null) {
            return null;
        }
        PlateformeEntity plateformeEntity = new PlateformeEntity();
        plateformeEntity.setId(plateforme.getId());
        plateformeEntity.setNom(plateforme.getNom());
        return plateformeEntity;
    }
}