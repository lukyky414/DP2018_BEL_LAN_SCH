import model.Terrain;
import view.VueJeu;

import javax.swing.*;

public class Main {

    public static void main(String args[]) {
        JFrame jf=new JFrame("Bataille Navale");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Null pour l'instant
        Terrain terrain=null;
        VueJeu vj=new VueJeu(null, null, 10);
        jf.add(vj);

        jf.pack();
        jf.setVisible(true);
    }
}
