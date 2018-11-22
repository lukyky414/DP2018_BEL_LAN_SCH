package controller;

import model.Bateau;
import model.Coup;
import model.Terrain;
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
        if (e.getButton() == 3) {
            changerRotation(1);
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

        int taille=this.b.getTaille();
        int direction=this.b.getDirection();

        if (t.verificationPlacer(c)) {
            setColorInDirection(direction,taille,PlacementListener.green);
        } else {
            setColorInDirection(direction,taille,PlacementListener.red);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setXYFromMouseEvent(e);

        int taille=this.b.getTaille();
        int direction=this.b.getDirection();
        setColorInDirection(direction,taille,null);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        changerRotation(e.getWheelRotation());
    }

    //rotation 1 ou -1
    private void changerRotation(int rotation) {
        int olddirection=b.getDirection();
        int newdirection=(b.getDirection()+rotation+4)%4;
        int taille=b.getTaille();

        setColorInDirection(olddirection,taille,null);


        this.b.setDirection(newdirection);
        if (t.verificationPlacer(c)) {
            setColorInDirection(newdirection,taille,green);
        } else {
            setColorInDirection(newdirection,taille,red);
        }
    }

    public void setColorInDirection(int direction, int nbcases, Color c) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x-i,y,Math.PI);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x+i,y,0);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x,y-i,Math.PI/2);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x,y+i,-Math.PI/2);
                }
                break;
        }
    }

    private void display(Color c, int x, int y, double rotation) {
        if (x>= 0 && x < grid.length && y>=0 && y < grid[0].length && grid[x][y] != null) {
            CustomJButton cb=grid[x][y];
            cb.setBackground(c);
            if (c==null) {
                cb.setIcon(null);
                cb.setRotation(0);
            } else {
                ImageIcon i =new ImageIcon("boat.png");
                Image img = i.getImage() ;
                Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
                cb.setRotation(rotation);
                cb.setIcon(new ImageIcon(newimg));
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
