package textureFactory;

import model.Bateau;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SingletonFutur extends SingletonEpoque {

    private static final String chemin="img/Futur/";

    private static SingletonFutur instance = new SingletonFutur();
    private HashMap<Integer, ArrayList<ImageIcon>> textures;

    public static SingletonFutur getInstance() {
        if (instance == null) {
            instance=new SingletonFutur();
        }
        return instance;
    }

    private SingletonFutur(){
        this.textures = new HashMap<Integer, ArrayList<ImageIcon>>();

        ArrayList<ImageIcon> list_icon= new ArrayList<ImageIcon>();
        list_icon.add(new ImageIcon(chemin+"Bateau T2-1.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T2-2.png"));

        this.textures.put(0,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(new ImageIcon(chemin+"Bateau 2T2-1.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau 2T2-2.png"));

        this.textures.put(1,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(new ImageIcon(chemin+"Bateau T3-1.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T3-2.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T3-3.png"));

        this.textures.put(2,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(new ImageIcon(chemin+"Bateau T4-1.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T4-2.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T4-3.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T4-4.png"));


        this.textures.put(3,list_icon);

        list_icon= new ArrayList<ImageIcon>();
        list_icon.add(new ImageIcon(chemin+"Bateau T5-1.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T5-2.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T5-3.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T5-4.png"));
        list_icon.add(new ImageIcon(chemin+"Bateau T5-5.png"));

        this.textures.put(4,list_icon);
    }


    /**
     * Fonction qui récupère un bateau. On a besoin de la taille du bateau pour aller chercher la liste de textures correspondantes
     * @parap bateau
     * @return liste
     */
    @Override
    public ArrayList<ImageIcon> getTexture(Bateau b) throws WrongEpoqueException {
        if(b.getFactory() == this) {
            return this.textures.get(b.getId());
        } else {
            throw new WrongEpoqueException("Ce bateau n'appartient pas a cette epoque.");
        }
    }

    @Override
    public ArrayList<Bateau> generateFleet() {
        ArrayList<Bateau> list = new ArrayList<>();
        int id=0;
        Bateau b0=new Bateau(this,id,2,null);
        id=1;
        Bateau b1=new Bateau(this,id,2,null);
        id=2;
        Bateau b2=new Bateau(this,id,3,null);
        id=3;
        Bateau b3=new Bateau(this,id,4,null);
        id=4;
        Bateau b4=new Bateau(this,id,5,null);
        return list;
    }

    @Override
    public String toString() {
        return "Futuriste";
    }
}
