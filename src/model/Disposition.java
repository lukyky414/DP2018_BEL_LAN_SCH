package model;

import java.awt.*;

public class Disposition {

    private Bateau[][] champ;
	/*
	 * { { B , B , B },
	 * 	{ B , B , B },
	 * 	{ B , B , B }}
	 *
	 * Une position X,Y correspond a champ[Y][X] */

    public Disposition() {
        this.champ = new Bateau[10][10];
    }

    /**
	 * Permet de recuperer le bateau sur la position donnee.
	 *
	 * @param pos, la position que l'on verifie
	 * @return le bateau a la position, null si inexistant.
	 */
    public Bateau get(Point pos) {
		if(estEnDehors(pos)){
			return null;
		}

		return champ[pos.y][pos.x];
    }

    /**
	 * Permet de savoir si on peut utiliser cette case pour y placer un bateau.
	 *
	 * @param pos, la position que l'on verifie
	 * @return true si case vide, false si case pleine ou en dehors de la map.
	 */
    public boolean estUtilisable(Point pos) {
        if(estEnDehors(pos))
        	return false;
        if (get(pos) == null) {
			return true;
		}
		return false;
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


    //TODO Modifier diagramme
    //TODO A compléter
    public boolean placer(Coup c) {
        if (peutEtrePlace(c)) {
            Bateau b=c.getBateau();
            Point p=c.getPos();
            int taille=b.getTaille();
            int direction=b.getDirection();
            int x=(int)p.getX();
            int y=(int)p.getY();

            switch (direction) {
                case Bateau.HAUT:
                    for (int i = 0; i < taille; i++) {
                        champ[x-i][y]=b;
                    }
                    return true;
                case Bateau.BAS:
                    for (int i = 0; i < taille; i++) {
                        champ[x+i][y]=b;
                    }
                    return true;
                case Bateau.GAUCHE:
                    for (int i = 0; i < taille; i++) {
                        champ[x][y-i]=b;
                    }
                    return true;
                case Bateau.DROITE:
                    for (int i = 0; i < taille; i++) {
                        champ[x][y+i]=b;
                    }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean peutEtrePlace(Coup c) {
        Bateau b=c.getBateau();
        Point p=c.getPos();
        int taille=b.getTaille();
        int direction=b.getDirection();
        int x=(int)p.getX();
        int y=(int)p.getY();

        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < taille; i++) {
                    if (!estUtilisable(new Point(x-i, y))) {
                        return false;
                    }
                }
                return true;
            case Bateau.BAS:
                for (int i = 0; i < taille; i++) {
                    if (!estUtilisable(new Point(x+i, y ))) {
                        return false;
                    }
                }
                return true;
            case Bateau.GAUCHE:
                for (int i = 0; i < taille; i++) {
                    if (!estUtilisable(new Point(x, y-i))) {
                        return false;
                    }
                }
                return true;
            case Bateau.DROITE:
                for (int i = 0; i < taille; i++) {
                    if (!estUtilisable(new Point(x, y+i))) {
                        return false;
                    }
                }
                return true;
        }
        return false;
    }


}
