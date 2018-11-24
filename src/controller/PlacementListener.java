package controller;

import model.Bateau;
import model.Coup;
import model.Terrain;
import textureFactory.WrongEpoqueException;
import view.CustomJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

public class PlacementListener implements MouseListener, MouseWheelListener {

    private Terrain terrain;

    private ArrayList<Bateau> listeBateaux=new ArrayList<Bateau>();
    private Bateau bateauEnCours;

    private Coup coup;
    private int x;
    private int y;

    private CustomJButton[][] grid;

    private static final Color red=new Color(255, 0, 0,50);
    private static final Color green=new Color(0, 255, 0,50);
    private static final Color blue=new Color(51, 255, 230,50);

    public PlacementListener(CustomJButton[][] grid, Terrain t, ArrayList<Bateau> listeBateaux) {
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
                afficherBateau();
                int taille=this.bateauEnCours.getTaille();
                int direction=this.bateauEnCours.getDirection();
                setPlacedInDirection(direction,taille);
                this.listeBateaux.remove(bateauEnCours);
                effacerBateau();
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
        afficherBateau();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (bateauEnCours == null) {
            return;
        }
        setXYFromMouseEvent(e);
        effacerBateau();
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
        effacerBateau();
        this.bateauEnCours.setDirection(newdirection);
        afficherBateau();
    }

    private void afficherBateau() {
        if (bateauEnCours == null) {
            return;
        }

        int taille=this.bateauEnCours.getTaille();
        int direction=this.bateauEnCours.getDirection();

        if (terrain.verificationPlacer(coup)) {
            setColorInDirection(direction,taille,green);
        } else {
            setColorInDirection(direction,taille,red);
        }
    }

    private void effacerBateau() {
        if (bateauEnCours == null) {
            return;
        }
        int direction=bateauEnCours.getDirection();
        int taille= bateauEnCours.getTaille();
        setColorInDirection(direction,taille,null);
    }

    public void setColorInDirection(int direction, int nbcases, Color c) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x-i,y,-Math.PI/2,i);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x+i,y,Math.PI/2,i);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x,y-i,Math.PI,i);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x,y+i,0,i);
                }
                break;
        }
    }

    public void setPlacedInDirection(int direction, int nbcases) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    //System.out.println("x="+(int)(x-i)+" y="+y);
                    placed(x-i,y);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    //System.out.println("x="+(int)(x+i)+" y="+y);
                    placed(x+i,y);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    //System.out.println("x="+x+" y="+(int)(y-i));
                    placed(x,y-i);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    //System.out.println("x="+x+" y="+(int)(y+i));
                    placed(x,y+i);
                }
                break;
        }
    }

    public void placed(int x, int y) {
        CustomJButton cb = grid[x][y];
        cb.setBateauPose(true);
    }

    private void display(Color c, int x, int y, double rotation, int numTexture) {
        if (bateauEnCours == null) {
            return;
        }
        if (x>= 0 && x < grid.length && y>=0 && y < grid[0].length && grid[x][y] != null) {
            CustomJButton cb=grid[x][y];
            cb.setBackground(c);

            ImageIcon imgIcon = null;
            if (!cb.isBateauPose()) {
                if (c==null) {
                    cb.setRotation(0);
                    cb.setIcon(null);
                } else {
                    try {
                        imgIcon = bateauEnCours.getTexture(numTexture);
                    } catch (WrongEpoqueException e) {
                        e.printStackTrace();
                    }
                    cb.setRotation(rotation);
                    cb.setIcon(imgIcon);
                }
            }
        }
    }

    //Permet d'avoir un seul listener
    private void setXYFromMouseEvent(MouseEvent e) {
        CustomJButton button=(CustomJButton)(e.getSource());
        this.x=button.getSpecialX();
        this.y=button.getSpecialY();
        this.coup.setXY(x-1,y-1);
    }

}
