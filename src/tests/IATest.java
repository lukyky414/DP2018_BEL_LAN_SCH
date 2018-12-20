package tests;

import model.Bateau;
import model.IA;
import model.Jeu;
import model.Terrain;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import textureFactory.SingletonEpoque;
import textureFactory.SingletonMedieval;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IATest {


    SingletonEpoque singleton = SingletonMedieval.getInstance();
    Jeu j = Jeu.getInstance();
    Terrain t = new Terrain();
        IA ia = new IA(0, t);


    @Test
    public void placerBateaux() {
        assertNotNull(ia.placerBateaux());
        ArrayList<Bateau> bateaux = ia.getTerrain().getBateaux();
        for(Bateau b : bateaux){
            System.out.println(b.toString());
        }



    }
}