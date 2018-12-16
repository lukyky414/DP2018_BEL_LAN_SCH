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
        updateTir();
    }

    @Override
    public void ajouterBateau(ArrayList<Bateau> listeBateaux) {
        this.listeBateaux=listeBateaux;
        this.placementListener=null;

        this.listeBateaux.get(0).setPv(1);
        //TODO effacer test
        Coup c=new Coup(new Point(0,0),this.listeBateaux.get(0));
        this.terrain.placer(c);
    }
}
