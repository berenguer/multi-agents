# Système multi-agents

Etudiants:
* M. CHASTE
* PP. BERENGUER

M2 IAGL

dernière édition 27-01-2015

Université de Lille 1

Encadré par Philippe Mathieu - LIFL

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

## Hunting : Les chasseurs suivent le plus court chemin jusqu'a une proie qui s'échappe

Les chasseurs se dirigent vers la proie. **L'algorithme Dijkstra permet de calculer toutes les distances dans la grille depuis la position de la proie**.
A partir de cela chaque chasseur prend la main aléatoirement lors d'un tour de jeux. Le chasseur choisie la case de la grille qui lui ai adjacente et qui a la plus petite distance avec la proie.
Cette case de plus courte distance doit être libre : pas d'autre chasseur, et pas de mûr.

La proie recherche le chasseur le plus proche. Elle compare cette distance avec les mêmes distances cette fois sur les cases adjacentes.
La proie se déplace ensuite, ou reste sur la case avec la plus petite distance du chasseur.

## Exécution via Eclipse

Nécessite Java 1.7.

Lancer Eclipse -> File -> Import
Dans la partie "Générale" -> **"Existing Projects into Workspace"**

Ensuite il faut choisir la classe src.controller.Main comme classe principale.
Pour lancer Water il faut mettre en argument:
* --water
respectivement
* --city
* --hunt

### Exécution via JAR

Vous pouvez également paramétrer avec les options :

--city (int)size (int)type1 occurences (int)type2 occurences (float)min satisfaction
```
java -jar multi-agents.jar --city
```
```
java -jar multi-agents.jar --city 20 90 90 0.7
```

Vous pouvez également paramétrer avec les options :

--water (int)size (int)type1 occurences (int)type2 occurences (int)Tuna birth delay (int)Shark birth delay (int)Shark death delay
```
java -jar multi-agents.jar --water
```
```
java -jar multi-agents.jar --water 40 200 200 1 3 3
```

Vous pouvez également paramétrer avec les options :

--hunt (int)size (int)total hunters (int)total mûrs
```
java -jar multi-agents.jar --hunt
```
```
java -jar multi-agents.jar --hunt 40 100 50
```

## Configuration de la partie

Une factory permet de ici de créer soit l'envirronement :

* Environment city = EnvironmentFactory.createAndInitializeCity(50, 800, 800, 0.7f);*

Les arguments correspondent à la taille de la grille, nombre d'agents de type 1, nombre d'agents de type 2, et la satisfaction minimale à satisfaire qui va de 0 à 1.

Idem pour le système Water, ou Hunt avec les arguments correspondant (voir partie : Exécution via JAR)

## Architecture

```
├── multi-agents.jar
├── README.md
├── src
│   ├── controller
│   │   └── Main.java
│   ├── img
│   │   ├── blue-water-icon.png
│   │   ├── fish-icon.png
│   │   ├── next.png
│   │   ├── pause.png
│   │   ├── play.png
│   │   ├── restart.png
│   │   ├── shark-icon.png
│   │   └── stone-icon.png
│   ├── model
│   │   ├── core
│   │   │   ├── AgentFactory.java
│   │   │   ├── Agent.java
│   │   │   ├── Environment.java
│   │   │   ├── hunt
│   │   │   │   ├── HuntEnvironment.java
│   │   │   │   ├── Hunter.java
│   │   │   │   ├── Prey.java
│   │   │   │   └── Wall.java
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
        ├── moyenne-age_poissons_requins_100x100_2.png
        ├── ...etc
```
        
## Implémentation

Les agents Fish (pour water), People (pour city), Hunter/Prey (pour hunt) heritent de Agent.java.

**EnvironmentFactory.java** permet de créer/initialiser un environnement et ses agents en un seule appel.

Le **patron MVC** est utilisé. Model sera l'Envirronnement et contient les agents.
La vue sera MainFrame qui comporte un grille pour afficher le jeu (environnement, et agents).

Le **patern Observeur/Observable** est utilisé. La vue est abonnée à l'Envirronnement.
Envirronnement implémente Observable.
MainFrame implémente Observeur.
La vue dispose dans JPanel menu avec bouton play, pause, next, et un slider permettant d'accéler (resp. ralentir) le système.

**Fonctionnemant du système multi-agent** :

```
Environment city = EnvironmentFactory.createAndInitializeCity(50, 800, 800, 0.7f);
//Environment city = EnvironmentFactory.createAndInitializeCity(30, 50, 50, 0.6f);

// initiate view
MainFrame view = new MainFrame(city);
// attach view as observer of the model
city.attach(view);
view.setVisible(true);
```

## Déroulement d'une partie

Une itération du système fait appel à Environment.doIt().
Environment.doIt() fait appel pour tous ses agents à Agent.action(). Les actions diffèrent selon le système (water, city, hunt).
Les abonnés (vue) sont prévenus que l'environnement est mis à jour.

* WaterEnvironment permet de sauver les âges, et le nombre de thons, requins. Une update de la grille supprime les thons morts à chaque tour.
* HuntEnvironment offre une méthode pour calculer le plus court chemin jusqu'à la proie : shorterPathAt(int posX, int posY)
 la partie via Envirronnement.doIt() alors l'Envirronnement prévient ses abonnées, ici la vue.

## Statistiques

Dans le répertoire **stats/** sont présents des enregistrements de valeurs obtenues lors de l'exécution d'une partie.
Certains fichiers d'enregistrements disposent d'un graphe au nom éponyme.
