package model;

import textureFactory.SingletonEpoque;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Jeu extends Observable {

    private static Jeu instance;

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



    public void tirIA() {

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
	}

	public SingletonEpoque getEpoque(){
    	return this.terrainJ1.getBateaux().get(0).getFactory();
	}

	public Terrain getTerrainJ1(){ return this.terrainJ1;}

	public Terrain getTerrainJ2(){ return this.terrainJ2;}
}
