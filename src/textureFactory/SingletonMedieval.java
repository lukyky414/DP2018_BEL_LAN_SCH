package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SingletonMedieval extends SingletonEpoque {

	private static SingletonMedieval instance = new SingletonMedieval();
	private HashMap<Integer, ArrayList<ImageIcon>> textures;


	@Override
	public SingletonEpoque getInstance() {
		return instance;
	}

	private SingletonMedieval(){
		this.textures = new HashMap<Integer, ArrayList<ImageIcon>>();
	}


	/**
	 * Fonction qui récupère un bateau. On a besoin de la taille du bateau pour aller chercher la liste de textures correspondantes
	 * @parap bateau
	 * @return liste
	 */
	@Override
	public ArrayList<ImageIcon> getTexture(Bateau b) throws WrongEpoqueException {
		if(b.getFactory() == this)
			return textures.get(b.getTaille());
		throw new WrongEpoqueException("Ce bateau ne fait pas partie de cette époque.");
	}

	@Override
	public List<Bateau> generateFleet() {
		return new ArrayList<>();
	}
}
