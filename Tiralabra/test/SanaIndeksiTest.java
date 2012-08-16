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
 * Testaa Tallentaja ja Hakija-luokkien toimintaa.
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
        tallentaja.tallenna("Koe.txt", tiedosto);
        System.setOut(new PrintStream(ulos));
    }
    
    
    @After
    public void tearDown() {
        tallentaja = null;
        System.setOut(null);
    }
    
    /**
     * Testaa, että tallennus toimii.
     */
    /*@Test
    public void tallennusToimii() {
        assertTrue( tallentaja.rivit.length > 0 );
    }*/
    
    /**
     * Testaa, että haku toimii.
     */
    @Test
    public void hakuToimii() throws FileNotFoundException {
        tallentaja = new Tallentaja();
        puu = tallentaja.sanaPuu;
        hakija = new Hakija(puu, tallentaja);
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
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
        assertEquals( "Haku \"joku\" ei tuottanut yhtään riviä!\n", ulos.toString() );
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
        assertEquals( "Haku \"dogs\" ei tuottanut yhtään riviä!\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kahdella hakusanalla ja hakumerkillä
    @Test
    public void hakuKahdella() {
        hakija.printtaa("him & her");
        assertEquals( "29. blood, and called her to revive and not to leave him miserable,\n", ulos.toString() );
    }
    
    //Testaa, että haku kahden sanan haku toimii suluilla
    @Test
    public void hakuKahdellaJaSuluilla() {
        hakija.printtaa("(him & her)");
        assertEquals( "29. blood, and called her to revive and not to leave him miserable,\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kolmella sanalla
    @Test
    public void hakuKolmella() {
        hakija.printtaa("con* & (he / her)");
        assertEquals( "1. Cephalus, though he had lost his dog, still continued to take\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kolmella sanalla kun sulkeet ensimmäisten sanojen ympärillä
    @Test
    public void toinenHakuKolmella() {
        hakija.printtaa("(con* & he) / her");
        assertEquals( "1. Cephalus, though he had lost his dog, still continued to take\n"
                + "18. directed her.  Cephalus came as he was wont when tired with\n"
                + "26. place, and found her bleeding and with sinking strength\n"
                + "27. endeavoring to draw forth from the wound the javelin, her own\n"
                + "28. gift.  Cephalus raised her from the earth, strove to stanch the\n"
                + "29. blood, and called her to revive and not to leave him miserable,\n"
                + "30. to reproach himself with her death.  She opened her feeble eyes,\n"
                + "35. What advantage to disclose it now?  She died; but her face wore a\n"
                + "36. calm expression, and she looked pityingly and forgivingly on her\n"
                + "37. husband when he made her understand the truth.\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kolmella sanalla ilman sulkuja. 
    //Tulos on eri kuin käyttäjä ehkä odottaisi.
    @Test
    public void hakuIlmanSulkuja() {
        hakija.printtaa("con* & he / her");
        assertEquals( "1. Cephalus, though he had lost his dog, still continued to take\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii neljällä sanalla
    @Test
    public void hakuNeljalla() {
        hakija.printtaa("((him/her)&(con*/green))");
        assertEquals( "17. after him, and concealed herself in the place where the informer\n", ulos.toString() );
    } 
    
    //Testaa, että haku toimii neljällä vaikka koko lauseen ympärillä ei ole sulkuja
    @Test
    public void hakuVahemmillaSuluilla() {
        hakija.printtaa("(him/her)&(con*/green)");
        assertEquals( "17. after him, and concealed herself in the place where the informer\n", ulos.toString() );
    }
}
