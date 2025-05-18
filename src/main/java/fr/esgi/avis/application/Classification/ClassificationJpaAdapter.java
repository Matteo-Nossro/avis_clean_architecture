package fr.esgi.avis.application.Classification;

import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.domain.Classification.model.Classification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ClassificationJpaAdapter implements ClassificationDataSourcePort {

    private final ClassificationJpaRepository classificationJpaRepository;

    @Override
    public Classification save(Classification classification) {
        ClassificationEntity classificationEntity = ClassificationMapper.toEntity(classification);
        return ClassificationMapper.toDomain(classificationJpaRepository.save(classificationEntity));
    }

    @Override
    public Optional<Classification> findById(Long id) {
        return classificationJpaRepository.findById(id)
                .map(ClassificationMapper::toDomain);
    }

    @Override
    public Optional<Classification> findByNom(String nom) {
        return classificationJpaRepository.findByNom(nom)
                .map(ClassificationMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        classificationJpaRepository.deleteById(id);
    }

    @Override
    public List<Classification> findAll() {
        return classificationJpaRepository.findAll().stream()
                .map(ClassificationMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return classificationJpaRepository.count();
    }
}