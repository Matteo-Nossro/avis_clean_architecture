package fr.esgi.avis.useCases.Joueur;

import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JoueurUseCases {

    private final JoueurDataSourcePort joueurDataSourcePort;

    public JoueurUseCases(JoueurDataSourcePort joueurDataSourcePort) {
        this.joueurDataSourcePort = joueurDataSourcePort;
    }

    public Joueur createJoueur(String pseudo, String motDePasse, String email, LocalDate dateDeNaissance, Long avatarId) {
        Joueur joueur = Joueur.builder()
                .pseudo(pseudo)
                .motDePasse(motDePasse)
                .email(email)
                .dateDeNaissance(dateDeNaissance)
                .avatarId(avatarId)
                .build();
        return joueurDataSourcePort.save(joueur);
    }

    public Optional<Joueur> getJoueurById(Long id) {
        return joueurDataSourcePort.findById(id);
    }

    public Optional<Joueur> getJoueurByPseudo(String pseudo) {
        return joueurDataSourcePort.findByPseudo(pseudo);
    }

    public List<Joueur> getAllJoueurs() {
        return joueurDataSourcePort.findAll();
    }

    public long countJoueurs() {
        return joueurDataSourcePort.count();
    }

    public void updateAvatar(Long joueurId, Long avatarId) {
        joueurDataSourcePort.updateAvatar(joueurId, avatarId);
    }
}