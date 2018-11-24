import model.Bateau;
import model.Coup;
import model.Disposition;
import model.Terrain;
import view.VueJeu;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String args[]) {
        //On utilise le rendu par défaut
        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        JFrame jf=new JFrame("Bataille Navale");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Null pour l'instant
        Terrain terrain=new Terrain(10);
        VueJeu vj=new VueJeu(terrain, null, 10);
        jf.add(vj);

        jf.pack();
        jf.setVisible(true);
        /*
        Disposition d=new Disposition(10);
        Bateau b= new Bateau(null,2,null);
        b.setDirection(Bateau.DROITE);
        Point p=new Point(0,1);
        Coup c=new Coup(p,b);
        System.out.println(d.peutEtrePlace(c));*/
    }
}
