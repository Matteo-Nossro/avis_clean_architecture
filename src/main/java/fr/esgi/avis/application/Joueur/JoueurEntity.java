package fr.esgi.avis.application.Joueur;

import fr.esgi.avis.application.Avatar.AvatarEntity;
import fr.esgi.avis.application.Avis.AvisEntity;
import fr.esgi.avis.application.Utilisateur.UtilisateurEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@SuperBuilder
@NoArgsConstructor
@Data
public class JoueurEntity extends UtilisateurEntity {

    private LocalDate dateDeNaissance;

    @OneToMany(mappedBy = "joueur", fetch = FetchType.EAGER)
    private List<AvisEntity> avis = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    private AvatarEntity avatar;
}