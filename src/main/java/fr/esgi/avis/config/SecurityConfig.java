package fr.esgi.avis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // ressources statiques
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // pages modérateur
                        .requestMatchers("/moderateur/**").hasRole("MODERATEUR")
                        // tout le reste → authentifié
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")              // your custom login page
                        .loginProcessingUrl("/login")     // traitement du POST
                        .defaultSuccessUrl("/jeux", true) // où rediriger après log in
                        .failureUrl("/login?error")       // en cas d’erreur
                        .permitAll()                      // <-- clé pour éviter la boucle
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()                      // déconnexion autorisée
                )
        ;
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        var user = User.withUsername("user")
                .password("{noop}password")
                .roles("JOUEUR")
                .build();
        var mod = User.withUsername("mod")
                .password("{noop}modpass")
                .roles("MODERATEUR")
                .build();
        return new InMemoryUserDetailsManager(user, mod);
    }

    // Si vous configurez manuellement Thymeleaf :
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver resolver) {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(resolver);
        engine.addDialect(new SpringSecurityDialect());
        return engine;
    }
}