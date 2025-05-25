# Compte Rendu du Projet "Avis"

## 🏗️ Architecture du projet

Ce projet a été conçu en suivant une architecture visant la modularité et la séparation des préoccupations, s'inspirant des principes de la Clean Architecture. L'objectif est d'obtenir un code maintenable, testable et évolutif.

### 📂 Structure des répertoires principaux

Le projet est organisé en plusieurs modules principaux, chacun ayant une responsabilité claire :

*   `src/main/java/fr/esgi/avis/`
    *   `📂 domain`: Contient les entités métiers principales de l'application (ex: `Avis`, `Jeu`, `Joueur`). C'est le cœur de l'application, indépendant des frameworks et des détails d'implémentation.
        *   `📂 Avis`
            *   `📂 model`
                *   `Avis.java` (Modèle du domaine pour un avis)
            *   `AvisDataSourcePort.java` (Interface définissant comment les données des avis sont persistées ou récupérées)
        *   `📂 Jeu`
            *   `📂 model`
                *   `Jeu.java`
            *   `JeuDataSourcePort.java`
        *   `(Autres entités du domaine comme Joueur, Editeur, Genre, etc.)`
    *   `📂 application`: Sert de pont entre le domaine et les couches externes. Il contient les implémentations concrètes des ports définis dans le domaine (par exemple, les adaptateurs pour la base de données).
        *   `📂 Avis`
            *   `AvisJpaAdapter.java` (Implémentation de `AvisDataSourcePort` utilisant Spring Data JPA)
            *   `AvisJpaRepository.java` (Interface Spring Data JPA pour l'entité Avis)
            *   `AvisMapper.java` (Pour mapper entre les entités du domaine et les entités JPA)
        *   `📂 Jeu`
            *   `JeuJpaAdapter.java`
            *   `JeuJpaRepository.java`
            *   `JeuMapper.java`
        *   `(Autres adaptateurs et mappers pour les entités correspondantes)`
    *   `📂 useCases`: Contient la logique métier et les cas d'utilisation de l'application. Ces classes orchestrent les interactions entre les objets du domaine et utilisent les ports pour accéder aux données.
        *   `📂 Avis`
            *   `AvisUseCases.java` (Ex: `creerAvis`, `recupererAvisParJeu`, `modererAvis`)
        *   `📂 Jeu`
            *   `JeuUseCases.java` (Ex: `ajouterJeu`, `recupererTousLesJeux`)
        *   `(Autres cas d'utilisation pour les fonctionnalités associées)`
    *   `📂 controller`: Gère les requêtes entrantes (HTTP pour une API REST, par exemple) et délègue le traitement aux cas d'utilisation. Il s'occupe également de la transformation des données entre le format externe (DTOs) et les objets du domaine.
        *   `📂 Avis`
            *   `📂 dto`
                *   `AvisDTO.java`
                *   `CreateAvisDTO.java`
            *   `📂 rest`
                *   `AvisRestController.java` (Expose les endpoints REST pour les avis)
            *   `AvisDtoMapper.java` (Pour mapper entre les DTOs et les objets du domaine/use cases)
        *   `📂 Jeu`
            *   `📂 dto`
                *   `JeuDTO.java`
                *   `CreateJeuDTO.java`
            *   `📂 rest`
                *   `JeuRestController.java`
            *   `JeuDtoMapper.java`
        *   `(Autres contrôleurs, DTOs et mappers)`
    *   `📂 config`: Contient la configuration de l'application, notamment pour l'injection de dépendances (Spring) et la sécurité.
        *   `AvisConfig.java`
        *   `JeuConfig.java`
        *   `SecurityConfig.java`
        *   `UseCasesConfig.java` (Configuration pour l'instanciation des classes de cas d'utilisation et l'injection de leurs dépendances)
    *   `AvisApplication.java`: Point d'entrée de l'application Spring Boot.

## 🛠️ Technologies utilisées

*   **Java**: Version 23+
*   **Spring Boot**: Pour la création d'applications stand-alone et basées sur Spring.
*   **Spring Data JPA**: Pour la persistance des données et l'interaction avec la base de données.
*   **Spring MVC**: Pour la création des API REST.
*   **Lombok**: Pour réduire le code boilerplate (getters, setters, constructeurs, etc.).
*   **H2 Database**: Base de données en mémoire (configurable pour d'autres bases de données).
*   **JUnit 5 / Mockito**: Pour les tests unitaires et d'intégration.

## 🎯 Principes SOLID appliqués

L'application s'efforce de respecter les principes SOLID pour une meilleure conception logicielle.

1.  **S** - Principe de Responsabilité Unique (SRP - Single Responsibility Principle):
    *   *Exemple*: La classe `AvisUseCases` est uniquement responsable de la logique métier liée aux avis (création, modération, récupération). Elle ne gère pas la manière dont les avis sont stockés (c'est le rôle de `AvisJpaAdapter`) ni comment ils sont exposés via une API (c'est le rôle de `AvisRestController`). Chaque classe a une seule raison de changer.

2.  **O** - Principe Ouvert/Fermé (OCP - Open/Closed Principle):
    *   *Exemple*: Si nous souhaitons changer de mécanisme de persistance pour les avis (passer de JPA à MongoDB par exemple), nous pourrions créer une nouvelle classe `AvisMongoDbAdapter` implémentant `AvisDataSourcePort` sans avoir à modifier `AvisUseCases` ou le domaine `Avis`. Le système est ouvert à l'extension (nouvel adaptateur) mais fermé à la modification (les cas d'utilisation existants n'ont pas besoin d'être changés).

3.  **L** - Principe de Substitution de Liskov (LSP - Liskov Substitution Principle):
    *   *Exemple*:  `AvisJpaAdapter` est un substitut valide pour `AvisDataSourcePort`. Toute classe utilisant `AvisDataSourcePort` pourrait fonctionner indifféremment avec `AvisJpaAdapter` ou toute autre implémentation future de ce port sans altérer le comportement attendu.

4.  **I** - Principe de Ségrégation des Interfaces (ISP - Interface Segregation Principle):
    *   *Exemple*: L'interface `AvisDataSourcePort` définit uniquement les méthodes nécessaires pour la gestion des données des avis (ex: `save`, `findById`, `findByJeuId`). Si nous avions une interface plus large pour toutes les opérations de données, les classes implémentant cette interface pour des entités simples seraient forcées d'implémenter des méthodes non pertinentes. En ségrégeant les interfaces par entité ou par fonctionnalité, on évite ce problème.

5.  **D** - Principe d'Inversion des Dépendances (DIP - Dependency Inversion Principle):
    *   *Exemple*: La classe `AvisUseCases` dépend de l'abstraction `AvisDataSourcePort` (une interface définie dans la couche domaine) et non d'une implémentation concrète comme `AvisJpaAdapter`. L'implémentation concrète (`AvisJpaAdapter`) dépend également de cette abstraction. La dépendance est donc inversée : les modules de haut niveau (cas d'utilisation) ne dépendent pas des modules de bas niveau (détails de persistance), mais tous deux dépendent d'abstractions. Ceci est généralement réalisé via l'injection de dépendances (Spring injecte une instance de `AvisJpaAdapter` là où un `AvisDataSourcePort` est requis).

    ```java
    // Dans AvisUseCases.java
    public class AvisUseCases {
        private final AvisDataSourcePort avisDataSourcePort; // Dépendance sur une abstraction

        public AvisUseCases(AvisDataSourcePort avisDataSourcePort) {
            this.avisDataSourcePort = avisDataSourcePort;
        }
        // ... méthodes utilisant avisDataSourcePort
    }
    ```

## 🧪 Tests et qualité du code
- **Tests Unitaires**:
    - Des tests unitaires sont mis en place pour valider le comportement de chaque composant de manière isolée.
    - Exemples de classes testées :
        - (test de la logique métier des avis) `AvisUseCasesTest`
        - (test de la logique métier des jeux) `JeuUseCasesTest`
        - (test des mappers entre entités de domaine et entités JPA) `AvisMapperTest`
        - (test des mappers entre DTOs et objets de domaine) `JeuDtoMapperTest`
        - (tests pour les contrôleurs, potentiellement avec MockMvc) `AvisControllerTest`
        - `JeuControllerTest`

- **Tests d'Intégration**:
    - (test de base pour vérifier que le contexte Spring se charge correctement). `AvisApplicationTests`
    - Des tests d'intégration pour les s sont prévus pour valider les flux de bout en bout, de la requête HTTP à la réponse, en passant par la base de données (avec H2). `RestController`

- **Commande pour lancer les tests**:
``` bash
    mvn test
```


##  Données initiales et utilisateurs par défaut

Au démarrage de l'application, un jeu de données initial est automatiquement inséré en base. Cela inclut des classifications, genres, plateformes, éditeurs, avatars, ainsi que des jeux, joueurs et avis de démonstration. Cette initialisation est gérée par la classe `AjoutDonneesInitiales.java` et permet d'avoir une application fonctionnelle avec du contenu dès le premier lancement.

L'application est configurée pour démarrer sur le port **8081**.

### Utilisateurs par défaut :

Deux utilisateurs sont créés par défaut pour faciliter les tests et la démonstration :

*   **Joueur**
    *   Identifiant (login) : `user`
    *   Mot de passe : `password`
    *   Rôle : `JOUEUR`
*   **Modérateur**
    *   Identifiant (login) : `mod`
    *   Mot de passe : `modpass`
    *   Rôle : `MODERATEUR`

## Routes principales de l'application

Voici les routes principales utilisées pour interagir avec l'application via un navigateur web :

*   `http://localhost:8081/jeux`
    *   **Description** : Affiche la liste de tous les jeux disponibles dans l'application.
    *   **Accès** : Ouvert à tous les utilisateurs (authentifiés ou non, selon la configuration de sécurité globale).

*   `http://localhost:8081/jeux/{id}` (par exemple, `http://localhost:8081/jeux/1`)
    *   **Description** : Affiche la page de détail d'un jeu spécifique, y compris les avis laissés par les joueurs pour ce jeu.
    *   **Accès** : Ouvert à tous les utilisateurs.

*   `http://localhost:8081/jeux/ajouter`
    *   **Description** : Permet aux utilisateurs ayant le rôle `MODERATEUR` d'accéder au formulaire d'ajout d'un nouveau jeu dans la base de données. Après soumission, si l'ajout est réussi, l'utilisateur est redirigé vers la page de détail du jeu nouvellement créé.
    *   **Accès** : Réservé aux utilisateurs authentifiés avec le rôle `MODERATEUR`.

Ces routes correspondent aux fonctionnalités principales de navigation et de gestion des jeux et des avis au sein de l'application. L'accès aux fonctionnalités de création/modification est contrôlé par des rôles utilisateurs.



![Diagramme Avis](avis.png)