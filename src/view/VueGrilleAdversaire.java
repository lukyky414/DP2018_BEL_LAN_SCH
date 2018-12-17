package view;

import controller.PlacementListener;
import model.Bateau;
import model.ChampTir;
import model.Coup;
import model.Terrain;
import textureFactory.SingletonMedieval;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class VueGrilleAdversaire extends VueGrille {

    public VueGrilleAdversaire(VueJeu vj, Terrain t, int s, int tb) {
        super(vj,t,s,tb);
        panelBoutonsBateaux.setVisible(false);
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

        //TODO effacer test
        this.listeBateaux.get(0).setPv(1);
        Coup c=new Coup(new Point(0,0),this.listeBateaux.get(0));
        this.terrain.placer(c);
    }
}
