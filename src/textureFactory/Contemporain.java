package textureFactory;

import model.Bateau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SingletonContemporain extends SingletonEpoque {
	//####################
	//# Partie Singleton #
	//####################
	private static SingletonContemporain instance = new SingletonContemporain();

	@Override
	SingletonEpoque getInstance() {
		return instance;
	}

	//##################
	//# Partie Factory #
	//##################
	private HashMap<Integer, Texture> textures;

	private SingletonContemporain(){
		this.textures = new HashMap<>();
	}

	@Override
    public Texture getTexture(Bateau b) throws WrongEpoqueException {
		if(b.getFactory() == this)
			return textures.get(b.getId());
		throw new WrongEpoqueException("Ce bateau n'appartient pas a cette epoque.");
	}

	@Override
	public List<Bateau> generateFleet() {
		return new ArrayList<>();
	}
}

