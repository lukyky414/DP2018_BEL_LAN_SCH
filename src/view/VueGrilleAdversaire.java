package view;

import controller.PlacementListener;
import model.*;
import textureFactory.SingletonMedieval;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class VueGrilleAdversaire extends VueGrille {

    public VueGrilleAdversaire(VueJeu vj, Terrain t, int s, int tb) {
        super(vj,t,s,tb);
        panelBoutonsBateaux.setVisible(false);
        panelInfo.setVisible(false);
    }

    @Override
    public void update(Observable o, Object arg) {
        effacerGrille();
        ArrayList<Bateau> list=this.terrain.getBateaux();
        for (Bateau b : list) {
            if (b.estMort()) {
                Point p=b.getPosition();
                int x=(int)p.getX()+1;
                int y=(int)p.getY()+1;
                int direction=b.getDirection();
                bateauDansUneDirection(x,y,direction,b,false);
            }
        }
        updateTir();
    }

    @Override
    public void ajouterBateau(ArrayList<Bateau> listeBateaux) {
        super.ajouterBateau(listeBateaux);
    }
}
