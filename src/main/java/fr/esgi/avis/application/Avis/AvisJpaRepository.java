package fr.esgi.avis.application.Avis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvisJpaRepository extends JpaRepository<AvisEntity, Long> {
    @Query("SELECT a FROM AvisEntity a WHERE a.jeu.id = :jeuId")
    List<AvisEntity> findByJeuId(@Param("jeuId") Long jeuId);
}