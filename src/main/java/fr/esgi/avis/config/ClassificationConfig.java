package fr.esgi.avis.config;

import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.useCases.Classification.ClassificationUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassificationConfig {

    @Bean
    public ClassificationUseCases classificationUseCases(ClassificationDataSourcePort port) {
        return new ClassificationUseCases(port);
    }
}
