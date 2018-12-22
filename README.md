# Projet Master

## Developpeurs:

* BELLANGER Clément
* LANUEL Charlotte
* SCHWAB Lucas

## Pré-requis

Java 8 (JDK), Ant

## Lancement (et compilation)

`ant run`

## Nettoyage

`ant clean`


## Comment jouer

## Démarrer une partie 
Au démarrage de l'application, aller dans le menu (bar de menu)
Fichier -> Démarrer 
Choisissez l'époque avec laquelle vous voulez jouer

Ensuite, placez vos bateaux sur la grille de gauche, prévue pour vous. 
Vous pouvez choisir l'ordre dans lequel vous placez vos bâtiments en cliquant sur les boutons au-dessus de la grille. 


## Tirer sur l'ennemi 

Pour tirer, séléctionner le bateau qui effectue le tir directement sur votre grille. Il s'affiche entouré de jaune. 
Cliquez sur la case visée sur la grille de droite, celle de votre ennemi.

Si la case s'affiche en violet, le coup est dans l'eau. 
Si la case devient rouge, vous avez touché le bateau. Ne reste plus qu'à le couler avec vos prochains tirs. 
Si la case est rouge et que le dessin du bateau apparait, le bateau est coulé. 

Lors de votre tir, un zone s'affiche sur la grille : c'est la zone de tir de votre bateau. 
Plus elle est grande, plus vous avez de chance de toucher ou de couler un bateau. Attention, plus la zone de tir est grande, moins de munitions vous avez : utilisez ces zones à bon escient. 

Si la zone s'affiche en rouge, votre bateau ne peut pas tirer : il est coulé ou n'a plus de munitions pour tirer. Les munitions ne sont pas rechargeable. 

## Sauvegarder et charger une partie

Vous pouvez sauvegarder votre partie à tout moment en allant sur Fichier -> Sauvegarder. 
Si vous avez enregisré une partie, vous pouvez la continuer à tout moment en allant sur Fichier -> Charger. Séléctionez ensuite la partie que vous voulez continuer. 


## Difficulté de l'ordinateur

Pour changer la difficulté du jeu, allez dans IA et cochez la difficulté souhaitée. 