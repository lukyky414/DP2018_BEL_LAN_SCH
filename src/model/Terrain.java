package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Terrain extends Observable {

    private ArrayList<Bateau> bateaux;

    private Disposition disposition;
    private ChampTir champTir;

    //Si une  case de la zone n'a pas été touché, on peut tirer.
    public boolean verificationTirer(Coup c) {
        Bateau b=c.getBateau();
        if (b.estMort() || b.aMunitions() == false) {
            return false;
        } else {
            ArrayList<Point>alp=b.getZoneSup();
            for (int i=0;i<alp.size();i++) {
                Point p=alp.get(i);
                if (champTir.estTouche(p) == false) {
                    return true;
                }
            }
            return false;
        }
    }

    public void placerBateaux() {

    }

    public void destroyShip(Bateau b) {

    }

}
