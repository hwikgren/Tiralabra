/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import SanaIndeksi.Tallentaja;
import SanaIndeksi.Hakija;
import java.io.File;
import java.io.FileNotFoundException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author heidi
 */
public class SanaIndeksiTest {
    
    Tallentaja tallentaja;
    Hakija hakija;
    
    public SanaIndeksiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        tallentaja = new Tallentaja();
        hakija = new Hakija();
    }
    
    @After
    public void tearDown() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void tallennusToimii() throws FileNotFoundException {
        File tiedosto = new File("Koe.txt");
        tallentaja.tallenna(tiedosto);
        assertTrue( tallentaja.rivit.size() > 0 );
    }
    
    @Test
    public void hakuToimii() throws FileNotFoundException {
        File tiedosto = new File("Koe.txt");
        tallentaja.tallenna(tiedosto);
        hakija.printtaa("her", tallentaja);
        assertTrue( hakija.viimeisinRivi == 37 );
    }
    
    @Test
    public void hakuSanallaJotaEiLoydy() throws FileNotFoundException {
        File tiedosto = new File("Koe.txt");
        tallentaja.tallenna(tiedosto);
        hakija.printtaa("joku", tallentaja);
        assertTrue( hakija.viimeisinRivi == 0 );
    }
}
