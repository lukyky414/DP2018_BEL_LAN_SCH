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

    public static int difficulte = FACILE;

	public static Jeu jeu = Jeu.newInstance();


    /** methode placer Bateau pour l'IA.
     * Genere la meme flotte que le joueur humain
     * Pour chaque bateau de la flotte on cree un nouveau Coup avec ce bateau
     * Et une pos aleatoire, une direction aleatoire
     * On verifie si le bateau peut être place tel quel,
     * Si oui on passe au suivant, sinon on essaye de replacer le bateau avec une nouvel direction
     * et une nouvelle pos
     */
    public static Terrain placerBateaux() {
        ArrayList<Bateau> bateaux = jeu.getEpoque().generateFleet();
        //ArrayList<Bateau> bateaux = epoque.generateFleet();
        Point p = new Point(1,1);
        Coup c = new Coup(p, bateaux.get(0));
        Random r = new Random();
        jeu.setTerrainJ1(new Terrain());
        for(Bateau b : bateaux){
            c.setBateau(b);
           do{
               c.getBateau().setDirection(r.nextInt(4));
               p.x = r.nextInt(10);
               p.y = r.nextInt(10);
               c.getBateau().setPosition(p);
               c.setXY(p.x,p.y);
           }while(!jeu.getTerrainJ1().verificationPlacer(c));
			jeu.getTerrainJ1().placer(c);

            Bateau ba=c.getBateau();
            int x=(int)(ba.getPosition().getX());
            int y=(int)(ba.getPosition().getY());
            /*System.out.println("id="+ba.getId()+"x="+x+" y="+y);
            System.out.println(ba.hashCode());*/
        }
        return jeu.getTerrainJ1();
    }

    public static Coup tirer() {
    	Coup c = null;
        switch(IA.difficulte){
            case IA.FACILE : c = IA.tirerFacile(); break;
            case IA.NORMAL : c = IA.tirMoyen(); break;
            case IA.HARDCORE : c = IA.tirHardcore(); break;
        }
        return c;
    }

    private static ArrayList<Bateau> getBateauDispo() {
        ArrayList<Bateau> _bateauxDispo = new ArrayList<>();
        for(Bateau b : jeu.getTerrainJ1().getBateaux()){
            if(b.peutTirer())
                _bateauxDispo.add(b);
        }
        return _bateauxDispo;
    }

    private static ArrayList<Point> getPointDispos() {
        ArrayList<Point> _posDispo = new ArrayList<>();
        int Colonne = 0, Ligne = 0;
        for(boolean estTouche : jeu.getTerrainJ2().getChampTir()){
            if(!estTouche)
                _posDispo.add(new Point(Ligne, Colonne));

            Colonne++;
            if(Colonne == 10){
                Colonne = 0;
                Ligne++;
            }
        }
        return _posDispo;
    }

    public static Coup tirerFacile(){
		Random r = new Random();

        ArrayList<Bateau> _bateauxDispo=getBateauDispo();
		ArrayList<Point> _posDispo = getPointDispos();

		if(_bateauxDispo.size() == 0 || _posDispo.size() == 0)
			return null;

		Coup coupFinal = new Coup(new Point(_posDispo.get(r.nextInt(_posDispo.size()))),
				_bateauxDispo.get(r.nextInt(_bateauxDispo.size())));

		/*if (jeu.getTerrainJ2().verificationTirer(coupFinal)) {
            jeu.getTerrainJ2().tirer(coupFinal);
            return true;
        } else {
		    return false;
        }
        NON -> la selection fait en sorte que le coup SOIT valide!
        Le bateau PEUT tirer, et la case visee n'est pas touchee!
        */
		jeu.getTerrainJ2().tirer(coupFinal);
		return coupFinal;
	}

    private static ArrayList<Point> _strategieEnCroix = new ArrayList<>();
    public static Coup tirMoyen(){
		Random r = new Random();

		ArrayList<Bateau> _bateauxDispo = getBateauDispo();
		ArrayList<Point> _posDispo = getPointDispos();

		if(_bateauxDispo.size() == 0 || _posDispo.size() == 0)
			return null;

		//Methode a faire pour vider la liste
		//en gros les truc deja tire on degage
		Point pos;
		for(int i = 0; i < _strategieEnCroix.size(); i++){
			pos = _strategieEnCroix.get(i);
			if(jeu.getTerrainJ2().getChampTir().estTouche(pos)) {
				_strategieEnCroix.remove(i);
				i--;
			}
		}

		if(_strategieEnCroix.size()>0)
			pos = _strategieEnCroix.get(0);
		else
			pos = new Point(_posDispo.get(r.nextInt(_posDispo.size())));

		Coup coupFinal = new Coup(pos, _bateauxDispo.get(r.nextInt(_bateauxDispo.size())));
		jeu.getTerrainJ2().tirer(coupFinal);

		if(jeu.getTerrainJ2().getDisposition().get(coupFinal.getPos()) != null){
			_strategieEnCroix.add(new Point(pos.x+1,pos.y));
			_strategieEnCroix.add(new Point(pos.x-1,pos.y));
			_strategieEnCroix.add(new Point(pos.x,pos.y+1));
			_strategieEnCroix.add(new Point(pos.x,pos.y-1));
		}

		return coupFinal;
	}


    public static Coup tirHardcore(){


        return null;
    }

    public static Terrain getTerrain(){return jeu.getTerrainJ1();}
}
