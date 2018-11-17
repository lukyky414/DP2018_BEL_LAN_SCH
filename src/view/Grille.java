package view;

import model.Terrain;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Grille extends JPanel implements Observer {

    private Terrain terrain;

    private int size;
    private JButton[][] grid;

    public Grille(Terrain t, int s) {
        this.terrain = t;
        this.size = s;
        GridLayout gridLayout = new GridLayout(this.size + 1, this.size + 1, 1, 1);
        this.setLayout(gridLayout);

        //Création d'une grille de boutons
        grid = new JButton[size + 1][size + 1];

        //Les boutons de 1 à 10
        for (int j = 0; j < size + 1; j++) {
            //Bouton vide en haut à gauche
            if (j == 0) {
                addJButton(grid[0][j], "", true);
            } else {
                addJButton(grid[0][j], "" + j, true);
            }
        }

        //Les Lettres de A  à J
        for (int i = 1; i < size + 1; i++) {
            char character = (char) ('A' + i - 1);
            addJButton(grid[i][0], "" + character, true);

            //Les Cases restantes
            for (int j = 1; j < size + 1; j++) {
                addJButton(grid[i][j], "", false);
            }
        }
    }

    private void addJButton(JButton button, String s, boolean border) {
        button = new JButton(s);
        if (border) {
            button.setBackground(Color.lightGray);
            button.setOpaque(false);
        }
        button.setPreferredSize(new Dimension(50, 50));
        this.add(button);
    }

    @Override
    public void update(Observable o, Object arg) {
        //Utilise Terrain pour mettre à jour la grille
    }
}