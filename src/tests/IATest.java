package tests;

import model.Terrain;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IATest {

    @Before
    public void setUp() throws Exception {
        Terrain t = EasyMock.createStrictMock(Terrain.class);

    }

    @Test
    public void placerBateaux() {
    }
}