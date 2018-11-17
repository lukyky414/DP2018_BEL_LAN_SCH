package view;

import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueJoueur extends JPanel implements Observer  {

    //Ici on aurait la grille + des autres infos

    private Terrain terrain;
    private Grille grille;

    public VueJoueur(Terrain terrain,int taille) {
        this.terrain=terrain;
        this.grille=new Grille(terrain,taille);
        this.add(this.grille);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Utilise Terrain pour mettre à jour les autres infos
    }
}
