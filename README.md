# Système multi-agents

M. CHASTE
PP. BERENGUER

M2 IAGL

13-01-2015

Université de Lille 1

## Water : Des requins qui mangent des poissons

Une grille regroupe des agents. Les poissons et requins sont des agents.
Sur une grille de taille n x n, **les agents sont placés aléatoirement**.
A chaque tour de la partie les agents peuvent, selon le délais fixé, donner naissance à un nouvel agent.

Un poisson peut se deplacer dans une case alentour si elle est vide.
Lorsque le moment est venu, un poisson accouche.

Les requins quand à eux meurt s'il n'ont plus de point de vie.
En se déplaçant sur une case contenant un poisson, le poisson est mangé il disparait. Le requin regagne alors tous ses points de vie.

## City : Les agents se regroupent par type

Tant qu'une satisfaction comprise entre 0 et 1 n'est pas satisfaite pour un agent, alors il se déplace aleatoirement dans la grille.
La **satisfaction est calculée en prenant le nombre de voisins identique / par le nombre total de voisins sur les cases adjacentes**.

## Exécution

Nécessite Java 1.7.

Lancer Eclipse -> File -> Import
Dans la partie "Générale" -> **"Existing Projects into Workspace"**

Ensuite il faut choisir la classe src.controller.Main comme classe principale.
Pour lancer Water il faut mettre en argument:
* --water
respectivement
* --city

### GUI

Lancer la partie de façon automatique en cliquant sur le bouton "Play".
Le bouton "Next" permet de faire seulement une seule itération du jeu.
Le slider permet d'augmenter le delais avant l'appel à Environment.doIt() c.a.d une itération d'action pour tous les agents.

## Configuration de la partie

Une factory permet de ici de créer soit l'envirronement :

* *Environment city = EnvironmentFactory.createAndInitializeCity(50, 800, 800, 0.7f);*

Les arguments correspondent à la taille de la grille, nombre d'agents de type 1, nombre d'agents de type 2, et la satisfaction minimale à satisfaire qui va de 0 à 1.

Idem pour water :
* *WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(40, 200, 200, 1, 3, 3);*

Les arguments correspondent à la taille de la grille, le nombre de thons, le nombre de requins, le nombre de tour avant que le thon accouche, le nombre de tour pour que le requin accouche, et le nombre de tour pour le requin décède.

Code pour lancer une système multi-agent:
```
Environment city = EnvironmentFactory.createAndInitializeCity(50, 800, 800, 0.7f);
//Environment city = EnvironmentFactory.createAndInitializeCity(30, 50, 50, 0.6f);

// initiate view
MainFrame view = new MainFrame(city);
// attach view as observer of the model
city.attach(view);
view.setVisible(true);
```

## Architecture
```
── images
│   ├── blue-water-icon.png
│   ├── fish-icon.png
│   ├── next.png
│   ├── pause.png
│   ├── play.png
│   ├── restart.png
│   └── shark-icon.png
├── README.md
├── src
│   ├── controller
│   │   └── Main.java
│   ├── model
│   │   ├── core
│   │   │   ├── AgentFactory.java
│   │   │   ├── Agent.java
│   │   │   ├── Environment.java
│   │   │   ├── Observable.java
│   │   │   ├── population
│   │   │   │   ├── People.java
│   │   │   │   ├── PeopleType1.java
│   │   │   │   └── PeopleType2.java
│   │   │   └── water
│   │   │       ├── Fish.java
│   │   │       ├── Shark.java
│   │   │       ├── Tuna.java
│   │   │       └── WaterEnvironment.java
│   │   └── EnvironmentFactory.java
│   └── view
│       ├── GridPanel.java
│       ├── MainFrame.java
│       ├── MenuPanel.java
│       └── Observer.java
└── statistics
    ├── city
    │   └── evolution_satisfaction_grille50x50_200vs200.png
    └── fish
        ├── moyenne-age_poissons_requins_100x100_2.csv
        ├── ... etc
```
        
## Implémentation

Les agents Fish (pour water) et People (pour city) heritent de Agent.java.

EnvironmentFactory.java permet de créer/initialiser un environnement et ses agents en un seule appel.

Le patron MVC est utilisé. Model sera l'Envirronnement et contient les agents.
La vue sera MainFrame qui comporte un grille pour afficher le jeu (environnement, et agents).

Le **patern Observeur/Observable** est utilisé. La vue est abonnée à l'Envirronnement.
Envirronnement implémente Observable.
MainFrame implémente Observeur.

Lors d'un itération de la partie via Envirronnement.doIt() alors l'Envirronnement prévient ses abonnées, ici la vue.
La vue dispose dans JPanel menu avec bouton play, pause, next, et un slider permettant d'accéler (resp. ralentir) le système.

L'initialisation du système se déroule dans le controleur (voir Configuration de la partie).

## Statistiques

Dans le répertoire **stats/** sont présents des enregistrements de valeurs obtenues lors de l'exécution d'une partie.
Certains fichiers d'enregistrements disposent d'un graphe au nom éponyme.
