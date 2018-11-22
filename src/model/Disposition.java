package model;

import java.awt.*;

public class Disposition {

    private int taille;
    private Bateau[][] champ;

    public Disposition(int taille) {
        this.taille = taille;
        this.champ = new Bateau[taille][taille];
    }

    public Bateau get(Point pos) {
        int x=(int)(pos.getX());
        int y=(int)(pos.getY());

        if (x >= 0 && x<=champ.length-1 && y >= 0 && y<=champ[0].length-1) {
            return champ[x][y];
        } else {
            return null;
        }
    }

    public boolean estUtilisable(Point pos) {
        int x=(int)(pos.getX());
        int y=(int)(pos.getY());
        if (x >= 0 && x<=champ.length-1 && y >= 0 && y<=champ[0].length-1) {
            if (get(pos) == null) {
                return true;
            }
            return false;
        } else {
            return false;
        }
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
