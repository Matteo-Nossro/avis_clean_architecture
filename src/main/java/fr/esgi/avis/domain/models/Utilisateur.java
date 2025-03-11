package fr.esgi.avis.domain.models;

public abstract class Utilisateur {
    protected final Long id;
    protected final String pseudo;
    protected final String motDePasse;
    protected final String email;


    protected Utilisateur(Long id, String pseudo, String motDePasse, String email) {
        this.id = id;
        this.pseudo = pseudo;
        this.motDePasse = motDePasse;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getEmail() {
        return email;
    }
}
