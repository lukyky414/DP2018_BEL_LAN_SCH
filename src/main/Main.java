package main;

import model.Bateau;
import model.Jeu;
import model.Terrain;
import textureFactory.SingletonEpoque;
import view.VueJeu;
import view.VueMenuBar;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        Terrain terrain=new Terrain();
        Terrain terrain2=new Terrain();

        Jeu jeu=Jeu.getInstance();

        jeu.setTerrainJ1(terrain);
        jeu.setTerrainJ2(terrain);

        VueJeu vj=new VueJeu(terrain, terrain2, 10);
        VueMenuBar vmb=new VueMenuBar(jf,vj);
        jf.setJMenuBar(vmb);
        jf.add(vj);

        VueJeu.setEnabled(vj,false);

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
