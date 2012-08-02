/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import SanaIndeksi.RiviKeko;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author heidi
 */
public class KekoTest {
    
    public KekoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void keonTestaus() {
        RiviKeko keko = new RiviKeko();
        keko.lisaaRivi(1);
        keko.lisaaRivi(16);
        keko.lisaaRivi(3);
        keko.lisaaRivi(15);
        keko.lisaaRivi(8);
        int[] rivit = keko.getRivit();
        String lista = "";
        for (int i=0; i<rivit.length-1; i++) {
            lista += rivit[i]+", ";
        }
        lista += rivit[rivit.length-1];
        assertEquals( "1, 3, 8, 15, 16, 0, 0, 0, 0, 0", lista);
    }
}
