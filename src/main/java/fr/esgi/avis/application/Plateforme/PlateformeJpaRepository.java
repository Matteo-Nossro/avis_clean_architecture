package fr.esgi.avis.application.Plateforme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlateformeJpaRepository extends JpaRepository<PlateformeEntity, Long> {
    Optional<PlateformeEntity> findByNom(String nom);
}