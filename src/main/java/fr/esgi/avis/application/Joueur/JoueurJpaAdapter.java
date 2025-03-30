package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.application.Avatar.AvatarJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JoueurJpaAdapter implements JoueurDataSourcePort {

    private final JoueurJpaRepository joueurJpaRepository;
    private final AvatarJpaRepository avatarJpaRepository;

    @Override
    public Joueur save(Joueur joueur) {
        JoueurEntity joueurEntity = JoueurMapper.toEntity(joueur);
        return JoueurMapper.toDomain(joueurJpaRepository.save(joueurEntity));
    }

    @Override
    public List<Joueur> findAll() {
        return joueurJpaRepository.findAll()
                .stream()
                .map(JoueurMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Joueur> findByPseudo(String pseudo) {
        return Optional.ofNullable(JoueurMapper.toDomain(joueurJpaRepository.findByPseudo(pseudo)));
    }

    @Override
    public Optional<Joueur> findById(Long id) {
        return joueurJpaRepository.findById(id)
                .map(JoueurMapper::toDomain);
    }

    @Override
    public Optional<Joueur> findByDateDeNaissance(LocalDate dateDeNaissance) {
        return joueurJpaRepository.findByDateDeNaissance(dateDeNaissance)
                .map(JoueurMapper::toDomain);
    }

    public void deleteByPseudo(String pseudo) {
        joueurJpaRepository.deleteByPseudo(pseudo);
    }

    @Override
    public long count() {
        return joueurJpaRepository.count();
    }

    @Override
    public void updateAvatar(Long joueurId, Long avatarId) {
        Optional<JoueurEntity> joueurEntity = joueurJpaRepository.findById(joueurId);
        Optional<fr.esgi.avis.application.Avatar.AvatarEntity> avatarEntity = avatarJpaRepository.findById(avatarId);

        if (joueurEntity.isEmpty() || avatarEntity.isEmpty()){
            throw new IllegalArgumentException("Joueur ou Avatar introuvable.");
        }

        JoueurEntity joueurEntityToUpdate = joueurEntity.get();
        fr.esgi.avis.application.Avatar.AvatarEntity avatarEntityToUpdate = avatarEntity.get();

        joueurEntityToUpdate.setAvatar(avatarEntityToUpdate);
        joueurJpaRepository.save(joueurEntityToUpdate);
    }
}