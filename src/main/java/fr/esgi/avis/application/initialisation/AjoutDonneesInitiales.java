package fr.esgi.avis.application.initialisation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.github.javafaker.Faker;

import fr.esgi.avis.domain.Avatar.AvatarDataSourcePort;
import fr.esgi.avis.domain.Avatar.model.Avatar;
import fr.esgi.avis.domain.Avis.AvisDataSourcePort;
import fr.esgi.avis.domain.Avis.model.Avis;
import fr.esgi.avis.domain.Classification.ClassificationDataSourcePort;
import fr.esgi.avis.domain.Classification.model.Classification;
import fr.esgi.avis.domain.Editeur.EditeurDataSourcePort;
import fr.esgi.avis.domain.Editeur.model.Editeur;
import fr.esgi.avis.domain.Genre.GenreDataSourcePort;
import fr.esgi.avis.domain.Genre.model.Genre;
import fr.esgi.avis.domain.Jeu.JeuDataSourcePort;
import fr.esgi.avis.domain.Jeu.model.Jeu;
import fr.esgi.avis.domain.Joueur.JoueurDataSourcePort;
import fr.esgi.avis.domain.Joueur.model.Joueur;
import fr.esgi.avis.domain.Moderateur.ModerateurDataSourcePort;
import fr.esgi.avis.domain.Moderateur.model.Moderateur;
import fr.esgi.avis.domain.Plateforme.PlateformeDataSourcePort;
import fr.esgi.avis.domain.Plateforme.model.Plateforme;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@Profile({"DEV", "PROD"})
public class AjoutDonneesInitiales {

    private static final String PS5    = "PlayStation 5";
    private static final String AUCUNE = "AUCUNE";

    private final EditeurDataSourcePort        editeurDataSourcePort;
    private final ClassificationDataSourcePort classificationDataSourcePort;
    private final GenreDataSourcePort          genreDataSourcePort;
    private final PlateformeDataSourcePort     plateformeDataSourcePort;
    private final JeuDataSourcePort            jeuDataSourcePort;
    private final JoueurDataSourcePort         joueurDataSourcePort;
    private final ModerateurDataSourcePort     moderateurDataSourcePort;
    private final AvisDataSourcePort           avisDataSourcePort;
    private final AvatarDataSourcePort         avatarDataSourcePort;

    @PersistenceContext
    private EntityManager entityManager;

    private static final Faker faker = new Faker(new Locale("fr"));

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        ajouterClassifications();
        ajouterGenres();
        ajouterPlateformes();
        ajouterEditeurs();
        ajouterAvatars();
        ajouterJoueurs(100);
        ajouterModerateurs();
        ajouterJeux();
        ajouterAvis(200);
        afficherStatistiques();
    }

    private void ajouterClassifications() {
        if (classificationDataSourcePort.count() == 0) {
            classificationDataSourcePort.save(
                    Classification.builder()
                            .nom("PEGI 3")
                            .couleurRGB("0000FF")
                            .build()
            );
            classificationDataSourcePort.save(
                    Classification.builder()
                            .nom("PEGI 7")
                            .couleurRGB("00FF00")
                            .build()
            );
            classificationDataSourcePort.save(
                    Classification.builder()
                            .nom("PEGI 12")
                            .couleurRGB("FF0000")
                            .build()
            );
            classificationDataSourcePort.save(
                    Classification.builder()
                            .nom("PEGI 16")
                            .couleurRGB("FFFF00")
                            .build()
            );
            classificationDataSourcePort.save(
                    Classification.builder()
                            .nom("PEGI 18")
                            .couleurRGB("00FFFF")
                            .build()
            );
            classificationDataSourcePort.save(
                    Classification.builder()
                            .nom(AUCUNE)
                            .couleurRGB("FFFFFF")
                            .build()
            );
        }
    }

    private void ajouterGenres() {
        if (genreDataSourcePort.count() == 0) {
            List<String> noms = List.of(
                    "Action", "Aventure", "RPG (Role-playing game)",
                    "Stratégie", "Sport", "Course",
                    "MOBA (Multiplayer online battle arena)",
                    "FPS (First person shooter)", "MMORPG", "Simulation"
            );
            for (String nom : noms) {
                genreDataSourcePort.save(
                        Genre.builder()
                                .nom(nom)
                                .build()
                );
            }
        }
    }

    private void ajouterPlateformes() {
        if (plateformeDataSourcePort.count() == 0) {
            List<String> platefs = List.of(
                    "Nintendo Switch", PS5, "Xbox Series X",
                    "PC", "Mobile", "Nintendo Wii",
                    "PlayStation 4", "Xbox One"
            );
            for (String nom : platefs) {
                plateformeDataSourcePort.save(
                        Plateforme.builder()
                                .nom(nom)
                                .build()
                );
            }
        }
    }

    private void ajouterEditeurs() {
        if (editeurDataSourcePort.count() == 0) {
            List<String> editeurs = List.of(
                    "Nintendo",
                    "Sony Interactive Entertainment",
                    "Microsoft",
                    "Ubisoft",
                    "Electronic Arts",
                    "Activision Blizzard",
                    "Riot Games",
                    "Ankama",
                    "BioWare",
                    "CD Projekt Red",
                    "FromSoftware",
                    "Naughty Dog",
                    "Hazelight Studios",
                    "idSoftware"
            );
            for (String nom : editeurs) {
                editeurDataSourcePort.save(
                        Editeur.builder()
                                .nom(nom)
                                .build()
                );
            }
        }
    }

    private void ajouterAvatars() {
        if (avatarDataSourcePort.count() == 0) {
            for (int i = 1; i <= 5; i++) {
                avatarDataSourcePort.save(new Avatar("Avatar " + i));
            }
        }
    }

    private void ajouterJoueurs(int nbJoueurs) {
        if (joueurDataSourcePort.count() == 0) {
            Random random = new Random();
            Calendar cal = Calendar.getInstance();

            for (int i = 0; i < nbJoueurs; i++) {
                cal.set(1940, Calendar.JANUARY, 1);
                Date debut = cal.getTime();
                cal.set(2003, Calendar.JANUARY, 1);
                Date fin = cal.getTime();
                Date d = faker.date().between(debut, fin);
                LocalDate bday = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                String prenom = faker.name().firstName();
                String email  = prenom.toLowerCase() + "." +
                        faker.name().lastName().toLowerCase().replaceAll("\\s+","") +
                        "@example.com";

                Joueur j = Joueur.builder()
                        .pseudo(prenom + (random.nextInt(900) + 100))
                        .motDePasse(Integer.toString(random.nextInt(90000000) + 10000000))
                        .email(email)
                        .dateDeNaissance(bday)
                        .build();

                joueurDataSourcePort.save(j);
            }
            // compte de test
            joueurDataSourcePort.save(Joueur.builder()
                    .pseudo("joueurDemo")
                    .motDePasse("motDePasse")
                    .email("joueurDemo@mail.fr")
                    .dateDeNaissance(LocalDate.of(2000, LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth()))
                    .build());
        }
    }

    private void ajouterModerateurs() {
        if (moderateurDataSourcePort.count() == 0) {
            moderateurDataSourcePort.save(new Moderateur("Peppe", "azerty", "peppe@spiagge.it", "+39123456789"));
            moderateurDataSourcePort.save(new Moderateur("Admin", "admin123", "admin@avis.fr", "+33123456789"));
            moderateurDataSourcePort.save(new Moderateur("Modo", "modo123", "modo@avis.fr", "+33234567890"));
        }
    }

    private void ajouterJeux() {
        if (jeuDataSourcePort.count() == 0) {
            // Elden Ring
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("Elden Ring")
                    .editeur(editeurDataSourcePort.findByNom("FromSoftware").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("RPG (Role-playing game)").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 16").orElseThrow())
                    .description("Un monde ouvert sombre créé en collaboration avec George R. R. Martin, où l’exploration et les combats exigeants sont au cœur de l’expérience.")
                    .dateDeSortie(LocalDate.of(2022, 2, 25))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("PlayStation 5").orElseThrow(),
                            plateformeDataSourcePort.findByNom("Xbox Series X").orElseThrow(),
                            plateformeDataSourcePort.findByNom("PC").orElseThrow()
                    ))
                    .image("doc/assets/eldenring.jpg")
                    .prix(69.99f)
                    .build());

            // FIFA 24
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("FIFA 24")
                    .editeur(editeurDataSourcePort.findByNom("Electronic Arts").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("Sport").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 3").orElseThrow())
                    .description("Le dernier opus de la célèbre franchise de simulation de football, avec des visuels améliorés et un gameplay toujours plus réaliste.")
                    .dateDeSortie(LocalDate.of(2023, 9, 29))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("PlayStation 5").orElseThrow(),
                            plateformeDataSourcePort.findByNom("Xbox Series X").orElseThrow(),
                            plateformeDataSourcePort.findByNom("PC").orElseThrow()
                    ))
                    .image("doc/assets/fifa24.jpg")
                    .prix(59.99f)
                    .build());

            // Cyberpunk 2077
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("Cyberpunk 2077")
                    .editeur(editeurDataSourcePort.findByNom("CD Projekt Red").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("RPG (Role-playing game)").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 18").orElseThrow())
                    .description("Un RPG futuriste en monde ouvert, plongée dans la mégalopole de Night City, avec des choix narratifs et un univers riche.")
                    .dateDeSortie(LocalDate.of(2020, 12, 10))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("PC").orElseThrow(),
                            plateformeDataSourcePort.findByNom("PlayStation 5").orElseThrow(),
                            plateformeDataSourcePort.findByNom("Xbox Series X").orElseThrow()
                    ))
                    .image("doc/assets/cyberpunk2077.jpg")
                    .prix(49.99f)
                    .build());

            // Halo Infinite
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("Halo Infinite")
                    .editeur(editeurDataSourcePort.findByNom("Microsoft").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("FPS (First person shooter)").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 16").orElseThrow())
                    .description("Le retour du légendaire Master Chief dans un mode campagne épique et un multijoueur gratuit soutenu par la communauté.")
                    .dateDeSortie(LocalDate.of(2021, 12, 8))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("Xbox Series X").orElseThrow(),
                            plateformeDataSourcePort.findByNom("PC").orElseThrow()
                    ))
                    .image("doc/assets/haloinfinite.jpg")
                    .prix(59.99f)
                    .build());

            // Animal Crossing: New Horizons
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("Animal Crossing: New Horizons")
                    .editeur(editeurDataSourcePort.findByNom("Nintendo").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("Simulation").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 3").orElseThrow())
                    .description("Construisez et personnalisez votre île paradisiaque, rencontrez des villageois charmants et vivez à votre rythme.")
                    .dateDeSortie(LocalDate.of(2020, 3, 20))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("Nintendo Switch").orElseThrow()
                    ))
                    .image("doc/assets/animalcrossing.jpg")
                    .prix(49.99f)
                    .build());

            // League of Legends
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("League of Legends")
                    .editeur(editeurDataSourcePort.findByNom("Riot Games").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("MOBA (Multiplayer online battle arena)").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 12").orElseThrow())
                    .description("Le MOBA incontournable, où deux équipes de puissants champions s’affrontent pour détruire le Nexus adverse.")
                    .dateDeSortie(LocalDate.of(2009, 10, 27))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("PC").orElseThrow()
                    ))
                    .image("doc/assets/lol.jpg")
                    .prix(0.00f)
                    .build());

            // Mass Effect Legendary Edition
            jeuDataSourcePort.save(Jeu.builder()
                    .nom("Mass Effect Legendary Edition")
                    .editeur(editeurDataSourcePort.findByNom("BioWare").orElseThrow())
                    .genre(genreDataSourcePort.findByNom("RPG (Role-playing game)").orElseThrow())
                    .classification(classificationDataSourcePort.findByNom("PEGI 16").orElseThrow())
                    .description("Remasterisation des trois premiers Mass Effect avec graphismes améliorés et gameplay retravaillé.")
                    .dateDeSortie(LocalDate.of(2021, 5, 14))
                    .plateformes(List.of(
                            plateformeDataSourcePort.findByNom("PlayStation 5").orElseThrow(),
                            plateformeDataSourcePort.findByNom("PC").orElseThrow(),
                            plateformeDataSourcePort.findByNom("Xbox Series X").orElseThrow()
                    ))
                    .image("doc/assets/masseffect.jpg")
                    .prix(39.99f)
                    .build());
        }
    }

    private void ajouterAvis(int nbAvis) {
        if (avisDataSourcePort.count() == 0) {
            Random random = new Random();
            List<Joueur> joueurs       = joueurDataSourcePort.findAll();
            List<Jeu>    jeux          = jeuDataSourcePort.findAll();
            List<Moderateur> modos     = moderateurDataSourcePort.findAll();

            for (int i = 0; i < nbAvis; i++) {
                Joueur j   = joueurs.get(random.nextInt(joueurs.size()));
                Jeu    jeu = jeux.get(random.nextInt(jeux.size()));
                Moderateur m = modos.get(random.nextInt(modos.size()));

                Avis avis = Avis.builder()
                        .description(faker.lorem().paragraph())
                        .jeuId(jeu.getId())
                        .joueurId(j.getId())
                        .note(random.nextFloat() * 20f)
                        .dateDEnvoi(LocalDateTime.now())
                        .moderateurId(m.getId())
                        .build();

                avisDataSourcePort.save(avis);
            }
        }
    }

    private void afficherStatistiques() {
        System.out.println("=== Statistiques initialisation ===");
        System.out.println("Éditeurs      : " + editeurDataSourcePort.count());
        System.out.println("Genres         : " + genreDataSourcePort.count());
        System.out.println("Plateformes    : " + plateformeDataSourcePort.count());
        System.out.println("Joueurs        : " + joueurDataSourcePort.count());
        System.out.println("Modérateurs    : " + moderateurDataSourcePort.count());
        System.out.println("Jeux           : " + jeuDataSourcePort.count());
        System.out.println("Avis           : " + avisDataSourcePort.count());
        System.out.println("Avatars        : " + avatarDataSourcePort.count());
    }
}