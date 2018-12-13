package view;

import controller.PlacementListener;
import model.Bateau;
import model.Coup;
import model.Terrain;
import textureFactory.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

public abstract class VueGrille extends JPanel implements Observer {

    public final static Color colorEmpty = new Color(0, 0, 0,0);

    protected Terrain terrain;

    protected int size;
    protected int tailleBouton;
    protected CustomJButton[][] grid;
    protected JButton[] buttonBateau;

    protected PlacementListener placementListener;

    private static final Color red=new Color(255, 0, 0,50);
    private static final Color green=new Color(0, 255, 0,50);
    //private static final Color blue=new Color(51, 255, 230,50);

    public VueGrille(Terrain t, int s, int tailleBoutons) {
        this.tailleBouton=tailleBoutons;
        this.terrain = t;
        if (this.terrain != null) {
            this.terrain.addObserver(this);
        }
        this.size = s;
        GridLayout gridLayout = new GridLayout(this.size + 1, this.size + 1, 1, 1);
        this.setLayout(gridLayout);

        //Création d'une grille de boutons
        this.grid = new CustomJButton[size + 1][size + 1];

        //TODO REMOVE THIS !!!
        ArrayList<Bateau> liste=SingletonStarWars.getInstance().generateFleet();
        this.placementListener=new PlacementListener(this,this.grid,terrain,liste);

        //Les boutons de 1 à 10
        for (int j = 0; j < size + 1; j++) {
            //Bouton vide en haut à gauche
            if (j == 0) {
                addJButton(grid[0][j], "", true,-1,-1);
            } else {
                addJButton(grid[0][j], "" + j, true,-1,-1);
            }
        }

        //Les Lettres de A  à J
        for (int i = 1; i < size + 1; i++) {
            char character = (char) ('A' + i - 1);
            addJButton(grid[i][0], "" + character, true,-1,-1);

            //Les Cases restantes
            for (int j = 1; j < size + 1; j++) {
                addJButton(grid[i][j], "", false,i,j);
            }
        }
    }

    private void addJButton(CustomJButton button, String s, boolean border, int x, int y) {
        button = new CustomJButton(s,x,y);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(this.tailleBouton, this.tailleBouton));
        button.setMargin(new Insets(0, 0, 0, 0));
        if (border) {
            button.setBackground(colorEmpty);
        } else {
            grid[x][y]=button;
            if (terrain != null) {
                button.addMouseListener(this.placementListener);
                button.addMouseWheelListener(this.placementListener);
            }
        }
        this.add(button);
    }

    public void effacerBateau(int x, int y, Bateau b) {
        if (b == null) {
            return;
        }
        int direction=b.getDirection();
        int taille= b.getTaille();
        setColorInDirection(direction,taille,null,x,y,b);
    }

    public void afficherBateau(int x, int y, Bateau b, Coup coup) {
        if (b == null) {
            return;
        }

        int taille=b.getTaille();
        int direction=b.getDirection();

        if (terrain.verificationPlacer(coup)) {
            setColorInDirection(direction,taille,green,x,y,b);
        } else {
            setColorInDirection(direction,taille,red,x,y,b);
        }
    }

    public void setPlacedInDirection(int direction, int nbcases, int x ,int y) {
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

    public void setColorInDirection(int direction, int nbcases, Color c, int x, int y, Bateau b) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x-i,y,-Math.PI/2,i,b);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x+i,y,Math.PI/2,i,b);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x,y-i,Math.PI,i,b);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    display(c,x,y+i,0,i,b);
                }
                break;
        }
    }

    public void display(Color c, int x, int y, double rotation, int numTexture, Bateau b) {
        if (b == null) {
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



}