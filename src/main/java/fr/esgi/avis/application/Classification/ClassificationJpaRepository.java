package fr.esgi.avis.application.Classification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassificationJpaRepository extends JpaRepository<ClassificationEntity, Long> {
}