package model;

import java.awt.*;

public class Champ_Tir {

    private boolean[][] champ;

    public boolean estTouche(Point pos) {
        return champ[(int)(pos.getX())][(int)(pos.getY())];
    }

    public void touche(Point pos) {
        champ[(int)(pos.getX())][(int)(pos.getY())]=true;
    }
}
