package view;

import controller.PlacementListener;
import model.Bateau;
import model.ChampTir;
import model.Terrain;
import textureFactory.WrongEpoqueException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class VueGrilleJoueur extends VueGrille {

    public VueGrilleJoueur(VueJeu vj, Terrain t, int s, int tb) {
        super(vj,t,s,tb);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.terrain != null) {
            effacerGrille();
            ArrayList<Bateau> list=this.terrain.getBateaux();
            for (int i=0;i<list.size();i++) {
                Bateau b=list.get(i);
                Point p=b.getPosition();
                //System.out.println(p);
                int x=(int)p.getX()+1;
                int y=(int)p.getY()+1;
                int direction=b.getDirection();
                int taille=b.getTaille();

                bateauDansUneDirection(x,y,direction,b,false);
                couleurDansUneDirection(x,y,direction,taille,VueGrille.colorEmpty,false);
                updateTir();
            }
        }
    }

    @Override
    public void ajouterBateau(ArrayList<Bateau> listeBateaux) {
        super.ajouterBateau(listeBateaux);

        this.placementListener=new PlacementListener(this.vj,this,terrain,listeBateaux,tableauBoutonsBateaux);

        //On met à jour le Placement Listener
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                grid[i][j].addMouseListener(this.placementListener);
                grid[i][j].addMouseWheelListener(this.placementListener);
            }
        }

        for (int i=0;i<tableauBoutonsBateaux.length;i++) {
            tableauBoutonsBateaux[i].addActionListener(this.placementListener);
            tableauBoutonsBateaux[i].setEnabled(true);
        }
    }
}
