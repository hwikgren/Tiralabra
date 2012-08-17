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
    @Test
    public void tallennusToimii() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        assertTrue( tallentaja.rivit.length > 0 );
    }
    
    /**
     * Testaa, että kahden tiedoston tallennus toimii.
     * @throws FileNotFoundException 
     */
    @Test
    public void kahdenTallennusToimii() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        tiedosto = new File("Prometheus.txt");
        tallentaja.tallenna("Prometheus.txt", tiedosto);
        assertTrue( tallentaja.rivit.length > 100 );
    }
    
    /**
     * Testaa, että haku toimii.
     */
    @Test
    public void hakuToimii() throws FileNotFoundException {
        
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("dog");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla 'dog' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "1. Cephalus, though he had lost his dog, still continued to take\n", ulos.toString() );
    }
    
    /**
     * Testaa, että haku toimii kun tuloksena on usempia rivejä.
     */
    @Test
    public void sanaUseallaRivilla() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("went");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla 'went' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "11. he was talking to some maiden, went and told the secret to\n"
                + "16. morning, when Cephalus went to hunt as usual.  Then she stole out\n", ulos.toString() );
    }
    
    /**
     * Testaa, että haku toimii sanalla, jota ei ole tiedostossa.
     */
    @Test
    public void hakuSanallaJotaEiLoydy() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("joku");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaku 'joku' ei tuottanut yhtään riviä!\n", ulos.toString() );
    }
    
    /**
     * Testaa, että haku toimii sanan osalla.
     */
    @Test
    public void hakuSananOsalla() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("him*");
        assertTrue( ulos.toString().contains("stretched himself on the green bank") );
    }
    
    /**
     * Testaa, että haku toimii sanalla, josta osa löytyy mutta koko sanaa ei.
     */
    @Test
    public void hakuSanallaJostaOsaLoytyy() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("dogs");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaku 'dogs' ei tuottanut yhtään riviä!\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kahdella hakusanalla ja hakumerkillä
    @Test
    public void hakuKahdella() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("him & her");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla 'him & her' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "29. blood, and called her to revive and not to leave him miserable,\n", ulos.toString() );
    }
    
    //Testaa, että haku kahden sanan haku toimii suluilla
    @Test
    public void hakuKahdellaJaSuluilla() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("(him & her)");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla '(him & her)' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "29. blood, and called her to revive and not to leave him miserable,\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kolmella sanalla
    @Test
    public void hakuKolmella() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("con* & (he / her)");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla 'con* & (he / her)' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "1. Cephalus, though he had lost his dog, still continued to take\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kolmella sanalla kun sulkeet ensimmäisten sanojen ympärillä
    @Test
    public void toinenHakuKolmella() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("(con* & he) / her");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla '(con* & he) / her' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "1. Cephalus, though he had lost his dog, still continued to take\n"
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
    public void hakuIlmanSulkuja() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("con* & he / her");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla 'con* & he / her' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "1. Cephalus, though he had lost his dog, still continued to take\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii neljällä sanalla
    @Test
    public void hakuNeljalla() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("((him/her)&(con*/green))");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla '((him/her)&(con*/green))' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "17. after him, and concealed herself in the place where the informer\n", ulos.toString() );
    } 
    
    //Testaa, että haku toimii neljällä vaikka koko lauseen ympärillä ei ole sulkuja
    @Test
    public void hakuVahemmillaSuluilla() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        hakija.printtaa("(him/her)&(con*/green)");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nHaulla '(him/her)&(con*/green)' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "17. after him, and concealed herself in the place where the informer\n", ulos.toString() );
    }
    
    //Testaa, että haku toimii kahdesta tiedostosta.
    @Test
    public void hakuKahdestaToimii() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        tiedosto = new File("Prometheus.txt");
        tallentaja.tallenna("Prometheus.txt", tiedosto);
        hakija.printtaa("sun");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nTiedosto 'Prometheus.txt' tallennettiin!\n"
                + "\nHaulla 'sun' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "5. hunting, when the sun got high he would seek a shady nook where a\n"
                + "Prometheus.txt:\n"
                + "5. together.  As yet no sun gave light to the world, nor did the\n"
                + "43. lighted his torch at the chariot of the sun, and brought down\n"
                + "95. The sun his annual course obliquely made,\n", ulos.toString() );
    }
    
    // Testaa, että haku kahdella hakusanalla toimii myös kahdesta tiedostosta.
    @Test
    public void hakuKahdellaKahdestaToimii() throws FileNotFoundException {
        tiedosto = new File("Koe.txt");
        tallentaja.tallenna("Koe.txt", tiedosto);
        tiedosto = new File("Prometheus.txt");
        tallentaja.tallenna("Prometheus.txt", tiedosto);
        hakija.printtaa("went / came");
        assertEquals( "\nTiedosto 'Koe.txt' tallennettiin!\n"
                + "\nTiedosto 'Prometheus.txt' tallennettiin!\n"
                + "\nHaulla 'went / came' löytyi seuraavat rivit:\n"
                + "Koe.txt:\n"
                + "11. he was talking to some maiden, went and told the secret to\n"
                + "16. morning, when Cephalus went to hunt as usual.  Then she stole out\n"
                + "18. directed her.  Cephalus came as he was wont when tired with\n"
                + "Prometheus.txt:\n"
                + "40. animals, came last.  But for man Epimetheus had nothing,-- he had\n"
                + "41. bestowed all his gifts elsewhere.  He came to his brother for\n"
                + "42. help, and Prometheus, with the aid of Minerva, went up to heaven,\n"
                + "105. To this came next in course the Brazen Age:\n"
                + "112. their places came fraud and cunning, violence, and the wicked\n", ulos.toString() );
    }
}
