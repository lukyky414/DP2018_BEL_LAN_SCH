package controller;

import model.Bateau;
import model.Coup;
import model.Terrain;
import view.CustomJButton;
import view.VueGrille;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class PlacementListener implements MouseListener, MouseWheelListener {

    private Terrain terrain;
    private VueGrille vueGrille;

    private ArrayList<Bateau> listeBateaux=new ArrayList<Bateau>();
    private Bateau bateauEnCours;

    private Coup coup;
    private int x;
    private int y;

    private CustomJButton[][] grid;

    public PlacementListener(VueGrille vg, CustomJButton[][] grid, Terrain t, ArrayList<Bateau> listeBateaux) {
        this.vueGrille=vg;
        this.grid = grid;
        this.terrain = t;
        this.listeBateaux=listeBateaux;
        this.bateauEnCours = listeBateaux.get(0);
        this.x=0;
        this.y=0;
        this.coup = new Coup(new Point(0,0), bateauEnCours);
    }

    public void setBateauEnCours(Bateau bateauEnCours) {
        this.bateauEnCours = bateauEnCours;
        this.coup.setBateau(bateauEnCours);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (bateauEnCours == null) {
            return;
        }
        setXYFromMouseEvent(e);
        if (e.getButton() == 3) {
            changerRotation(1);
        } else {
            if (terrain.verificationPlacer(coup)) {
                terrain.placer(coup);
                this.listeBateaux.remove(bateauEnCours);
                if (this.listeBateaux.size() > 0) {
                    setBateauEnCours(this.listeBateaux.get(0));
                } else {
                    setBateauEnCours(null);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (bateauEnCours == null) {
            return;
        }
        setXYFromMouseEvent(e);
        vueGrille.afficherBateau(this.x,this.y,bateauEnCours,this.coup);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (bateauEnCours == null) {
            return;
        }
        setXYFromMouseEvent(e);
        vueGrille.effacerBateau(this.x, this.y, bateauEnCours);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (bateauEnCours == null) {
            return;
        }
        changerRotation(e.getWheelRotation());
    }

    //rotation 1 ou -1
    private void changerRotation(int rotation) {
        if (bateauEnCours == null) {
            return;
        }
        int newdirection=(bateauEnCours.getDirection()+rotation+4)%4;
        vueGrille.effacerBateau(this.x, this.y, bateauEnCours);
        this.bateauEnCours.setDirection(newdirection);
        vueGrille.afficherBateau(this.x,this.y,bateauEnCours,this.coup);
    }

    //Permet d'avoir un seul listener
    private void setXYFromMouseEvent(MouseEvent e) {
        CustomJButton button=(CustomJButton)(e.getSource());
        this.x=button.getSpecialX();
        this.y=button.getSpecialY();
        this.coup.setXY(x-1,y-1);
    }

}
