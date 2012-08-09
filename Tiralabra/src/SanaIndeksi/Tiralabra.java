package SanaIndeksi;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



/**
 * Ohjelman pääluokka.
 * Sisältää main-metodin.
 * Toistaiseksi kysyy tiedoston joka kerta ja hakee vain yhden sanan.
 * @author heidi
 */
public class Tiralabra {
    
    private static Scanner lukija = new Scanner(System.in);
    
    /**
     * Mein-metodi.<p>
     * Luo aluksi Tallentaja- ja Hakija-olennot.
     * Kysyy käyttäjältä tiedoston nimen. jos tiedosto on olemassa, pyytää Tallentajaa tallentamaan sen.
     * Kysyy käyttäjältä etsittävää sanaa ja kutsuu Hakijan printtaa-metodia.
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        /**
         * Luodaan tallentaja-olento.
         */
        Tallentaja tallentaja = new Tallentaja();
        Puu puu = tallentaja.sanaPuu;
        
        /**
         * Luodaan Hakija-olento.
         */
        Hakija hakija = new Hakija(puu, tallentaja);
        
        System.out.println("Anna talletettavan tiedoston nimi:");
        String tiedostoNimi = lukija.nextLine();
        File tiedosto = new File(tiedostoNimi);
        /**
         * Tarkistetaan onko talletettavat tiedosto olemassa.
         */
        if (!tiedosto.exists()) {
            System.out.println("Tiedostoa "+ tiedostoNimi + " ei löydy!");
            return;
        }
        
        /**
         * Pyydetään Tallentaja-oliota tallentamaan kyseinen tiedosto.
         */
        tallentaja.tallenna(tiedosto);
        
        System.out.println("Anna haettava sana:");
        String haettu = lukija.nextLine();
        /**
         * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
         */
        hakija.printtaa(haettu);
    }
}
