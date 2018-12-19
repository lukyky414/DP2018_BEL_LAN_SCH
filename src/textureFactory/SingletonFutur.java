package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SingletonFutur extends SingletonEpoque {

    private static final String chemin="/img/Futur/";

    private static SingletonFutur instance = new SingletonFutur();

    public static SingletonFutur getInstance() {
        if (instance == null) {
            instance=new SingletonFutur();
        }
        return instance;
    }

    private SingletonFutur(){
        this.presentationEpoque=SingletonEpoque.redimensionner((new ImageIcon(getClass().getResource(chemin+"Future.jpg"))),SingletonEpoque.TAILLE_PRESENTATION_WIDTH,SingletonEpoque.TAILLE_PRESENTATION_HEIGHT);
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

        ArrayList<Point> zone1= new ArrayList<Point>();
        zone1.add(new Point(0,1));

        ArrayList<Point> zone2= new ArrayList<Point>();
        zone2.add(new Point(0,1));
        zone2.add(new Point(1,0));
        zone2.add(new Point(0,-1));
        zone2.add(new Point(-1,0));

        ArrayList<Point> zone3= new ArrayList<Point>();
        zone3.add(new Point(0,-1));
        zone3.add(new Point(0,1));
        zone3.add(new Point(0,2));

        ArrayList<Point> zone4= new ArrayList<Point>();
        zone4.add(new Point(-2,-1));
        zone4.add(new Point(-2,0));
        zone4.add(new Point(-2,1));

        zone4.add(new Point(2,-1));
        zone4.add(new Point(2,0));
        zone4.add(new Point(2,1));

        zone4.add(new Point(0,2));
        zone4.add(new Point(-1,2));
        zone4.add(new Point(1,2));
        zone4.add(new Point(-2,2));
        zone4.add(new Point(2,2));

        zone4.add(new Point(0,-2));
        zone4.add(new Point(1,-2));
        zone4.add(new Point(2,-2));
        zone4.add(new Point(-1,-2));
        zone4.add(new Point(-2,-2));

        zone4.add(new Point(0,-3));
        zone4.add(new Point(0,-4));
        zone4.add(new Point(1,-5));
        zone4.add(new Point(-1,-5));

        ArrayList<Bateau> list = new ArrayList<>();
        list.add(new Bateau(this,0,1, 100, 2, null));
        list.add(new Bateau(this,1, 2, 10, 2,zone1));
        list.add(new Bateau(this,2, 3, 4, 3,zone2));
        list.add(new Bateau(this,3, 4, 4, 4,zone3));
        list.add(new Bateau(this,4, 1, 1, 5,zone4));
        return list;
    }

    @Override
    public String toString() {
        return "Futuriste";
    }
}
