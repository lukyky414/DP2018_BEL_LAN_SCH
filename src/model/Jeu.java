package model;

import java.util.Observable;

public class Jeu extends Observable {

    public static Jeu instance;

    private boolean enCours;

    private Terrain terrainJ1;
    private Terrain terrainJ2;

    private Jeu () {
        enCours=true;
    }

    public static Jeu getInstance() {
        if (instance == null) {
            instance=new Jeu();
        }
        return instance;
    }

    //TODO Modifier diagramme
    public boolean verificationTir(Coup c, Terrain t) {
        return t.verificationTirer(c);
    }

    public void tirHumain() {

    }

    public void tirIA() {

    }

    public void checkerConditionVictoireDefaite() {

    }

}
