package fr.esgi.avis.config;

import fr.esgi.avis.controller.Avatar.AvatarController;
import fr.esgi.avis.useCases.Avatar.AvatarUseCases;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AvatarConfig {

    /**
     * Expose AvatarUseCases (car annoté @Service) et AvatarController
     * comme beans Spring pour pouvoir les injecter dans les controllers REST.
     */
    @Bean
    public AvatarUseCases avatarUseCases(fr.esgi.avis.domain.Avatar.AvatarDataSourcePort avatarDataSourcePort) {
        return new AvatarUseCases(avatarDataSourcePort);
    }

    @Bean
    public AvatarController avatarController(AvatarUseCases avatarUseCases) {
        return new AvatarController(avatarUseCases);
    }
}