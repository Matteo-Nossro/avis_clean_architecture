package fr.esgi.avis.useCases.Jeu;

import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class JeuUseCases {

    private final JeuDataSourcePort jeuDataSourcePort;

    public JeuUseCases(JeuDataSourcePort jeuDataSourcePort) {
        this.jeuDataSourcePort = jeuDataSourcePort;
    }

    public Jeu createJeu(String nom,
                         Editeur editeur,
                         Genre genre,
                         String description,
                         LocalDate dateDeSortie,
                         List<Plateforme> plateformes,
                         String image,
                         float prix) {
        Jeu jeu = Jeu.builder()
                .nom(nom)
                .editeur(editeur)
                .genre(genre)
                .description(description)
                .dateDeSortie(dateDeSortie)
                .plateformes(plateformes)
                .image(image)
                .prix(prix)
                .build();
        return jeuDataSourcePort.save(jeu);
    }

    public Optional<Jeu> getJeuById(Long id) {
        return jeuDataSourcePort.findById(id);
    }

    public List<Jeu> getAllJeux() {
        return jeuDataSourcePort.findAll();
    }

    public void deleteJeu(Long id) {
        jeuDataSourcePort.deleteById(id);
    }
}