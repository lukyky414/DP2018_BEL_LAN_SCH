package model;

import textureFactory.SingletonEpoque;
import textureFactory.WrongEpoqueException;

import javax.swing.*;
import java.awt.Point;
import java.util.ArrayList;

public class Bateau {

    public static final int HAUT = 0;
	public static final int DROITE = 1;
	public static final int BAS = 2;
	public static final int GAUCHE = 3;

    private SingletonEpoque factory;

    //Un id de 0 à 4 pour identifier les textures du bateau
    private int id;
	private int munitions;
	private int pv;
	private int direction;
	private Point position;
	private int taille;
	private ArrayList<Point> zoneSup;

    public Bateau(SingletonEpoque epoque, int id, int taille, ArrayList<Point> zoneSup) {
    	this.id = id;
    	this.factory = epoque;
    	this.taille = taille;
    	this.zoneSup = zoneSup;
    	//TODO MUNITION & VIE DANS LE CONSTRUCTEUR
    	this.munitions = 100;
    	this.pv = taille;
	}

	//###############
	//#  FONCTIONS  #
	//###############

	/**
	 * Cette methode verifie si le bateau a des munitions
	 *
	 * @return boolean
	 */
    public boolean aMunitions(){
        return this.munitions>0;
    }

    /**
	 * Cette methode verifie si le bateau est mort
	 * Un bateau est mort quand il n'a plus de PV
	 *
	 * @return boolean
	 */
    public boolean estMort(){
        return this.pv<=0;
    }

    /**
	 * Cette methode verifie si le bateau peut tirer
	 * Un bateau peut tirer si il a des munitions et si il est vivant
	 *
	 * @return boolean
	 */
    public boolean peutTirer(){
        return this.aMunitions() && !(this.estMort());
    }

    /**
	 * Cette methode precise que le bateau vient d'utiliser une munition
	 * Le nombre de munition diminue
	 *
	 * @return int, le nombre de munition restantes
	 */
    public int utiliserMunition(){
    	this.munitions--;
		return this.munitions;
	}

	/**
	 * Cette methode precise que le bateau vient de se faire toucher
	 * Le nombre de PV diminue
	 *
	 * @return int, le nombre de pv restants
	 */
	public int diminuerVie(){
		this.pv--;
		return this.pv;
	}

    //###############
	//#     GET     #
	//###############

	public SingletonEpoque getFactory() {
		return factory;
	}

	public int getId() {
		return id;
	}

	public int getMunitions() {
		return munitions;
	}

	public int getPv() {
		return pv;
	}

	public int getDirection() {
		return direction;
	}

	public Point getPosition() {
		return position;
	}

	public int getTaille() {
		return taille;
	}

	public ArrayList<Point> getZoneSup() {
		ArrayList<Point> res = new ArrayList<>(zoneSup.size());
		for(Point p : zoneSup)
			res.add(new Point(p));
		return res;
	}

	public ImageIcon getTexture(int i) throws WrongEpoqueException {
		return factory.getTexture(this).get(i);
	}

	//###############
	//#    SET      #
	//###############

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setPosition(Point position) {
		this.position = position;
	}


	//################
	//#  TOSTRING    #
	//################


	@Override
	public String toString() {
		return "Bateau id:"+this.id+" mun:"+munitions+" posX:"+position.x+" posY:"+position.y+" dir:"+direction+" vie:"+pv;
	}
}
