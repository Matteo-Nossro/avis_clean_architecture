package fr.esgi.avis.application.Moderateur;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ModerateurJpaRepository extends JpaRepository<ModerateurEntity, Long> {
    Optional<ModerateurEntity> findByPseudo(String pseudo);
    Optional<ModerateurEntity> findByEmail(String email);
    Optional<ModerateurEntity> findByNumeroDeTelephone(String numeroDeTelephone);
    void deleteByPseudo(String pseudo);
}