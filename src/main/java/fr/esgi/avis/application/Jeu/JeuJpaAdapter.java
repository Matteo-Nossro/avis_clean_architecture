package fr.esgi.avis.application.Jeu;

import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JeuJpaAdapter implements JeuDataSourcePort {

    private final JeuJpaRepository jeuJpaRepository;

    @Override
    public Jeu save(Jeu jeu) {
        JeuEntity jeuEntity = JeuMapper.toEntity(jeu);
        return JeuMapper.toDomain(jeuJpaRepository.save(jeuEntity));
    }

    @Override
    public Optional<Jeu> findById(Long id) {
        return jeuJpaRepository.findById(id)
                .map(JeuMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jeuJpaRepository.deleteById(id);
    }

    @Override
    public List<Jeu> findAll() {
        return jeuJpaRepository.findAll()
                .stream()
                .map(JeuMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return jeuJpaRepository.count();
    }
}