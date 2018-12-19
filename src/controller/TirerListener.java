package controller;

import model.Bateau;
import model.Coup;
import model.Disposition;
import model.Terrain;
import view.CustomJButton;
import view.VueGrille;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TirerListener implements MouseListener {

    private Terrain terrainJoueur;
    private Terrain terrainAdverse;

    private VueGrille vueGrilleJoueur;
    private VueGrille vueGrilleAdverse;

    private ArrayList<Bateau> listeBateauxJoueur;

    private Coup coup;
    private int x;
    private int y;
    private boolean terrainAdverseSelectionne;

    public TirerListener(VueGrille vueGrilleJoueur, VueGrille vueGrilleAdverse, Terrain terrainJoueur, Terrain terrainAdverse) {
        this.terrainAdverseSelectionne=false;
        this.vueGrilleJoueur=vueGrilleJoueur;
        this.vueGrilleAdverse=vueGrilleAdverse;
        this.terrainJoueur=terrainJoueur;
        this.terrainAdverse=terrainAdverse;
        this.listeBateauxJoueur=terrainJoueur.getBateaux();
        vueGrilleJoueur.setBateauSelectionne(listeBateauxJoueur.get(0));
        this.x=0;
        this.y=0;
        this.coup = new Coup(new Point(0,0), vueGrilleJoueur.getBateauSelectionne());
        vueGrilleJoueur.afficherBordJButtonBateauDansUneDirection(vueGrilleJoueur.getBateauSelectionne(),Color.yellow);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setXYFromMouseEvent(e);
        //Si on clique chez l'adversaire
        if (this.terrainAdverseSelectionne) {
            if (vueGrilleJoueur.getBateauSelectionne() == null) {
                return;
            } else {
                if (terrainAdverse.verificationTirer(this.coup)) {
                    terrainAdverse.tirer(this.coup);
                }
            }
            //Si on clique sur notre grille
        } else {
            Disposition dispoJoueur=terrainJoueur.getDisposition();
            Bateau bateauclique=dispoJoueur.get(this.coup.getPos());
            if (bateauclique != null) {
                if (vueGrilleJoueur.getBateauSelectionne() != null) {
                    vueGrilleJoueur.nettoyerBordJButtonBateauDansUneDirection(vueGrilleJoueur.getBateauSelectionne());
                }
                //System.out.println("SELECTION");
                vueGrilleJoueur.setBateauSelectionne(bateauclique);
                this.coup.setBateau(vueGrilleJoueur.getBateauSelectionne());
                vueGrilleJoueur.afficherBordJButtonBateauDansUneDirection(vueGrilleJoueur.getBateauSelectionne(),Color.yellow);
                Bateau b=this.vueGrilleJoueur.getBateauSelectionne();
                if (b != null) {
                    vueGrilleJoueur.getJtVie().setText(""+b.getPv());
                    vueGrilleJoueur.getJtMunition().setText(""+b.getMunitions());
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setXYFromMouseEvent(e);
        //Si on clique chez l'adversaire
        if (this.terrainAdverseSelectionne) {
            if (vueGrilleJoueur.getBateauSelectionne() == null) {
                return;
            } else {
                if (terrainAdverse.verificationTirer(this.coup)) {
                    vueGrilleAdverse.afficherCouleurTirJButton(this.x,this.y,VueGrille.green,vueGrilleJoueur.getBateauSelectionne(),true);
                } else {
                    vueGrilleAdverse.afficherCouleurTirJButton(this.x,this.y,VueGrille.red,vueGrilleJoueur.getBateauSelectionne(),true);
                }
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setXYFromMouseEvent(e);
        //Si on clique chez l'adversaire
        if (this.terrainAdverseSelectionne) {
            if (vueGrilleJoueur.getBateauSelectionne() == null) {
                return;
            } else {
                vueGrilleAdverse.resetCouleurTirJButton(this.x,this.y,vueGrilleJoueur.getBateauSelectionne(),true);
            }
        }
    }

    //Permet d'avoir un seul listener
    private void setXYFromMouseEvent(MouseEvent e) {
        CustomJButton button=(CustomJButton)(e.getSource());
        if (vueGrilleJoueur.appartienA(button)) {
            this.terrainAdverseSelectionne=false;
        } else {
            this.terrainAdverseSelectionne=true;
        }
        this.x=button.getSpecialX();
        this.y=button.getSpecialY();
        this.coup.setXY(x-1,y-1);
    }
}
