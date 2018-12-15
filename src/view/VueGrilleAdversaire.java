package view;

import controller.PlacementListener;
import model.Bateau;
import model.Terrain;

import java.util.ArrayList;
import java.util.Observable;

public class VueGrilleAdversaire extends VueGrille {

    public VueGrilleAdversaire(Terrain t, int s, int tb) {
        super(t,s,tb);
        panelBoutonsBateaux.setVisible(false);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void ajouterBateau(ArrayList<Bateau> listeBateaux) {
        this.listeBateaux=listeBateaux;
        this.placementListener=null;
    }
}
