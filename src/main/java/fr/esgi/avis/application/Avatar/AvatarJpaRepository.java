package fr.esgi.avis.application.Avatar;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvatarJpaRepository extends JpaRepository<AvatarEntity, Long> {
}