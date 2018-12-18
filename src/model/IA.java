package model;

import java.util.ArrayList;

public class IA {

    private ArrayList<Bateau> bateaux;

    public IA(){
        bateaux = new ArrayList<Bateau>();
    }

    public void placerBateaux(){
        bateaux = Jeu.getInstance().getEpoque().generateFleet();
    }

}
