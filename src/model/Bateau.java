package model;

import factory.SingletonEpoque;

import java.awt.*;
import java.util.ArrayList;

public class Bateau {

    private static int NEXT_ID;
    private static int HAUT = 0;
    private static int BAS = 1;
    private static int GAUCHE = 2;
    private static int DROITE = 3;

    private SingletonEpoque epoque;

    public int id;
    public int munitions;
    public int pv;
    public int direction;
    public Point position;
    public int taille;
    public ArrayList<Point> zoneSup;

    public Bateau() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMunitions() {
        return munitions;
    }

    public void setMunitions(int munitions) {
        this.munitions = munitions;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public ArrayList<Point> getZoneSup() {
        return zoneSup;
    }

    public void setZoneSup(ArrayList<Point> zoneSup) {
        this.zoneSup = zoneSup;
    }

    
}
