package fr.esgi.avis.application.Plateforme;

import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PlateformeJpaAdapter implements PlateformeDataSourcePort {

    private final PlateformeJpaRepository plateformeJpaRepository;

    @Override
    public Plateforme save(Plateforme plateforme) {
        PlateformeEntity plateformeEntity = PlateformeMapper.toEntity(plateforme);
        return PlateformeMapper.toDomain(plateformeJpaRepository.save(plateformeEntity));
    }

    @Override
    public Optional<Plateforme> findById(Long id) {
        return plateformeJpaRepository.findById(id)
                .map(PlateformeMapper::toDomain);
    }

    @Override
    public Optional<Plateforme> findByNom(String nom) {
        return plateformeJpaRepository.findByNom(nom)
                .map(PlateformeMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        plateformeJpaRepository.deleteById(id);
    }

    @Override
    public List<Plateforme> findAll() {
        return plateformeJpaRepository.findAll().stream()
                .map(PlateformeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return plateformeJpaRepository.count();
    }
}