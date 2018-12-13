package tests;

import model.*;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class JeuTest {

    Bateau b;
    Coup c;
    Point pos;
    ChampTir c1;
    Jeu j;
    Terrain t;

    @Before
    public void create(){
        b = EasyMock.createStrictMock(Bateau.class);
        c1 = EasyMock.createStrictMock(ChampTir.class);
        j = EasyMock.createStrictMock(Jeu.class);
        t = EasyMock.createStrictMock(Terrain.class);
        pos = new Point(4,4);
        c = new Coup(pos,b);


    }


    @Test
    public void verificationTir() {
        b.setPv(0);
       EasyMock.expect(j.verificationTir(c, t)).andReturn(false);
        b.setPv(4);
        b.setMunitions(0);
        EasyMock.expect(j.verificationTir(c, t)).andReturn(false);
        b.setMunitions(3);
        c1.touche(pos);
        EasyMock.expect(j.verificationTir(c, t)).andReturn(false);
    }

    @Test
    public void tirHumain() {
    }
}