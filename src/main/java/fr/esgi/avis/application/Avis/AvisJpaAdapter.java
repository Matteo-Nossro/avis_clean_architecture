package fr.esgi.avis.application.Avis;

import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.domain.Avis.model.Avis;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AvisJpaAdapter implements AvisDataSourcePort {

    private final AvisJpaRepository avisJpaRepository;

    @Override
    public Avis save(Avis avis) {
        AvisEntity avisEntity = AvisMapper.toEntity(avis);
        return AvisMapper.toDomain(avisJpaRepository.save(avisEntity));
    }

    @Override
    public Optional<Avis> findById(Long id) {
        return avisJpaRepository.findById(id).map(AvisMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        avisJpaRepository.deleteById(id);
    }

    @Override
    public List<Avis> findByJeuId(Long jeuId) {
        return avisJpaRepository.findByJeuId(jeuId).stream()
                .map(AvisMapper::toDomain)
                .toList();
    }

    @Override
    public long count() {
        return avisJpaRepository.count();
    }
}