package textureFactory;

import model.Bateau;

import java.util.List;

public abstract class SingletonEpoque {
	//### Partie Singleton ###
	/**
	 * Les epoques sont des singletons, donc il nous faut un getInstance.
	 *
	 * @return l'instance de la textureFactory
	 */
	public abstract SingletonEpoque getInstance();


	//### Partie Texture Factory ###

	/**
	 * On demande une texture d'un bateau.
	 *
	 * @return la texture du bateau
	 */
	public abstract Texture getTexture(Bateau b) throws WrongEpoqueException;

	/**
	 * Genere une nouvelle flotte de bateaux,
	 * ayant tous une taille ou une zone d'attaque propre a l'epoque qui la genere.
	 *
	 * @return liste de bateau, correspondant a la flotte
	 */
	public abstract List<Bateau> generateFleet();
}
