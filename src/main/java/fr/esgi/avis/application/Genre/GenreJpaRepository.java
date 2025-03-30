package fr.esgi.avis.application.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreJpaRepository extends JpaRepository<GenreEntity, Long> {
}