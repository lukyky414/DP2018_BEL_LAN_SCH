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
        //On recupere le bateau qui tire et on lui baisse ses munitions
        Bateau b = c.getBateau();
        b.utiliserMunition();
        ArrayList<Point> zoneSup = b.getZoneSup();

        //On recupere la disposition du terrain adverse pour savoir si le coup porté a échoué
        Disposition d = terrainJ2.getDisposition();
        ChampTir champ = terrainJ2.getChampTir();
        for(Point p : zoneSup){
           boolean estTouche = champ.estTouche(p);
           if(!estTouche){
               champ.touche(p);
               //get recupere le bateau touche
               Bateau ennemi = d.get(p);
               ennemi.diminuerVie();
               boolean mort = ennemi.estMort();
               if(mort)
                   terrainJ2.destroyShip(ennemi);
               checkerConditionVictoireDefaite();

           }

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

    public void setTerrainJ1(Terrain terrainJ1){
    	this.terrainJ1 = terrainJ1;
	}

	public void setTerrainJ2(Terrain terrainJ2){
		this.terrainJ2 = terrainJ2;
	}
}
