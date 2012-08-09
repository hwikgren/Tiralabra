/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import SanaIndeksi.PuuSolmu;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Testaa PuuSolmu-luokan toimintaa. 
 * @author heidi
 */
public class SolmuTest {
    PuuSolmu vanhempi;
    
    public SolmuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        vanhempi = new PuuSolmu('t', 1, false);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Testaa, että solmun luominen toimii ja että samalla luodaan solmulle lapset-taulukko.
     */
    @Test
    public void lisaaSolmu() {
        assertEquals( 't', vanhempi.kirjain );
        assertTrue( vanhempi.lapset != null);
    }
    
    /**
     * Testaa, että lapsen lisääminen solmulle toimii 
     * ja että lapsella on sen jälkeen 40-kokoinen lapset-taulu ja olemassaoleva rivi-taulu
     */
    @Test
    public void lisaaLapsi() {
        vanhempi.setLapsi('r', 1, false);
        PuuSolmu lapsi = vanhempi.getLapsi('r');
        assertTrue( lapsi.lapset.length == 40);
        assertEquals( 'r', lapsi.kirjain );
        assertTrue( lapsi.rivit != null);
    }
    
    /**
     * Testaa useamman lapsen lisäämistä samalle solmulle.
     */
    @Test
    public void lisaaLapsia() {
        vanhempi.setLapsi('r', 2, false);
        vanhempi.setLapsi('e', 3, false);
        vanhempi.setLapsi('o', 3, false);
        vanhempi.setLapsi('a', 5, false);
        vanhempi.setLapsi('i', 6, false);
        vanhempi.setLapsi('u', 6, false);
        PuuSolmu lapsi = vanhempi.getLapsi('r');
        assertEquals( 'r', lapsi.kirjain );
        PuuSolmu lapsi2 = vanhempi.getLapsi('e');
        assertEquals( 'e', lapsi2.kirjain );
        assertTrue( lapsi.lapset.length == 40);
    }
}
