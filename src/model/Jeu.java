package model;

import java.awt.*;
import java.util.ArrayList;
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

    public void tirHumain(Coup c) {
        Bateau b = c.getBateau();
        b.utiliserMunition();
        ArrayList<Point> zoneSup = b.getZoneSup();
        for(Point p : zoneSup){
           boolean estTouche = terrainJ2.getChampTir().estTouche(p);
           if(!estTouche)
               
        }
    }

    public void tirIA() {

    }

    public void checkerConditionVictoireDefaite() {
        ArrayList<Bateau> bateaux = terrainJ1.getBateaux();
        boolean aPerdu = true;
        for(Bateau b : bateaux){
            if(b.getMunitions() > 0 && (!b.estMort()))
                aPerdu = false;
        }
        if (aPerdu)
            return;
            //VueVictoire ?
        else{
            ArrayList<Bateau> bateauxJ2 = terrainJ2.getBateaux();
            boolean J2Perdu = true;
            for(Bateau b : bateauxJ2){
                if(b.getMunitions() > 0 && (!b.estMort()))
                    J2Perdu = false;
            }
            if(J2Perdu)
                return;
                //vueVictoire
            else
                //on continue la partie
                return;
        }

    }

}
