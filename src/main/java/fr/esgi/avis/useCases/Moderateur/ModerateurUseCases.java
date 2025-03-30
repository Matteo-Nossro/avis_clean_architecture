package fr.esgi.avis.useCases.Moderateur;

import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Moderateur.ModerateurDataSourcePort;

import java.util.List;
import java.util.Optional;

public class ModerateurUseCases {

    private final ModerateurDataSourcePort moderateurDataSourcePort;

    public ModerateurUseCases(ModerateurDataSourcePort moderateurDataSourcePort) {
        this.moderateurDataSourcePort = moderateurDataSourcePort;
    }

    public Moderateur createModerateur(String pseudo, String motDePasse, String email, String numeroDeTelephone) {
        Moderateur moderateur = Moderateur.builder()
                .pseudo(pseudo)
                .motDePasse(motDePasse)
                .email(email)
                .numeroDeTelephone(numeroDeTelephone)
                .build();
        return moderateurDataSourcePort.save(moderateur);
    }

    public Optional<Moderateur> getModerateurById(Long id) {
        return moderateurDataSourcePort.findById(id);
    }

    public Optional<Moderateur> getModerateurByPseudo(String pseudo) {
        return moderateurDataSourcePort.findByPseudo(pseudo);
    }

    public Optional<Moderateur> getModerateurByEmail(String email) {
        return moderateurDataSourcePort.findByEmail(email);
    }

    public Optional<Moderateur> getModerateurByNumeroDeTelephone(String numeroDeTelephone) {
        return moderateurDataSourcePort.findByNumeroDeTelephone(numeroDeTelephone);
    }

    public void deleteModerateurById(Long id) {
        moderateurDataSourcePort.deleteById(id);
    }

    public void deleteModerateurByPseudo(String pseudo) {
        moderateurDataSourcePort.deleteByPseudo(pseudo);
    }

    public long countModerateurs() {
        return moderateurDataSourcePort.count();
    }

    public List<Moderateur> getAllModerateurs() {
        return moderateurDataSourcePort.findAll();
    }
}