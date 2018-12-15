package view;

import controller.PlacementListener;
import model.Bateau;
import model.ChampTir;
import model.Coup;
import model.Terrain;
import textureFactory.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observer;

public abstract class VueGrille extends JPanel implements Observer {

    public final static Color colorEmpty = new Color(0, 0, 0,0);

    protected ArrayList<Bateau> listeBateaux;
    protected Terrain terrain;

    protected int size;
    protected int tailleBouton;
    protected CustomJButton[][] grid;
    protected JButton[] buttonBateau;
    protected JPanel panelGrille;
    protected JPanel panelBoutonsBateaux;
    protected JButton[] tableauBoutonsBateaux;

    protected PlacementListener placementListener;

    protected static final Color red=new Color(255, 0, 0,50);
    protected static final Color green=new Color(0, 255, 0,50);
    protected static final Color empty=new Color(0,0,0,0);
    //private static final Color blue=new Color(51, 255, 230,50);

    public VueGrille(Terrain t, int s, int tailleBoutons) {
        this.listeBateaux=null;
        this.tableauBoutonsBateaux=new JButton[5];
        this.panelGrille=new JPanel();
        this.panelBoutonsBateaux=new JPanel();
        this.tailleBouton=tailleBoutons;
        this.terrain = t;
        if (this.terrain != null) {
            this.terrain.addObserver(this);
        }
        this.size = s;
        GridLayout gridLayout = new GridLayout(this.size + 1, this.size + 1, 1, 1);
        panelGrille.setLayout(gridLayout);
        panelGrille.setMaximumSize(new Dimension(560,560));
        panelGrille.setMinimumSize(new Dimension(560,560));
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

        Dimension espaceBoutons=new Dimension(0,10);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        panelBoutonsBateaux.setLayout(new BoxLayout(panelBoutonsBateaux,BoxLayout.X_AXIS));
        ajoutertBoutons();
        this.add(Box.createRigidArea(espaceBoutons));
        this.add(panelBoutonsBateaux);
        this.add(Box.createRigidArea(espaceBoutons));
        this.add(panelGrille);
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
        panelGrille.add(button);
    }

    private void ajoutertBoutons() {
        for (int i=0;i<5;i++) {
            JButton bouton=new JButton("Bateau "+i);
            tableauBoutonsBateaux[i]=bouton;
            panelBoutonsBateaux.add(bouton);
        }
    }

    public void ajouterTerrain(Terrain t) {
        this.terrain=t;
        this.terrain.addObserver(this);
    }

    public void removeListener(PlacementListener pl) {
        //On met à jour le Placement Listener
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                grid[i][j].removeMouseListener(pl);
                grid[i][j].removeMouseWheelListener(pl);
            }
        }

        for (int i=0;i<tableauBoutonsBateaux.length;i++) {
            tableauBoutonsBateaux[i].removeActionListener(pl);
        }
    }

    public void ajouterBateau(ArrayList<Bateau> listeBateaux) {
        if (this.placementListener != null) {
            removeListener(this.placementListener);
        }

        this.listeBateaux=listeBateaux;
        this.placementListener=new PlacementListener(this,this.grid,terrain,listeBateaux,tableauBoutonsBateaux);

        //On met à jour le Placement Listener
        for (int i = 1; i < size + 1; i++) {
            for (int j = 1; j < size + 1; j++) {
                grid[i][j].addMouseListener(this.placementListener);
                grid[i][j].addMouseWheelListener(this.placementListener);
            }
        }

        for (int i=0;i<tableauBoutonsBateaux.length;i++) {
            tableauBoutonsBateaux[i].addActionListener(this.placementListener);
            tableauBoutonsBateaux[i].setEnabled(true);
        }
    }



    public void effacerGrille() {
        for (int i=1;i<grid.length;i++) {
            for (int j=1;j<grid[0].length;j++) {
                resetJButton(i,j,false);
            }
        }
    }

    public void effacerBateau(int x, int y, Bateau b) {
        if (b == null) {
            return;
        }
        int direction=b.getDirection();
        int taille= b.getTaille();
        resetDansUneDirection(x,y,direction,taille,true);
    }

    public void afficherBateau(int x, int y, Bateau b, Coup coup) {
        if (b == null) {
            return;
        }

        int taille=b.getTaille();
        int direction=b.getDirection();

        if (terrain.verificationPlacer(coup)) {
            bateauDansUneDirection(x,y,direction,b,true);
            couleurDansUneDirection(x,y,direction,taille,green,true);
        } else {
            bateauDansUneDirection(x,y,direction,b,true);
            couleurDansUneDirection(x,y,direction,taille,red,true);
        }
    }

    public void resetDansUneDirection(int x, int y, int direction, int nbcases, boolean temporaire) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    resetJButton(x-i,y,temporaire);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    resetJButton(x+i,y,temporaire);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    resetJButton(x,y-i,temporaire);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    resetJButton(x,y+i,temporaire);
                }
                break;
        }
    }

    public void couleurDansUneDirection(int x, int y, int direction, int nbcases, Color c, boolean temporaire) {
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    afficherCouleurJButton(x-i,y,c,temporaire);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    afficherCouleurJButton(x+i,y,c,temporaire);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    afficherCouleurJButton(x,y-i,c,temporaire);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    afficherCouleurJButton(x,y+i,c,temporaire);
                }
                break;
        }
    }

    public void bateauDansUneDirection(int x, int y, int direction, Bateau b, boolean temporaire) {
        if (b == null) {
            return;
        }
        int nbcases=b.getTaille();
        switch (direction) {
            case Bateau.HAUT:
                for (int i = 0; i < nbcases; i++) {
                    afficherImageBateauJButton(x-i, y,-Math.PI/2,i,b,temporaire);
                }
                break;
            case Bateau.BAS:
                for (int i = 0; i < nbcases; i++) {
                    afficherImageBateauJButton(x+i, y,Math.PI/2,i,b,temporaire);
                }
                break;
            case Bateau.GAUCHE:
                for (int i = 0; i < nbcases; i++) {
                    afficherImageBateauJButton(x, y-i,Math.PI,i,b,temporaire);
                }
                break;
            case Bateau.DROITE:
                for (int i = 0; i < nbcases; i++) {
                    afficherImageBateauJButton(x, y+i,0,i,b,temporaire);
                }
                break;
        }
    }

    public void resetJButton(int x, int y, boolean temporaire) {
        if (verifierCoordonnees(x,y)) {
            CustomJButton cb=grid[x][y];
            if (!cb.isBateauPose() || !temporaire) {
                cb.setRotation(0);
                cb.setIcon(null);
                cb.setBackground(null);
            }

            //On veut qu'un JButton soit effacé de manière permanente
            if (!temporaire) {
                setPermanent(x,y,false);
            }
        }
    }

    public void afficherCouleurJButton(int x, int y, Color c, boolean temporaire) {
        if (verifierCoordonnees(x,y)) {
            CustomJButton cb=grid[x][y];
            if (!cb.isBateauPose() || !temporaire) {
                cb.setBackground(c);
            }
            if (!temporaire) {
                setPermanent(x,y,true);
            }
        }
    }

    public void afficherImageBateauJButton(int x, int y, double rotation, int numTexture, Bateau b, boolean temporaire) {
        if (b == null) {
            return;
        }

        if (verifierCoordonnees(x,y)) {
            CustomJButton cb=grid[x][y];
            if (!cb.isBateauPose() || !temporaire) {
                ImageIcon imgIcon=null;
                try {
                    imgIcon = b.getTexture(numTexture);
                } catch (WrongEpoqueException e) {
                    e.printStackTrace();
                }
                cb.setRotation(rotation);
                cb.setIcon(imgIcon);
            }
            if (!temporaire) {
                setPermanent(x,y,true);
            }
        }
    }

    public void setPermanent(int x, int y, boolean permanent) {
        if (verifierCoordonnees(x,y)) {
            CustomJButton cb = grid[x][y];
            cb.setBateauPose(permanent);
        }
    }

    private boolean verifierCoordonnees(int x, int y) {
        if (x>= 0 && x < grid.length && y>=0 && y < grid[0].length && grid[x][y] != null) {
            return true;
        } else {
            return false;
        }
    }

    public void updateTir() {
        ChampTir ct=this.terrain.getChampTir();
        Point p=new Point(0,0);
        for (int i=0;i<10;i++) {
            for (int j=0;j<10;j++) {
                p.setLocation(i,j);
                if (ct.estTouche(p)) {
                    afficherCouleurJButton(i+1,j+1,red,false);
                }
            }
        }
    }

}