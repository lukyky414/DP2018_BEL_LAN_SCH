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
        if (b.estMort() || b.aMunitions() == false) {
            return false;
        } else {
            ArrayList<Point>alp=b.getZoneSup();
            for (int i=0;i<alp.size();i++) {
                Point p=alp.get(i);
                if (champTir.estTouche(p) == false) {
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
            setChanged();
            notifyObservers();
            return true;
        } else {
            return false;
        }

    }

    public void placerBateaux() {

    }

    public void destroyShip(Bateau b) {

    }

    public boolean Tirer(Coup c){
		Bateau b=c.getBateau();
		if (b.estMort() || !b.aMunitions()) {
			return false;
		} else {
			if (!champTir.estTouche(c.getPos())) {
				this.Tirer(c.getPos());
				b.utiliserMunition();
			}
			else return false;//Case deja touchee

			ArrayList<Point>alp=b.getZoneSup();
			for (int i=0;i<alp.size();i++) {
				Point p=alp.get(i);
				if (!champTir.estTouche(p)) {
					this.Tirer(p);
				}
			}
			return false;
		}
	}

    public boolean Tirer(Point pos){
    	if(champTir.estTouche(pos))
    		return false;
    	champTir.touche(pos);
    	Bateau b = disposition.get(pos);
    	if(b != null)
    		b.diminuerVie();
    	return true;
	}

    public ChampTir getChampTir() {
        return champTir;
    }

    public Disposition getDisposition() {
        return disposition;
    }
}
