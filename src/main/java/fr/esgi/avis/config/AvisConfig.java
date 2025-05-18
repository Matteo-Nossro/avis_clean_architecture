package fr.esgi.avis.config;

import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.useCases.Avis.AvisUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvisConfig {

    @Bean
    public AvisUseCases avisUseCases(AvisDataSourcePort avisDataSourcePort) {
        return new AvisUseCases(avisDataSourcePort);
    }
}