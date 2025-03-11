package fr.esgi.avis.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public abstract class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;

    @NonNull
    @Column(length=80)
    @Size(max=80)
    protected String pseudo;

    @NonNull
    protected String motDePasse;

    @NonNull
    protected String email;
}
