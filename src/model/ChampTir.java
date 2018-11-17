package model;

import java.awt.*;

public class ChampTir {

    private boolean[][] champ;

    public boolean estTouche(Point pos) {
        int x=(int)(pos.getX());
        int y=(int)(pos.getY());

        if (x >= 0 && x<=champ.length-1 && y >= 0 && y<=champ[0].length-1) {
            return champ[x][y];
        } else {
            //On considère que une case hors du terrain est déjà touchée
            return true;
        }
    }

    public void touche(Point pos) {
        int x=(int)(pos.getX());
        int y=(int)(pos.getY());

        if (x >= 0 && x<=champ.length-1 && y >= 0 && y<=champ[0].length-1) {
            champ[x][y]=true;
        }
    }
}
