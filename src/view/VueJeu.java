package view;

import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class VueJeu extends JPanel implements Observer  {

    //Ici on aurait la grille + des autres infos

    private Terrain terrainJoueur;
    private Terrain terrainAdversaire;

    private VueGrilleJoueur vueGrilleJoueur;
    private VueGrilleAdversaire vueGrilleAdversaire;

    public VueJeu(Terrain tj, Terrain ta, int taille) {
        this.terrainJoueur=tj;
        this.terrainAdversaire=ta;
        this.vueGrilleJoueur = new VueGrilleJoueur(terrainJoueur,taille,50);
        this.vueGrilleAdversaire = new VueGrilleAdversaire(terrainAdversaire,taille,50);
        this.add(this.vueGrilleJoueur);
        this.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(this.vueGrilleAdversaire);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Utilise Terrain pour mettre à jour les autres infos
    }
}
