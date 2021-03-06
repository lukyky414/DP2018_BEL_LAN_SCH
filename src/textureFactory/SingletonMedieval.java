package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SingletonMedieval extends SingletonEpoque {

	private static final String chemin="/img/Medieval/";

	private static SingletonMedieval instance = new SingletonMedieval();

	public static SingletonMedieval getInstance() {
		if (instance == null) {
			instance=new SingletonMedieval();
		}
		return instance;
	}

	private SingletonMedieval(){
		this.presentationEpoque=SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Medieval.png"))),SingletonEpoque.TAILLE_PRESENTATION_WIDTH,SingletonEpoque.TAILLE_PRESENTATION_HEIGHT);

		this.textures = new HashMap<Integer, ArrayList<ImageIcon>>();

		ArrayList<ImageIcon> list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T1-1.png"))),SingletonEpoque.TAILLE));

		this.textures.put(0,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T2-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T2-2.png"))),SingletonEpoque.TAILLE));

		this.textures.put(1,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-2.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-3.png"))),SingletonEpoque.TAILLE));

		//Les bateaux 2 et 3 auront la même texture
		this.textures.put(2,list_icon);
		this.textures.put(3,list_icon);

		list_icon= new ArrayList<ImageIcon>();
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-1.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-2.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-3.png"))),SingletonEpoque.TAILLE));
		list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T4-4.png"))),SingletonEpoque.TAILLE));

		this.textures.put(4,list_icon);
	}

	@Override
	public ArrayList<Bateau> generateFleet() {
		ArrayList<Bateau> list = new ArrayList<>();

		list.add(new Bateau(this,0, 1, 30, 1,null));
		list.add(new Bateau(this,1, 1, 50, 2,null));

		// xox
		ArrayList<Point> tmp = new ArrayList<>();
		tmp.add(new Point(0,1));
		tmp.add(new Point(0,-1));
		list.add(new Bateau(this,2, 3, 5, 3,tmp));

		tmp = new ArrayList<>();
		tmp.add(new Point(0,1));
		tmp.add(new Point(0,-1));
		list.add(new Bateau(this,3, 3, 5, 3,tmp));

		// xx o xx
		tmp = new ArrayList<>();
		tmp.add(new Point(0,2));
		tmp.add(new Point(0,-2));
		tmp.add(new Point(0,3));
		tmp.add(new Point(0,-3));
		list.add(new Bateau(this,4, 4, 3, 4,tmp));

		return list;
	}

	@Override
	public String toString() {
		return "Mediévale";
	}
}
