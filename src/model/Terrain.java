package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class Terrain extends Observable {

    private ArrayList<Bateau> bateaux;

    private Disposition disposition;
    private ChampTir champTir;
    private int taille;

    public Terrain() {
        this.taille = 10;
        this.disposition = new Disposition(this);
        this.champTir = new ChampTir();
        this.bateaux = new ArrayList<Bateau>();
    }


    public void ajouterBateau(Bateau b) {
        this.bateaux.add(b);
    }

    public ArrayList<Bateau> getBateaux() {
        return bateaux;
    }

    //Si une  case de la zone n'a pas été touché, on peut tirer.
    public boolean verificationTirer(Coup c) {
        Bateau b=c.getBateau();
        Point ptCentral=c.getPos();
        if (!b.peutTirer()) {
            return false;
        } else {
            if (!champTir.estTouche(ptCentral)) {
                return true;
            }
            ArrayList<Point>alp=b.getZoneSup();
            for (int i=0;i<alp.size();i++) {
                Point ptDecalage=alp.get(i);
                Point ptNouveau=new Point((int)(ptCentral.getX()+ptDecalage.getX()),(int)(ptCentral.getY()+ptDecalage.getY()));
                if (!champTir.estTouche(ptNouveau)) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean verificationPlacer(Coup c) {
        return this.disposition.peutEtrePlace(c);
    }

    public boolean placer(Coup c) {
        if (this.disposition.placer(c)) {
            bateaux.add(c.getBateau());
            setChanged();
            notifyObservers();
            return true;
        } else {
            return false;
        }
    }


    public void destroyShip(Bateau b) {
        Point p=b.getPosition();
        int x=(int)(p.getX());
        int y=(int)(p.getY());
        int direction=b.getDirection();
        int taille=b.getTaille();

        ChampTir ct=this.getChampTir();
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < taille; i++) {
                    Point ptBateau=new Point(x-i,y);
                    ct.touche(ptBateau);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < taille; i++) {
                    Point ptBateau=new Point(x+i,y);
                    ct.touche(ptBateau);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < taille; i++) {
                    Point ptBateau=new Point(x,y-i);
                    ct.touche(ptBateau);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < taille; i++) {
                    Point ptBateau=new Point(x,y+i);
                    ct.touche(ptBateau);
                }
                break;
        }
    }

    public boolean tirer(Coup c){
        Point ptCentral=c.getPos();
		Bateau b=c.getBateau();
		if (b.estMort() || !b.aMunitions()) {
			return false;
		} else {
			if (!champTir.estTouche(c.getPos())) {
				this.tirer(c.getPos());
				b.utiliserMunition();
			}

			ArrayList<Point>alp=b.getZoneSup();
			for (int i=0;i<alp.size();i++) {
                Point ptDecalage=alp.get(i);
                Point ptNouveau=new Point((int)(ptCentral.getX()+ptDecalage.getX()),(int)(ptCentral.getY()+ptDecalage.getY()));
				if (!champTir.estTouche(ptNouveau)) {
					this.tirer(ptNouveau);
                    setChanged();
                    notifyObservers();
				}
			}
			return true;
		}
	}

    public boolean tirer(Point pos){
    	if(champTir.estTouche(pos))
    		return false;
    	champTir.touche(pos);
    	Bateau b = disposition.get(pos);
    	if(b != null) {
            b.diminuerVie();
            if (b.estMort()) {
                this.destroyShip(b);
            }
        }

        setChanged();
        notifyObservers();
    	return true;
	}

    public ChampTir getChampTir() {
        return champTir;
    }

    public Disposition getDisposition() {
        return disposition;
    }
}
