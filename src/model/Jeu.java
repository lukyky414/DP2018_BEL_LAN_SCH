package model;

import textureFactory.SingletonEpoque;

import javax.swing.*;
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

    public static Jeu newInstance() {
    	return new Jeu();
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


    /**
     * Vérifie si la partie est finie et indique le gagnant
     * @param joueur Le joueur qui vient de tirer (true pour j1)
     * @return true si la partie est finie
     */
    public boolean checkerConditionVictoireDefaite(boolean joueur) {
        //Si J1 vient de tirer
        if (joueur) {
            //Si J2 mort
            if (joueurEstMort(false)) {
                JOptionPane.showMessageDialog(null, "Vous avez gagné.");
                return true;
            }

            //Si J1 mort
            if (joueurEstMort(true)) {
                JOptionPane.showMessageDialog(null, "Vous avez perdu.");
                return true;
            }
        //Si J2 vient de tirer
        } else  {
            //Si J1 mort
            if (joueurEstMort(true)) {
                JOptionPane.showMessageDialog(null, "Vous avez perdu.");
                return true;
            }

            //Si J2 mort
            if (joueurEstMort(false)) {
                JOptionPane.showMessageDialog(null, "Vous avez gagné.");
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie si un joueur est mort
     * @param joueur Le joueur que l'on veut vérifier (true pour j1)
     * @return true Si le joueur est mort
     */
    private boolean joueurEstMort(boolean joueur) {
        ArrayList<Bateau> bateaux;
        if (joueur) {
            bateaux=terrainJ1.getBateaux();
        } else {
            bateaux=terrainJ2.getBateaux();
        }
        for(Bateau b : bateaux){
            if(b.getMunitions() > 0 && (!b.estMort())) {
                return false;
            }
        }
        //Le joueur est donc mort ici.
		//Il faut ensuite tuer ses bateaux pour qu'ils s'affichent.
		for(Bateau b : bateaux){
			while(!b.estMort())
				b.diminuerVie();
		}
        return true;
    }

    public void setTerrainJ1(Terrain terrainJ1){
    	this.terrainJ1 = terrainJ1;
	}

	public void setTerrainJ2(Terrain terrainJ2){
        this.terrainJ2 = terrainJ2;
	}

	public SingletonEpoque getEpoque(){
    	return this.getSe();
	}

	public Terrain getTerrainJ1(){ return this.terrainJ1;}

	public Terrain getTerrainJ2(){ return this.terrainJ2;}
}
