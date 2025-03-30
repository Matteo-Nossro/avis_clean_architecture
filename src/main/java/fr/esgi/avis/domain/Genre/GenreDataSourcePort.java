package fr.esgi.avis.domain.Genre;

import fr.esgi.avis.domain.Genre.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDataSourcePort {
    Genre save(Genre genre);
    Optional<Genre> findById(Long id);
    void deleteById(Long id);
    List<Genre> findAll();
}

// Ce port respecte la Clean Architecture en isolant le domaine de Genre des détails techniques de persistance.
// Il permet de définir contractuellement les opérations de stockage sans imposer d'implémentation concrète.
