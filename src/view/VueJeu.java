package view;

import controller.TirerListener;
import model.Bateau;
import model.Jeu;
import model.Terrain;
import textureFactory.SingletonEpoque;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class VueJeu extends JPanel implements Observer  {

    //Ici on aurait la grille + des autres infos
    protected SingletonEpoque epoque;

    protected ArrayList<Bateau> listeBateaux;
    private Terrain terrainJoueur;
    private Terrain terrainAdversaire;

    private VueGrilleJoueur vueGrilleJoueur;
    private VueGrilleAdversaire vueGrilleAdversaire;


    public VueJeu(Terrain tj, Terrain ta, int taille) {
        this.terrainJoueur=tj;
        this.terrainAdversaire=ta;
        this.vueGrilleJoueur = new VueGrilleJoueur(this,terrainJoueur,taille,50);
        this.vueGrilleAdversaire = new VueGrilleAdversaire(this,terrainAdversaire,taille,50);
        this.add(this.vueGrilleJoueur);
        this.add(Box.createRigidArea(new Dimension(50,0)));
        this.add(this.vueGrilleAdversaire);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Utilise Terrain pour mettre à jour les autres infos
    }

    public void ajouterBateauxEtEpoque(SingletonEpoque se) {
        this.epoque=se;
        ArrayList<Bateau> bateauJoueur=this.epoque.generateFleet();
        ArrayList<Bateau> bateauAdvesaire=this.epoque.generateFleet();

        this.terrainJoueur=new Terrain();
        this.terrainAdversaire=new Terrain();

        Jeu jeu=Jeu.getInstance();

        jeu.setTerrainJ1(terrainJoueur);
        jeu.setTerrainJ2(terrainAdversaire);

        this.vueGrilleJoueur.effacerGrille();
        this.vueGrilleAdversaire.effacerGrille();

        this.vueGrilleJoueur.ajouterTerrain(this.terrainJoueur);
        this.vueGrilleAdversaire.ajouterTerrain(this.terrainAdversaire);

        this.vueGrilleJoueur.ajouterBateau(bateauJoueur);
        this.vueGrilleAdversaire.ajouterBateau(bateauAdvesaire);
    }

    public void ajouterTirerListener() {
        TirerListener tl=new TirerListener(vueGrilleJoueur,vueGrilleAdversaire,terrainJoueur,terrainAdversaire);
        vueGrilleJoueur.ajouterTirerListener(tl);
        vueGrilleAdversaire.ajouterTirerListener(tl);
    }

    //
    public static void setEnabled(Component component, boolean enabled) {
        component.setEnabled(enabled);
        if (component instanceof Container) {
            for (Component child : ((Container) component).getComponents()) {
                setEnabled(child, enabled);
            }
        }
    }
}
