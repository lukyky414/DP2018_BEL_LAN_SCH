package textureFactory;



import model.Bateau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingletonContemporain extends SingletonEpoque {
	//####################
	//# Partie Singleton #
	//####################
	private static SingletonContemporain instance = new SingletonContemporain();
	private HashMap<Integer, ArrayList<ImageIcon>> textures;

	@Override
	public SingletonEpoque getInstance() {
		return instance;
	}

	//##################
	//# Partie Factory #
	//##################


	private SingletonContemporain(){
		this.textures = new HashMap<Integer, ArrayList<ImageIcon>>();
	}

	@Override
    public ArrayList<ImageIcon> getTexture(Bateau b) throws WrongEpoqueException {
		if(b.getFactory() == this)
			return textures.get(b.getTaille());
		throw new WrongEpoqueException("Ce bateau n'appartient pas a cette epoque.");
	}

	@Override
	public List<Bateau> generateFleet() {
		return new ArrayList<>();
	}
}

