package fr.esgi.avis.application.Avatar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class AvatarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Long joueurId;

    public AvatarEntity(Long id, String nom, Long joueurId) {
        this.id = id;
        this.nom = nom;
        this.joueurId = joueurId;
    }
}