package fr.esgi.avis.application.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
    Optional<GenreEntity> findByNom(String nom);
}