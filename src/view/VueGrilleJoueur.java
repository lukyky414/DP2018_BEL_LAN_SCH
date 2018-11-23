package view;

import model.Bateau;
import model.Terrain;
import textureFactory.WrongEpoqueException;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;

public class VueGrilleJoueur extends VueGrille {

    public VueGrilleJoueur(Terrain t, int s, int tb) {
        super(t,s,tb);
    }

    @Override
    public void update(Observable o, Object arg) {
        /*ArrayList<Bateau> list=this.terrain.getBateaux();
        for (int i=0;i<list.size();i++) {
            Bateau b=list.get(i);
            Point p=b.getPosition();
            int x=(int)p.getX();
            int y=(int)p.getY();
            int direction=b.getDirection();
            int taille=b.getTaille();

            //setColorInDirection(b,x,y,direction,taille,new Color(0,0,0,0));
        }*/
    }

    /*public void setColorInDirection(Bateau b, int x,int y, int direction, int nbcases, Color c) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    display(b,c,x-i,y,-Math.PI/2,i);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    display(b,c,x+i,y,Math.PI/2,i);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    display(b,c,x,y-i,Math.PI,i);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    display(b,c,x,y+i,0,i);
                }
                break;
        }
    }

    private void display(Bateau b, Color c, int x, int y, double rotation, int numTexture) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] != null) {
            CustomJButton cb = grid[x][y];
            cb.setBackground(c);
            if (c == null) {
                cb.setIcon(null);
                cb.setRotation(0);
            } else {
                switch (numTexture) {
                    case 0:
                        i = SingletonMedieval.getInstance().textures.get(1).get(0);
                        break;
                    case 1:
                        i =new ImageIcon("img/Medieval/Bateau T2-1.png");
                        break;
                    case 2:
                        i =new ImageIcon("img/Medieval/Bateau T2-2.png");
                        break;
                    case 3:
                        i =new ImageIcon("Bateau T5-4.png");
                        break;
                    case 4:
                        i =new ImageIcon("Bateau T5-5.png");
                        break;
                }
                ImageIcon i = null;
                try {
                    i = b.getTexture(numTexture);
                } catch (WrongEpoqueException e) {
                    e.printStackTrace();
                }
                //A supprimer
                //ImageIcon i =new ImageIcon("Bateau T3-1.png");
                Image img = i.getImage();
                Image newimg = img.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
                cb.setRotation(rotation);
                cb.setIcon(new ImageIcon(newimg));
            }
        }
    }*/
}
