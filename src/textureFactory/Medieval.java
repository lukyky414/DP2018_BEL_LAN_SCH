package textureFactory;

import model.Bateau;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Medieval extends SingletonEpoque {

	//####################
	//# Partie Singleton #
	//####################
	private static Medieval instance = new Medieval();

	@Override
	public SingletonEpoque getInstance() {
		return instance;
	}

	//##################
	//# Partie Factory #
	//##################
	//private HashMap<Integer, Texture> textures;

	private Medieval(){
		/*this.textures = new HashMap<>();*/
	}

	/*@Override
	public Texture getTexture(Bateau b) throws WrongEpoqueException {
		if(b.getFactory() == this)
			return textures.get(b.getId());
		throw new WrongEpoqueException("Ce bateau n'appartient pas a cette epoque.");
	}*/

	@Override
	public List<Bateau> generateFleet() {
		return new ArrayList<>();
	}
}
