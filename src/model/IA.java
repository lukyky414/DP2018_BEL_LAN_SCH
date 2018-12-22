package model;

import textureFactory.SingletonEpoque;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class IA {


    public static final int FACILE = 0;
    public static final int NORMAL = 1;
    public static final int HARDCORE = 2;

    public static int difficulte;

    private static Terrain terrainJoueur;
    private static Terrain terrainAdverse;


    //degueu mais necessaire pour des tests
    private static SingletonEpoque epoque;


    /** methode placer Bateau pour l'IA.
     * Genere la meme flotte que le joueur humain
     * Pour chaque bateau de la flotte on cree un nouveau Coup avec ce bateau
     * Et une pos aleatoire, une direction aleatoire
     * On verifie si le bateau peut être place tel quel,
     * Si oui on passe au suivant, sinon on essaye de replacer le bateau avec une nouvel direction
     * et une nouvelle pos
     */
    public static ArrayList<Bateau> placerBateaux() {
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
               c.setXY(p.x,p.y);
           }while(!terrainJoueur.verificationPlacer(c));
            terrainJoueur.placer(c);

            Bateau ba=c.getBateau();
            int x=(int)(ba.getPosition().getX());
            int y=(int)(ba.getPosition().getY());
            /*System.out.println("id="+ba.getId()+"x="+x+" y="+y);
            System.out.println(ba.hashCode());*/
        }
        return terrainJoueur.getBateaux();
    }

    public static boolean tirer() {
        switch(IA.difficulte){
            case IA.FACILE : IA.tirerFacile(); break;
            case IA.NORMAL : IA.tirMoyen(); break;
            case IA.HARDCORE : IA.tirHardcore(); break;
        }
        return true;
    }

    public static boolean LUKY_tirerFacile(){
		Random r = new Random();

		ArrayList<Bateau> _bateauxDispo = new ArrayList<>();
		for(Bateau b : terrainJoueur.getBateaux()){
			if(b.peutTirer())
				_bateauxDispo.add(b);
		}

		ArrayList<Point> _posDispo = new ArrayList<>();
		int Colonne = 0, Ligne = 0;
		for(boolean estTouche : terrainAdverse.getChampTir()){
			if(!estTouche)
				_posDispo.add(new Point(Ligne, Colonne));

			Colonne++;
			if(Colonne == 10){
				Colonne = 0;
				Ligne++;
			}
		}

		if(_bateauxDispo.size() == 0 || _posDispo.size() == 0)
			return false;

		Coup coupFinal = new Coup(new Point(_posDispo.get(r.nextInt(_posDispo.size()))),
				_bateauxDispo.get(r.nextInt(_bateauxDispo.size())));

		terrainAdverse.tirer(coupFinal);
		
		return true;
	}


    public static boolean tirerFacile(){
        Random r = new Random();
        Point p = new Point(r.nextInt(10), r.nextInt(10));
        Coup c = new Coup(p, terrainJoueur.getBateaux().get(r.nextInt(5)));
        ArrayList<Point> pointsAToucher = new ArrayList<Point>();
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; i++){
                pointsAToucher.add(new Point(i,j));
            }
        }

        while(!(terrainAdverse.tirer(c)) || (!pointsAToucher.contains(p))){

            //Si le bateau ne peut pas tirer, on change le bateau
            if(!c.getBateau().peutTirer())
                c.setBateau(terrainJoueur.getBateaux().get(r.nextInt(5)));
            else{
                //On change le point qu'on vise
                p.x = r.nextInt(10);
                p.y = r.nextInt(10);
                c.setXY(p.x, p.y);
            }
            pointsAToucher.remove(p);

         }
        return true;
    }

    public static boolean tirMoyen(){
        Random r = new Random();
        ArrayList<Point> posAutour = new ArrayList<Point>();
        Point p = new Point(r.nextInt(10), r.nextInt(10));
        ArrayList<Point> pointsAToucher = new ArrayList<Point>();
        Coup c = new Coup(p, terrainJoueur.getBateaux().get(r.nextInt(5)));

        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; i++){
                pointsAToucher.add(new Point(i,j));
            }
        }

        //c.getBateau().peutTirer() && pointsAToucher.contains(p)
        while(!c.getBateau().peutTirer() || (!pointsAToucher.contains(p))){
            if(!c.getBateau().peutTirer())
                c.setBateau(terrainJoueur.getBateaux().get(r.nextInt(5)));
            else
                c.setXY(r.nextInt(10), r.nextInt(10));
        }


        if(posAutour.isEmpty())
            return true;
        else{
            c.setXY(posAutour.get(0).x, posAutour.get(0).y);
            posAutour.remove(c.getPos());
            return true;
        }
    }

    public static boolean tirHardcore(){


        return true;
    }

    public static Terrain getTerrainJoueur(){return terrainJoueur;}

    public static void setTerrain(Terrain terrainJoueur, Terrain terrainAdverse) {
        IA.terrainJoueur=terrainJoueur;
        IA.terrainAdverse=terrainAdverse;
    }
}
