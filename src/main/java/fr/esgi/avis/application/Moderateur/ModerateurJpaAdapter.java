package fr.esgi.avis.application.Moderateur;

import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Moderateur.ModerateurDataSourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ModerateurJpaAdapter implements ModerateurDataSourcePort {

    private final ModerateurJpaRepository moderateurJpaRepository;

    @Override
    public Moderateur save(Moderateur moderateur) {
        ModerateurEntity moderateurEntity = ModerateurMapper.toEntity(moderateur);
        return ModerateurMapper.toDomain(moderateurJpaRepository.save(moderateurEntity));
    }

    @Override
    public Optional<Moderateur> findById(Long id) {
        return moderateurJpaRepository.findById(id)
                .map(ModerateurMapper::toDomain);
    }

    @Override
    public Optional<Moderateur> findByPseudo(String pseudo) {
        return moderateurJpaRepository.findByPseudo(pseudo)
                .map(ModerateurMapper::toDomain);
    }

    @Override
    public Optional<Moderateur> findByEmail(String email) {
        return moderateurJpaRepository.findByEmail(email)
                .map(ModerateurMapper::toDomain);
    }

    @Override
    public Optional<Moderateur> findByNumeroDeTelephone(String numeroDeTelephone) {
        return moderateurJpaRepository.findByNumeroDeTelephone(numeroDeTelephone)
                .map(ModerateurMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        moderateurJpaRepository.deleteById(id);
    }

    @Override
    public void deleteByPseudo(String pseudo) {
        moderateurJpaRepository.deleteByPseudo(pseudo);
    }

    @Override
    public long count() {
        return moderateurJpaRepository.count();
    }

    @Override
    public List<Moderateur> findAll() {
        return moderateurJpaRepository.findAll()
                .stream()
                .map(ModerateurMapper::toDomain)
                .collect(Collectors.toList());
    }
}