package model;

import textureFactory.SingletonEpoque;
import textureFactory.SingletonMedieval;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class IA {


    public static final int FACILE = 0;
    public static final int NORMAL = 1;
    public static final int HARDCORE = 2;
    private int difficulte;
    private Terrain terrain;
    //degueu mais necessaire pour des tests
    private SingletonEpoque epoque;

    public IA(int diff, Terrain terr){
        terrain = terr;
        difficulte = diff;
        epoque = SingletonMedieval.getInstance();
    }

    /** methode placer Bateau pour l'IA.
     * Genere la meme flotte que le joueur humain
     * Pour chaque bateau de la flotte on cree un nouveau Coup avec ce bateau
     * Et une pos aleatoire, une direction aleatoire
     * On verifie si le bateau peut être place tel quel,
     * Si oui on passe au suivant, sinon on essaye de replacer le bateau avec une nouvel direction
     * et une nouvelle pos
     */
    public ArrayList<Bateau> placerBateaux(){
        ArrayList<Bateau> bateaux = Jeu.getInstance().getEpoque().generateFleet();
        //ArrayList<Bateau> bateaux = epoque.generateFleet();
        Point p = new Point(1,1);
        Coup c = new Coup(p, bateaux.get(0));
        Random r = new Random();
        for(Bateau b : bateaux){
            c.setBateau(b);
           do{
               c.getBateau().setDirection(r.nextInt(4));
               p.x = r.nextInt(10);
               p.y = r.nextInt(10);
               c.getBateau().setPosition(p);
           }while(!terrain.getDisposition().peutEtrePlace(c));
            terrain.placer(c);
        }
        return terrain.getBateaux();
    }


    public boolean tirerFacile(){
        Random r = new Random();
        Point p = new Point(r.nextInt(10), r.nextInt(10));
        Coup c = new Coup(p,terrain.getBateaux().get(r.nextInt(5)));

        while(!(terrain.tirer(c))){

            //Si le bateau ne peut pas tirer, on change le bateau
            if(!c.getBateau().peutTirer())
                c.setBateau(terrain.getBateaux().get(r.nextInt(5)));
            else{
                //On change le point qu'on vise
                p.x = r.nextInt(10);
                p.y = r.nextInt(10);
                c.setXY(p.x, p.y);
            }

         }
        return true;
    }

    public boolean tirMoyen(){
       return true;
    }

    public Terrain getTerrain(){return this.terrain;}

}
