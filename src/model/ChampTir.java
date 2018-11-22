package model;

import model.Iterators.ChampTirIterator;

import java.awt.*;
import java.util.Iterator;

public class ChampTir implements Iterable<Boolean> {

    private boolean[][] champ;
    /*
    * { { B , B , B },
    * 	{ B , B , B },
    * 	{ B , B , B }}
    *
    * Une position X,Y correspond a champ[Y][X] */

    /**
	 * On fixe la taille d'un champ de tir a 10 par 10.
	 * Un champ de tir retient quelle case a deja ete touchee.
	 * Cela permet de ne pas tirer 2x sur la meme case ainsi que de charger facilement une partie.
	 *
	 * La taille est fixe a 10x10.
	 */
    public ChampTir() {
        this.champ = new boolean[10][10];
    }

    /**
	 * Permet de savoir si la case en parametre a ete touchee ou non.
	 * Si la case est en dehors de la map, elle est touchee.
	 *
	 * @param pos, la position que l'on verifie
	 * @return true si la case est touchee, false sinon.
	 */
    public boolean estTouche(Point pos) {

        if(estEnDehors(pos)){
        	return true; //<- Une case en dehors du terrain est considere comme touchee.
		}

		return champ[pos.y][pos.x];
    }

    /**
	 * Cette methode est appellee quand un bateau tire sur une case.
	 * La case devient donc touchee.
	 * Un tir en dehors de la map est ignore.
	 *
	 * @param pos, la position a laquelle on tire.
	 */
    public void touche(Point pos) {

		if(!estEnDehors(pos)){
			champ[pos.y][pos.x]=true;
		}

    }

    /**
	 * Permet de savoir facilement si une position depasse la map ou non.
	 *
	 * @param pos, la position a verifier
	 * @return true si pos est en dehors, false sinon.
	 */
    private boolean estEnDehors(Point pos){
    	return (pos.x < 0 || pos.x >= 10 || pos.y < 0 || pos.y >= 10);
	}

    /**
	 * Permet de parcourir facilement tout la carte du Champ de Tir
	 */
	@Override
	public Iterator<Boolean> iterator() {
		return new ChampTirIterator(this);
	}
}
