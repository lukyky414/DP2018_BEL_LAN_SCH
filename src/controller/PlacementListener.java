package controller;

import model.CustomJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlacementListener implements MouseListener {

    private CustomJButton[][] grid;
    private int x;
    private int y;

    public PlacementListener(CustomJButton[][] grid, int x, int y) {
        this.grid = grid;
        this.x = x;
        this.y = y;
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
        display(new Color(255, 0, 0,50),x,y);
        display(new Color(255, 0, 0,50),x,y+1);
        display(new Color(255, 0, 0,50),x,y+2);
        display(new Color(255, 0, 0,50),x,y+3);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        display(null,x,y);
        display(null,x,y+1);
        display(null,x,y+2);
        display(null,x,y+3);

    }

    private void display(Color c, int x, int y) {
        if (x>= 0 && x < grid.length && y>=0 && y < grid[0].length && grid[x][y] != null) {
            grid[x][y].setBackground(c);
        }
    }

}
