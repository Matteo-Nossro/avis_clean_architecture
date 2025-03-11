package fr.esgi.avis.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper=false)
@Data
public class JoueurEntity extends UtilisateurEntity {

    private LocalDate dateDeNaissance;

    // Hypothèse de Nicolas : il ne sait pas comment initialiser la liste d'avis
    @Builder.Default
    @OneToMany(mappedBy = "joueur", fetch = FetchType.EAGER)
    private List<AvisEntity> avis = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private AvatarEntity avatar;
}
