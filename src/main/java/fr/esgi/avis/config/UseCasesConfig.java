package fr.esgi.avis.config;

import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Moderateur.ModerateurDataSourcePort;
import fr.esgi.avis.domain.Utilisateur.UtilisateurDataSourcePort;
import fr.esgi.avis.useCases.Joueur.JoueurUseCases;
import fr.esgi.avis.useCases.Moderateur.ModerateurUseCases;
import fr.esgi.avis.useCases.Utilisateur.UtilisateurUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;
import fr.esgi.avis.domain.Genre.GenreDataSourcePort;
import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;

import fr.esgi.avis.useCases.Jeu.JeuUseCases;
import fr.esgi.avis.useCases.Editeur.EditeurUseCases;
import fr.esgi.avis.useCases.Genre.GenreUseCases;
import fr.esgi.avis.useCases.Plateforme.PlateformeUseCases;

@Configuration
public class UseCasesConfig {

    @Bean
    public JeuUseCases jeuUseCases(JeuDataSourcePort jeuDataSourcePort) {
        return new JeuUseCases(jeuDataSourcePort);
    }

    @Bean
    public EditeurUseCases editeurUseCases(EditeurDataSourcePort editeurDataSourcePort) {
        return new EditeurUseCases(editeurDataSourcePort);
    }

    @Bean
    public GenreUseCases genreUseCases(GenreDataSourcePort genreDataSourcePort) {
        return new GenreUseCases(genreDataSourcePort);
    }

    @Bean
    public PlateformeUseCases plateformeUseCases(PlateformeDataSourcePort plateformeDataSourcePort) {
        return new PlateformeUseCases(plateformeDataSourcePort);
    }

    @Bean
    public JoueurUseCases joueurUseCases(JoueurDataSourcePort joueurDataSourcePort) {
        return new JoueurUseCases(joueurDataSourcePort);
    }

    @Bean
    public ModerateurUseCases moderateurUseCases(ModerateurDataSourcePort moderateurDataSourcePort) {
        return new ModerateurUseCases(moderateurDataSourcePort);
    }

    @Bean
    public UtilisateurUseCases utilisateurUseCases(UtilisateurDataSourcePort utilisateurDataSourcePort) {
        return new UtilisateurUseCases(utilisateurDataSourcePort);
    }
}