package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;


public class SingletonStarWars extends SingletonEpoque {

    private static final String chemin="/img/StarWars/";

    private static SingletonStarWars instance = new SingletonStarWars();

    public static SingletonStarWars getInstance() {
        if (instance == null) {
            instance=new SingletonStarWars();
        }
        return instance;
    }

    private SingletonStarWars(){
        this.presentationEpoque=SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"StarWars.png"))),SingletonEpoque.TAILLE_PRESENTATION_WIDTH,SingletonEpoque.TAILLE_PRESENTATION_HEIGHT);

        this.textures = new HashMap<Integer, ArrayList<ImageIcon>>();

        ArrayList<ImageIcon> list_icon= new ArrayList<ImageIcon>();
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T2-1.png"))),SingletonEpoque.TAILLE));
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T2-2.png"))),SingletonEpoque.TAILLE));

        this.textures.put(0,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T2-1.png"))),SingletonEpoque.TAILLE));
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T2-2.png"))),SingletonEpoque.TAILLE));

        this.textures.put(1,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-1.png"))),SingletonEpoque.TAILLE));
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-2.png"))),SingletonEpoque.TAILLE));
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau T3-3.png"))),SingletonEpoque.TAILLE));

        this.textures.put(2,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T3-1.png"))),SingletonEpoque.TAILLE));
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T3-2.png"))),SingletonEpoque.TAILLE));
        list_icon.add(SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Bateau 2T3-3.png"))),SingletonEpoque.TAILLE));


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
		ArrayList<Point> tmp;

		/*
		* x   x
		*  x x
		*   o
		*  x x
		* x   x
		*/
		tmp = new ArrayList<>();
		tmp.add(new Point(1,1));
		tmp.add(new Point(2,2));
		tmp.add(new Point(-1,-1));
		tmp.add(new Point(-2,-2));
		tmp.add(new Point(1,-1));
		tmp.add(new Point(2,-2));
		tmp.add(new Point(-1,1));
		tmp.add(new Point(-2,2));
        list.add(new Bateau(this,0, 1, 2, 1,tmp));


        /*
        *   x
        *   x
        * xxoxx
        *   x
        *   x
        */
		tmp = new ArrayList<>();
		tmp.add(new Point(0,1));
		tmp.add(new Point(0,2));
		tmp.add(new Point(0,-1));
		tmp.add(new Point(0,-2));
		tmp.add(new Point(1,0));
		tmp.add(new Point(2,0));
		tmp.add(new Point(-1,0));
		tmp.add(new Point(-2,0));
        list.add(new Bateau(this,1, 1, 1, 1,tmp));


        /*
        * x  x  x
        *  x x x
        *   xxx
        * xxxoxxx
        *   xxx
        *  x x x
        * x  x  x
        */
		tmp = new ArrayList<>();
		tmp.add(new Point(1,1));
		tmp.add(new Point(2,2));
		tmp.add(new Point(3,3));
		tmp.add(new Point(-1,-1));
		tmp.add(new Point(-2,-2));
		tmp.add(new Point(-3,-3));
		tmp.add(new Point(1,-1));
		tmp.add(new Point(2,-2));
		tmp.add(new Point(3,-3));
		tmp.add(new Point(-1,1));
		tmp.add(new Point(-2,2));
		tmp.add(new Point(-3,3));


		tmp.add(new Point(0,1));
		tmp.add(new Point(0,2));
		tmp.add(new Point(0,3));
		tmp.add(new Point(0,-1));
		tmp.add(new Point(0,-2));
		tmp.add(new Point(0,-3));
		tmp.add(new Point(1,0));
		tmp.add(new Point(2,0));
		tmp.add(new Point(3,0));
		tmp.add(new Point(-1,0));
		tmp.add(new Point(-2,0));
		tmp.add(new Point(-3,0));
        list.add(new Bateau(this,2, 1, 2, 1,tmp));

		tmp = new ArrayList<>();
        list.add(new Bateau(this,3, 2, 1, 2,tmp));


        //xxxxxxxxxoxxxxxxxxx
		tmp = new ArrayList<>();
		tmp.add(new Point(0,1));
		tmp.add(new Point(0,2));
		tmp.add(new Point(0,3));
		tmp.add(new Point(0,4));
		tmp.add(new Point(0,5));
		tmp.add(new Point(0,6));
		tmp.add(new Point(0,7));
		tmp.add(new Point(0,8));
		tmp.add(new Point(0,9));
		tmp.add(new Point(0,-1));
		tmp.add(new Point(0,-2));
		tmp.add(new Point(0,-3));
		tmp.add(new Point(0,-4));
		tmp.add(new Point(0,-5));
		tmp.add(new Point(0,-6));
		tmp.add(new Point(0,-7));
		tmp.add(new Point(0,-8));
		tmp.add(new Point(0,-9));
        list.add(new Bateau(this,4, 3, 1, 3,tmp));
        return list;
    }

    @Override
    public String toString() {
        return "StarWars";
    }
}
