package model;

import java.awt.*;

public class Coup {

    private Point pos;
    private Bateau bateau;

    public Coup(Point pos, Bateau bateau) {
        this.pos = pos;
        this.bateau = bateau;
    }

    public void setBateau(Bateau bateau) {
        this.bateau = bateau;
    }

    public void setXY(int x, int y) {
        Point p=new Point(x,y);
        this.pos=p;
    }

    public Bateau getBateau() {
        return bateau;
    }

    public Point getPos() {
        return pos;
    }
}
