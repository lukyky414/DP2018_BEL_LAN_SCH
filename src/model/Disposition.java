package model;

import java.awt.*;

public class Disposition {

    private Bateau[][] champ;

    public Bateau get(Point pos) {
        return champ[(int)(pos.getX())][(int)(pos.getY())];
    }
}
