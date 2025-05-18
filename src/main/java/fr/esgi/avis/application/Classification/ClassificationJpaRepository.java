package fr.esgi.avis.application.Classification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClassificationJpaRepository extends JpaRepository<ClassificationEntity, Long> {
    Optional<ClassificationEntity> findByNom(String nom);
}