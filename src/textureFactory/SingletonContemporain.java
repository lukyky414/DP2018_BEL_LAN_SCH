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
	private static final String chemin="/img/Contemporain/";

	private static SingletonContemporain instance = new SingletonContemporain();

	public static SingletonContemporain getInstance() {
		if (instance == null) {
			instance=new SingletonContemporain();
		}
		return instance;
	}
	//##################
	//# Partie Factory #
	//##################


	private SingletonContemporain(){
		this.presentationEpoque=SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Contemporain.jpg"))),SingletonEpoque.TAILLE_PRESENTATION_WIDTH,SingletonEpoque.TAILLE_PRESENTATION_HEIGHT);
		this.textures = new HashMap<Integer, ArrayList<ImageIcon>>();
		ArrayList<ImageIcon> list_icon= new ArrayList<ImageIcon>();

		//Les getClass().getResource() permettent d'avoir un jar standalone
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T2-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T2-2.png"))),SingletonEpoque.TAILLE));

		this.textures.put(0,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-2.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-3.png"))),SingletonEpoque.TAILLE));

		this.textures.put(1,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T3-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T3-2.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T3-3.png"))),SingletonEpoque.TAILLE));

		this.textures.put(2,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-2.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-3.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-4.png"))),SingletonEpoque.TAILLE));

		this.textures.put(3,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T5-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T5-2.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T5-3.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T5-4.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T5-5.png"))),SingletonEpoque.TAILLE));

		this.textures.put(4,list_icon);
	}

	@Override
	public ArrayList<Bateau> generateFleet() {
		ArrayList<Bateau> list = new ArrayList<>();
		list.add(new Bateau(this,0,2,null));
		list.add(new Bateau(this,1,3,null));
		list.add(new Bateau(this,2,3,null));
		list.add(new Bateau(this,3,4,null));
		list.add(new Bateau(this,4,5,null));
		return list;
	}

	@Override
	public String toString() {
		return "Contemporaine";
	}
}

