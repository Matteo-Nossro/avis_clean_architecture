package fr.esgi.avis.useCases.Plateforme;

import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;

import java.util.List;
import java.util.Optional;

public class PlateformeUseCases {

    private final PlateformeDataSourcePort plateformeDataSourcePort;

    public PlateformeUseCases(PlateformeDataSourcePort plateformeDataSourcePort) {
        this.plateformeDataSourcePort = plateformeDataSourcePort;
    }

    public Plateforme createPlateforme(String nom) {
        Plateforme plateforme = Plateforme.builder()
                .nom(nom)
                .build();
        return plateformeDataSourcePort.save(plateforme);
    }

    public Optional<Plateforme> getPlateformeById(Long id) {
        return plateformeDataSourcePort.findById(id);
    }

    public List<Plateforme> getAllPlateformes() {
        return plateformeDataSourcePort.findAll();
    }

    public void deletePlateforme(Long id) {
        plateformeDataSourcePort.deleteById(id);
    }
}