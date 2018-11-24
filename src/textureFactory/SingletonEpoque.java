package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SingletonEpoque {

	//### Partie Texture Factory ###
	public static int TAILLE=50;

	public static ImageIcon redimensionner(ImageIcon imageIcon, int taille) {
		Image img = imageIcon.getImage() ;
		Image newimg = img.getScaledInstance( taille, taille,  java.awt.Image.SCALE_SMOOTH ) ;
		return new ImageIcon(newimg);
	}

	/**
	 * On demande une texture d'un bateau.
	 *
	 * @return la texture du bateau
	 */
	public abstract ArrayList<ImageIcon> getTexture(Bateau b) throws WrongEpoqueException;

	/**
	 * Genere une nouvelle flotte de bateaux,
	 * ayant tous une taille ou une zone d'attaque propre a l'epoque qui la genere.
	 *
	 * @return liste de bateau, correspondant a la flotte
	 */
	public abstract ArrayList<Bateau> generateFleet();

}
