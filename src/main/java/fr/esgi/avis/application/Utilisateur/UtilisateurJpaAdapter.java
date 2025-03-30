package fr.esgi.avis.application.Utilisateur;

import fr.esgi.avis.domain.Utilisateur.model.Utilisateur;
import fr.esgi.avis.domain.Utilisateur.UtilisateurDataSourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UtilisateurJpaAdapter implements UtilisateurDataSourcePort {

    private final UtilisateurJpaRepository utilisateurJpaRepository;

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        UtilisateurEntity utilisateurEntity = UtilisateurMapper.toEntity(utilisateur);
        return UtilisateurMapper.toDomain(utilisateurJpaRepository.save(utilisateurEntity));
    }

    @Override
    public Optional<Utilisateur> findByPseudo(String pseudo) {
        return Optional.ofNullable(UtilisateurMapper.toDomain(utilisateurJpaRepository.findByPseudo(pseudo)));
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        return Optional.ofNullable(UtilisateurMapper.toDomain(utilisateurJpaRepository.findByEmail(email)));
    }

    @Override
    public Optional<Utilisateur> findById(Long id) {
        return utilisateurJpaRepository.findById(id)
                .map(UtilisateurMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        utilisateurJpaRepository.deleteById(id);
    }
}