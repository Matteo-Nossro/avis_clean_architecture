package fr.esgi.avis.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Data
public class ModerateurEntity extends UtilisateurEntity {

    private String numeroDeTelephone;

    public ModerateurEntity(String pseudo, String motDePasse, String mail, String numeroDeTelephone) {
        super(pseudo, motDePasse, mail);
        this.numeroDeTelephone = numeroDeTelephone;
    }
}
