package model;

import textureFactory.SingletonEpoque;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Jeu extends Observable {

    private static Jeu instance;

    /** -1 Humain, 0 1 2 IA (difficultés)**/
    private int typeIA;
    private boolean enCours;

    private Terrain terrainJ1;
    private Terrain terrainJ2;

    private IA ia;
    private SingletonEpoque se;

    private Jeu () {
        enCours=true;
    }

    public static Jeu getInstance() {
        if (instance == null) {
            instance=new Jeu();
        }
        return instance;
    }

    public void setSe(SingletonEpoque se) {
        this.se = se;
    }

    public SingletonEpoque getSe() {
        return se;
    }

    public IA getIa() {
        return ia;
    }

    //TODO Modifier diagramme
    public boolean verificationTir(Coup c, Terrain t) {
        return t.verificationTirer(c);
    }


    public void checkerConditionVictoireDefaite() {
        ArrayList<Bateau> bateaux = terrainJ1.getBateaux();
        boolean aPerdu = true;
        for(Bateau b : bateaux){
            if(b.getMunitions() > 0 && (!b.estMort()))
                aPerdu = false;
        }

        //J1 perd
        if (aPerdu) {
            enCours = false;
            System.out.println("Vous avez perdu");
        }

        //J2 ou IA perd
        else{
            ArrayList<Bateau> bateauxJ2 = terrainJ2.getBateaux();
            boolean J2Perdu = true;
            for(Bateau b : bateauxJ2){
                if(b.getMunitions() > 0 && (!b.estMort()))
                    J2Perdu = false;
            }
            if(J2Perdu) {
                enCours = false;
                System.out.println("Vous avez gagné !");
            }

        }

    }

    public void setTerrainJ1(Terrain terrainJ1){
    	this.terrainJ1 = terrainJ1;
	}

	public void setTerrainJ2(Terrain terrainJ2){
        this.terrainJ2 = terrainJ2;
        IA.setTerrain(this.terrainJ2);
        IA.difficulte=IA.FACILE;
	}

	public SingletonEpoque getEpoque(){
    	return this.getSe();
	}

	public Terrain getTerrainJ1(){ return this.terrainJ1;}

	public Terrain getTerrainJ2(){ return this.terrainJ2;}
}
