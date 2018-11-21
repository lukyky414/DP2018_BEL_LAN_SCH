package controller;

import model.Bateau;
import model.Coup;
import model.Terrain;
import view.CustomJButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlacementListener implements MouseListener {

    private Terrain t;
    private Bateau b;

    private Coup c;

    private CustomJButton[][] grid;
    private int x;
    private int y;

    public PlacementListener(CustomJButton[][] grid, int x, int y, Terrain t, Bateau b) {
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.t = t;
        this.b = b;
        this.c = new Coup(new Point(x-1,y-1),b);
    }

    public void setB(Bateau b) {
        this.b = b;
        this.c = new Coup(new Point(x,y),b);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       if (e.getButton() == 3) {

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
        Color usedColor;

        Color red=new Color(255, 0, 0,50);
        Color green=new Color(0, 255, 0,50);
        if (t.verificationPlacer(c)) {
            usedColor=green;
        } else {
            usedColor=red;
        }
        int taille=this.b.getTaille();
        int direction=this.b.getDirection();
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < taille; i++) {
                    display(usedColor,x-i,y);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < taille; i++) {
                    display(usedColor,x+i,y);
                }
                break;
            case Bateau.GAUCHE:

                for (int i = 0; i < taille; i++) {
                    display(usedColor,x,y-i);
                }
                break;
            case Bateau.DROITE:

                for (int i = 0; i < taille; i++) {
                    display(usedColor,x,y+i);
                }
                break;
        }
        System.out.println("x="+x+" y="+y);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        /*Color usedColor;

        Color red=new Color(255, 0, 0,50);
        Color green=new Color(0, 255, 0,50);
        if (t.verificationPlacer(c)) {
            usedColor=red;
        } else {
            usedColor=green;
        }*/
        int taille=this.b.getTaille();
        int direction=this.b.getDirection();
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < taille; i++) {
                    display(null,x-i,y);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < taille; i++) {
                    display(null,x+i,y);
                }
                break;
            case Bateau.GAUCHE:

                for (int i = 0; i < taille; i++) {
                    display(null,x,y-i);
                }
                break;
            case Bateau.DROITE:

                for (int i = 0; i < taille; i++) {
                    display(null,x,y+i);
                }
                break;
        }
    }

    private void display(Color c, int x, int y) {
        if (x>= 0 && x < grid.length && y>=0 && y < grid[0].length && grid[x][y] != null) {
            CustomJButton cb=grid[x][y];
            cb.setBackground(c);

            if (c==null) {
                /*cb.setIcon(null);*/
            } else {
                /*ImageIcon i =new ImageIcon("test.png");
                Image img = i.getImage() ;
                Image newimg = img.getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ) ;
                System.out.println(i.getImage());
                cb.setIcon(new ImageIcon(newimg));*/
            }

        }
    }

}
