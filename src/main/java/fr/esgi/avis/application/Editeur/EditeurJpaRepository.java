package fr.esgi.avis.application.Editeur;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditeurJpaRepository extends JpaRepository<EditeurEntity, Long> {
    Optional<EditeurEntity> findByNom(String nom);
}