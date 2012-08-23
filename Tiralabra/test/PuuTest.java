/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import SanaIndeksi.Puu;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Testaa puun toimintaa. 
 * @author heidi
 */
public class PuuTest {
    Puu puu;
    
    public PuuTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        puu = new Puu();
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Testaa, että sanan lisäys puuhun toimii. 
     */
    @Test
    public void lisaaSana() {
        
        puu.lisaaSana("tree", 1);
        puu.lisaaSana("node", 1);
        assertTrue( puu.juuri.getLapsi('t') != null );
    }
    
    /**
     * Testaa, että sanan etsiminen toimii.
     */
    @Test
    public void etsiSana() {
        puu.lisaaSana("tree", 1);
        puu.lisaaSana("true", 1);
        assertTrue( puu.etsiSana("tree") != null);
    }
    
    /**
     * Testaa, että etsiminen toimii, kun sanaa ei löydy.
     */
    @Test
    public void sanaaEiLoydy() {
        puu.lisaaSana("tree", 1);
        puu.lisaaSana("node", 1);
        assertTrue( puu.etsiOsa("tru*") == null);
    }
    
    @Test
    public void kasvatetaanLapsia() {
        String merkit = "qwertyuiopåasdfghjklöäzxcvbnm,,-><;:_'*^¨´`?0=9)8(7/6&5%4€3#21!©@£$∞§|[[]≈±";
        for (int i=0; i<merkit.length(); i++) {
            puu.juuri.setLapsi(merkit.charAt(i), i, true);
        }
        assertTrue( puu.juuri.lapset.length > 40);
    }
}
