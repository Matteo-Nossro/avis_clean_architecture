package fr.esgi.avis.useCases.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.domain.Utilisateur.UtilisateurDataSourcePort;

import java.util.Optional;

public class UtilisateurUseCases {

    private final UtilisateurDataSourcePort utilisateurDataSourcePort;

    public UtilisateurUseCases(UtilisateurDataSourcePort utilisateurDataSourcePort) {
        this.utilisateurDataSourcePort = utilisateurDataSourcePort;
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurDataSourcePort.save(utilisateur);
    }

    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurDataSourcePort.findById(id);
    }

    public Optional<Utilisateur> getUtilisateurByPseudo(String pseudo) {
        return utilisateurDataSourcePort.findByPseudo(pseudo);
    }

    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurDataSourcePort.findByEmail(email);
    }
}