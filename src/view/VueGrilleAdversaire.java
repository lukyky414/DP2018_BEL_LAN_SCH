package view;

import model.Terrain;

import java.util.Observable;

public class VueGrilleAdversaire extends VueGrille {

    public VueGrilleAdversaire(Terrain t, int s, int tb) {
        super(t,s,tb);
        panelBoutonsBateaux.setVisible(false);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
