# Structure du projet

````pgsql
src/main/java/fr/esgi/fx/avis/
│── application/                --> Couche Application (Use Cases)
│── domain/                     --> Couche Métier (Entities + Business Rules)
│── infrastructure/             --> Couche Infrastructure (Détails techniques)
│   ├── adapter/                --> Implémentations des ports (Interactions externes)
│   │   ├── persistence/        --> Gestion des bases de données (JPA, MongoDB...)
│   │   │   │── entity/         --> Entités JPA uniquement
│   │   │   │── repository/     --> Interfaces des repositories Spring Data JPA
│   │   │   │── adapter/        --> Implémentation des `Ports` pour la base de données
│   │   ├── web/                --> API REST (Controllers)
│   │   │   │── controller/     --> Exposition des API REST
│   │   │   │── dto/            --> Objets de transfert JSON
│   │   │   │── mapper/         --> Conversions DTO -> Domain
│   │   ├── security/           --> Gestion de l'authentification et des droits
│   ├── config/                 --> Configuration technique de l’application (Spring Boot)
````

## Justification des choix d’architecture

### Domain (Métier)

* Contient les règles métier pures, sans dépendance à JPA, Spring ou tout autre framework.
* Permet une évolution du projet sans impacter le domaine (ex: migration de SQL vers MongoDB).

## Application (Use Cases)

* Orchestration des cas d’usage, sans dépendre de la persistance ou des controllers.
* Implémente les interfaces (Ports) définies pour interagir avec le domaine.

### Infrastructure

L’infrastructure gère tout ce qui est externe à l’application : base de données, API, sécurité, services tiers.

**Persistence** `(infrastructure/adapter/persistence/)`

* Sépare la persistance des objets métier avec des entités JPA distinctes.
* Fournit des adaptateurs qui implémentent les ports de l’application.

**Web** `(infrastructure/adapter/web/)`

* Expose les API REST sans dépendre de la logique métier.
* Les contrôleurs appellent uniquement les Use Cases.

**Sécurité** `(infrastructure/adapter/security/)`

* Gère l'authentification et l'autorisation avec Spring Security.
