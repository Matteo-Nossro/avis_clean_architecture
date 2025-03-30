package fr.esgi.avis.application.Avis;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisJpaRepository extends JpaRepository<AvisEntity, Long> {
}