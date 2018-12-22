package controller;

import main.RMI;
import model.Bateau;
import model.Coup;
import model.Jeu;
import model.Terrain;
import view.CustomJButton;
import view.VueGrille;
import view.VueJeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PlacementListener implements MouseListener, MouseWheelListener, ActionListener {

    private VueJeu vj;

    private Terrain terrain;
    private VueGrille vueGrille;
    private JButton[] tableauBoutonsBateaux;

    private ArrayList<Bateau> listeBateaux=new ArrayList<Bateau>();
    private Bateau bateauEnCours;

    private Coup coup;
    private int x;
    private int y;

    public PlacementListener(VueJeu vj, VueGrille vg, Terrain t, ArrayList<Bateau> listeBateaux, JButton[] tableauBoutonsBateaux) {
    	RMI.connect();
        this.vj=vj;
        this.vueGrille=vg;
        this.tableauBoutonsBateaux=tableauBoutonsBateaux;
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

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (bateauEnCours == null) {
            return;
        }
        setXYFromMouseEvent(e);
        if (e.getButton() == 3) {
            changerRotation(1);
        } else {
            if (terrain.verificationPlacer(coup)) {
                terrain.placer(coup);
                int id=bateauEnCours.getId();
                this.tableauBoutonsBateaux[id].setEnabled(false);
                this.listeBateaux.remove(bateauEnCours);
                if (this.listeBateaux.size() > 0) {
                    setBateauEnCours(this.listeBateaux.get(0));
                } else {
                    setBateauEnCours(null);
                    //Fin du placement, donc envoie du terrain a l'adversaire et recuperation du sien:
					RMI.setPlacement(Jeu.getInstance().getTerrainJ1());
					Jeu.getInstance().setTerrainJ2(RMI.getPlacement());
					vj.update();
                    vj.ajouterTirerListener();
                }
            }
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String bateauStringNum=e.getActionCommand();
        int bateauNum=Integer.parseInt(bateauStringNum.split(" ")[1]);

        for (int i=0;i<this.listeBateaux.size();i++) {
            Bateau b=this.listeBateaux.get(i);
            if (b.getId() == bateauNum) {
                setBateauEnCours(b);
            }
        }
    }
}
