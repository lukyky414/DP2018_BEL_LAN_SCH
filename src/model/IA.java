package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IA {

    private ArrayList<Bateau> bateaux;
    private int difficulte;
    private Terrain terrain;

    public IA(int diff, Terrain terr){
        bateaux = new ArrayList<Bateau>();
        terrain = terr;
        difficulte = diff;
    }

    /** methode placer Bateau pour l'IA.
     * Genere la meme flotte que le joueur humain
     * Pour chaque bateau de la flotte on cree un nouveau Coup avec ce bateau
     * Et une pos aleatoire, une direction aleatoire
     * On verifie si le bateau peut être place tel quel,
     * Si oui on passe au suivant, sinon on essaye de replacer le bateau avec une nouvel direction
     * et une nouvelle pos
     */
    public void placerBateaux(){
        bateaux = Jeu.getInstance().getEpoque().generateFleet();
        Point p = new Point(1,1);
        Coup c = new Coup(p,bateaux.get(0));
        Random r = new Random();
        for(Bateau b : bateaux){
            c.setBateau(b);
           do{
               c.getBateau().setDirection(r.nextInt(4));
               p.x = r.nextInt(10);
               p.y = r.nextInt(10);
               c.getBateau().setPosition(p);
           }while(!terrain.getDisposition().peutEtrePlace(c));


        }
    }

}
