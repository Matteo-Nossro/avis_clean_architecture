package fr.esgi.avis.application.Editeur;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EditeurJpaRepository extends JpaRepository<EditeurEntity, Long> {
}