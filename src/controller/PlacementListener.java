package controller;

import model.Bateau;
import model.Coup;
import model.Terrain;
import textureFactory.SingletonMedieval;
import textureFactory.WrongEpoqueException;
import view.CustomJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class PlacementListener implements MouseListener, MouseWheelListener {

    private Terrain t;
    private Bateau b;

    private Coup c;

    private int x;
    private int y;

    private CustomJButton[][] grid;

    private static final Color red=new Color(255, 0, 0,50);
    private static final Color green=new Color(0, 255, 0,50);
    private static final Color blue=new Color(51, 255, 230,50);

    public PlacementListener(CustomJButton[][] grid, Terrain t, Bateau b) {
        this.grid = grid;
        this.t = t;
        this.b = b;
        this.x=0;
        this.y=0;
        this.c = new Coup(new Point(0,0),b);
    }

    public void setB(Bateau b) {
        this.b = b;
        this.c.setBateau(b);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setXYFromMouseEvent(e);
        if (e.getButton() == 3) {
            changerRotation(1);
        } else {
            if (t.verificationPlacer(c)) {
                t.placer(c);
                afficherBateau();
                int taille=this.b.getTaille();
                int direction=this.b.getDirection();
                setPlacedInDirection(direction,taille);
                System.out.println(direction);
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
        setXYFromMouseEvent(e);
        afficherBateau();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setXYFromMouseEvent(e);
        effacerBateau();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        changerRotation(e.getWheelRotation());
    }

    //rotation 1 ou -1
    private void changerRotation(int rotation) {
        int newdirection=(b.getDirection()+rotation+4)%4;
        effacerBateau();
        this.b.setDirection(newdirection);
        afficherBateau();
    }

    private void afficherBateau() {
        int taille=this.b.getTaille();
        int direction=this.b.getDirection();

        if (t.verificationPlacer(c)) {
            setColorInDirection(direction,taille,green);
        } else {
            setColorInDirection(direction,taille,red);
        }
    }

    private void effacerBateau() {
        int direction=b.getDirection();
        int taille=b.getTaille();
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
        //System.out.println("direction="+direction);
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
        //if (x>= 0 && x < grid.length && y>=0 && y < grid[0].length && grid[x][y] != null) {
            CustomJButton cb = grid[x][y];
            cb.setBateauPose(true);
            //System.out.println("x="+x+" y="+y);
       //}
    }

    private void display(Color c, int x, int y, double rotation, int numTexture) {
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
                        imgIcon = b.getTexture(numTexture);
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
        this.c.setXY(x-1,y-1);
    }

}
