package fr.esgi.avis.controller.Avis;

import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AvisController {
    private final AvisUseCases avisUseCases;

    public Avis createAvis(Avis avis) {
        return avisUseCases.createAvis(
                avis.getDescription(),
                avis.getJeuId(),
                avis.getJoueurId(),
                avis.getNote(),
                avis.getModerateurId()
        );
    }

    public Optional<Avis> getAvisById(Long id) {
        return avisUseCases.getAvisById(id);
    }

    public List<Avis> getAvisByJeuId(Long jeuId) {
        return avisUseCases.getAvisByJeuId(jeuId);
    }

    public void deleteAvis(Long id) {
        avisUseCases.deleteAvis(id);
    }
}