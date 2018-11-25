package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class SingletonEpoque {

	//### Partie Texture Factory ###
	public static int TAILLE=50;
	public static int TAILLE_PRESENTATION_WIDTH=850;
	public static int TAILLE_PRESENTATION_HEIGHT=650;

	public static ImageIcon redimensionner(ImageIcon imageIcon, int width, int height) {
		Image img = imageIcon.getImage() ;
		Image newimg = img.getScaledInstance( width, height,  java.awt.Image.SCALE_SMOOTH ) ;
		return new ImageIcon(newimg);
	}

	public static ImageIcon redimensionner(ImageIcon imageIcon, int taille) {
		return redimensionner(imageIcon,taille,taille);
	}

	protected HashMap<Integer, ArrayList<ImageIcon>> textures;
	protected ImageIcon presentationEpoque;

	/**
	 * Genere une nouvelle flotte de bateaux,
	 * ayant tous une taille ou une zone d'attaque propre a l'epoque qui la genere.
	 *
	 * @return liste de bateau, correspondant a la flotte
	 */
	public abstract ArrayList<Bateau> generateFleet();

	/**
	 * Fonction qui récupère un bateau. On a besoin de la taille du bateau pour aller chercher la liste de textures correspondantes
	 * @parap bateau
	 * @return liste
	 */
	public ArrayList<ImageIcon> getTexture(Bateau b) throws WrongEpoqueException {
		if(b.getFactory() == this) {
			return this.textures.get(b.getId());
		} else {
			throw new WrongEpoqueException("Ce bateau n'appartient pas a cette epoque.");
		}
	}

	/**
	 * Retourne l'image qui sera utilisé pour visualiser l'époque
	 *
	 * @return l'image de présentation de l'époque
	 */
	public ImageIcon getPresentationEpoque() {
		return presentationEpoque;
	}

}
