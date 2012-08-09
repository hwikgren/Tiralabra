/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import SanaIndeksi.Hakija;
import SanaIndeksi.Puu;
import SanaIndeksi.Tallentaja;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 * Testaa Tiralabra, Tallentaja ja Hakija-luokkien toimintaa.
 * @author heidi
 */
public class SanaIndeksiTest {
    
    Tallentaja tallentaja;
    Puu puu;
    Hakija hakija;
    File tiedosto;
    private final ByteArrayOutputStream ulos = new ByteArrayOutputStream();
    
    public SanaIndeksiTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws FileNotFoundException {
        tallentaja = new Tallentaja();
        puu = tallentaja.sanaPuu;
        hakija = new Hakija(puu, tallentaja);
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna(tiedosto);
        System.setOut(new PrintStream(ulos));
    }
    
    
    @After
    public void tearDown() {
        System.setOut(null);
    }
    
    /**
     * Testaa, että tallennus toimii.
     */
    @Test
    public void tallennusToimii() {
        assertTrue( tallentaja.rivit.size() > 0 );
    }
    
    /**
     * Testaa, että haku toimii.
     */
    @Test
    public void hakuToimii() {
        hakija.printtaa("dog");
        assertEquals( "1. Cephalus, though he had lost his dog, still continued to take\n", ulos.toString() );
    }
    
    /**
     * Testaa, että haku toimii kun tuloksena on usempia rivejä.
     */
    @Test
    public void sanaUseallaRivilla() {
        hakija.printtaa("went");
        assertEquals( "11. he was talking to some maiden, went and told the secret to\n"
                + "16. morning, when Cephalus went to hunt as usual.  Then she stole out\n", ulos.toString() );
    }
    
    /**
     * Testaa, että haku toimii sanalla, jota ei ole tiedostossa.
     */
    @Test
    public void hakuSanallaJotaEiLoydy() {
        hakija.printtaa("joku");
        assertEquals( "Sanaa \"joku\" ei löydy tiedostosta!\n", ulos.toString() );
    }
    
    /**
     * Testaa, että haku toimii sanan osalla.
     */
    @Test
    public void hakuSananOsalla() {
        hakija.printtaa("him*");
        assertTrue( ulos.toString().contains("stretched himself on the green bank") );
    }
    
    /**
     * Testaa, että haku toimii sanalla, josta osa löytyy mutta koko sanaa ei.
     */
    @Test
    public void hakuSanallaJostaOsaLoytyy() {
        hakija.printtaa("dogs");
        assertEquals( "Sanaa \"dogs\" ei löydy tiedostosta!\n", ulos.toString() );
    }
}
