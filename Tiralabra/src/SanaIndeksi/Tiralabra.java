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
     * Mein-metodi.
     * Luo aluksi Tallentaja- ja Hakija-olennot.
     * Kysyy käyttäjältä tiedoston nimen ja pyytää Tallentajaa tallentamaan sen.
     * Kysyy käyttäjältä etsittävää sanaa ja kutsuu Hakijan printtaa-metodia.
     * @param args
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        Tallentaja tallentaja = new Tallentaja();
        Hakija hakija = new Hakija();
        
        System.out.println("Anna talletettavan tiedoston nimi:");
        String tiedostoNimi = lukija.nextLine();
        File tiedosto = new File(tiedostoNimi);
        
        if (!tiedosto.exists()) {
            System.out.println("Tiedostoa "+ tiedostoNimi + " ei löydy!");
            return;
        }
        
        tallentaja.tallenna(tiedosto);
        
        System.out.println("Anna haettava sana:");
        String haettu = lukija.nextLine();
        hakija.printtaa(haettu, tallentaja);
    }
}
