package main;

import model.Coup;
import model.IA;
import model.Jeu;
import model.Terrain;

public class RMI {
	public final static int LOCAL=0, HOST=1, JOIN=2;
	public static int ETAT=LOCAL;

	public static void connect(){
		switch(ETAT){
			case LOCAL:
				return;
			case HOST:
				return;
			case JOIN:
				return;
		}
	}

	public static void setPlacement(Terrain terrain){
		switch(ETAT){
			case LOCAL:
				IA.jeu.setTerrainJ2(terrain);
				break;
			case HOST:
				break;
			case JOIN:
				break;
		}
	}

	public static Terrain getPlacement(){
		switch(ETAT){
			case LOCAL:
				IA.jeu.setSe(Jeu.getInstance().getEpoque());
				return IA.placerBateaux();
			case HOST:
				break;
			case JOIN:
				break;
		}
		return null;
	}

	public static void setTir(Coup c){
		switch(ETAT){
			case LOCAL:
				IA.jeu.getTerrainJ1().tirer(c);
				break;
			case HOST:
				break;
			case JOIN:
				break;
		}
	}

	public static Coup getTir(){
		switch(ETAT){
			case LOCAL:
				return IA.tirer();
			case HOST:
				break;
			case JOIN:
				break;
		}
		return null;
	}
}
