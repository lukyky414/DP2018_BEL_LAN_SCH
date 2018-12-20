package tests;

import model.Bateau;
import model.IA;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IATest {


    /*SingletonEpoque singleton = SingletonMedieval.getInstance();
    Jeu j = Jeu.getInstance();
    Terrain t = new Terrain();
        IA ia = new IA(0, t);*/


    @Test
    public void placerBateaux() {
        assertNotNull(IA.placerBateaux());
        ArrayList<Bateau> bateaux = IA.getTerrainJoueur().getBateaux();
        for(Bateau b : bateaux){
            System.out.println(b.toString());
        }



    }
}