package model;

import java.awt.*;

public class Disposition {

    private Bateau[][] champ;

    public Bateau get(Point pos) {
        int x=(int)(pos.getX());
        int y=(int)(pos.getY());

        if (x >= 0 && x<=champ.length-1 && y >= 0 && y<=champ[0].length-1) {
            return champ[x][y];
        } else {
            return null;
        }
    }


    //TODO Modifier diagramme
    //TODO A compléter
    public boolean placer(Bateau b) {
        //int direction=
        int taille=b.getTaille();
        Point p=b.getPosition();
        return false;
    }


}
