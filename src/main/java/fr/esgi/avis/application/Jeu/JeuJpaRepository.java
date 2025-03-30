package fr.esgi.avis.application.Jeu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JeuJpaRepository extends JpaRepository<JeuEntity, Long> {
}