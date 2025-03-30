package fr.esgi.avis.application.Joueur;

import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface JoueurJpaRepository extends JpaRepository<JoueurEntity, Long> {
    JoueurEntity findByPseudo(String pseudo);
    Optional<JoueurEntity> findById(Long id);
    Optional<JoueurEntity> findByDateDeNaissance(LocalDate dateDeNaissance);
    void deleteByPseudo(String pseudo);
}