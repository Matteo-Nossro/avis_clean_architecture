package fr.esgi.avis.useCases.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Avis.AvisDataSourcePort;

import java.time.LocalDateTime;
import java.util.Optional;

public class AvisUseCases {

    private final AvisDataSourcePort avisDataSourcePort;

    public AvisUseCases(AvisDataSourcePort avisDataSourcePort) {
        this.avisDataSourcePort = avisDataSourcePort;
    }

    public Avis createAvis(String description, Long joueurId, Long jeuId, Float note) {
        Avis avis = new Avis(null, description, joueurId, jeuId, note, LocalDateTime.now());
        return avisDataSourcePort.save(avis);
    }

    public Optional<Avis> getAvisById(Long id) {
        return avisDataSourcePort.findById(id);
    }

    public void deleteAvis(Long id) {
        avisDataSourcePort.deleteById(id);
    }
}