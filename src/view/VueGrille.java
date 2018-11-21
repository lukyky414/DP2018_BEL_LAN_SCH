package view;

import controller.PlacementListener;
import model.Bateau;
import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.Observer;

public abstract class VueGrille extends JPanel implements Observer {

    public final static Color colorEmpty = new Color(0, 0, 0,0);

    protected Terrain terrain;

    protected int size;
    protected int tailleBouton;
    protected CustomJButton[][] grid;

    public VueGrille(Terrain t, int s, int tailleBoutons) {
        this.tailleBouton=tailleBoutons;
        this.terrain = t;
        this.size = s;
        GridLayout gridLayout = new GridLayout(this.size + 1, this.size + 1, 1, 1);
        this.setLayout(gridLayout);

        //Création d'une grille de boutons
        this.grid = new CustomJButton[size + 1][size + 1];

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
        button = new CustomJButton(s);
        button.setOpaque(false);
        button.setPreferredSize(new Dimension(this.tailleBouton, this.tailleBouton));
        button.setMargin(new Insets(0, 0, 0, 0));
        if (border) {
            button.setBackground(colorEmpty);
        } else {
            grid[x][y]=button;
           //TODO REMOVE THIS !!!
            Bateau b= new Bateau(null,7,null);
            b.setDirection(Bateau.GAUCHE);
            if (terrain != null) {
                button.addMouseListener(new PlacementListener(this.grid,x,y,terrain,b));
            }
        }
        this.add(button);


    }

}