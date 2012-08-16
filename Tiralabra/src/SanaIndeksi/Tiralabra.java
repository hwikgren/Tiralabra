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
    
    /**
     * Metodi printtaa päävalikon ja lukee käyttäjän valinnan.
     * @return käyttäjän valinta.
     */
    private static int paavalikko() {
        System.out.println("");
        System.out.println("***SANAKKO***");
        System.out.println("-------------");
        System.out.println("Päävalikko:");
        System.out.println("1. Hae yhdellä sanalla");
        System.out.println("2. Hae usealla sanalla");
        System.out.println("3. Lisää tiedosto");
        System.out.println("0. Poistu");

        System.out.println("\nValitse numero:");
        int valinta = lukija.nextInt();
        return valinta;
    }
    
    /**
     * Metodi pysäyttää do-while -loopin ja odottaa, että käyttäjä painaa enteriä<p>
     * Luodaan ensin uusi Scanner ja kehoitetaan käyttäjää painamaan enteriä päästäkseen eteenpäin.
     * Uudella scannerilla luetaan rivi. (Do-while -loopissa ei pysty käyttämään nextLine()a,
     * koska looppi tulkitsee sen painallukseksi ja jatkaa matkaa):
     */
    private static void odotaEnteria() {
        Scanner odota = new Scanner(System.in);
        System.out.print("\nPaina enter palataksesi päävalikkoon");
        odota.nextLine();
    }
    
    /**
     * Metodi pysäyttää do-while -loopin ja kysyy käyttäjältä hakusanoja.<p>
     * Luodaan uusi Scanner ja kysytään hakusanoja.
     * Uusi scanner lukee rivin.
     * @return Käyttäjän hakusanat.
     */
    private static String odotaHakua() {
        Scanner odota = new Scanner(System.in);
        System.out.println("\nVoit hakea yhdistää sanahakuja merkeillä & (ja) ja / (tai).\n"
            + "Jos haet kolmea tai useampaa sanaa, ryhmitä hakusi suluilla.\n"
            + "Minkä tahansa sanoista voi katkaista *-merkillä.");
        System.out.println("Anna haettavat sanat:");
        return odota.nextLine().toLowerCase();
    }
    
    private static Scanner lukija = new Scanner(System.in);
    
    /**
     * Mein-metodi.<p>
     * Luo aluksi Tallentaja- ja Hakija-olennot.
     * Näytetään käyttäjälle päävalikko ja reagoidaan valintaan.<br>
     * 
     * Jos käyttäjä valitsee 1 (hae yhdessä sanalla), kysytään sanaan ja suoritetaan haku.
     * Hakutulosten näkyessä, kutsutaan odotaEnteria-metodia.<br>
     * 
     * Jos valinta on 2 (hae usealla sanalla), haetaan hakusana kutsumalla odotaHakua-metodia.
     * Suoritetaan haku ja kutsutaan odotaEnteria-metodia.<br>
     * 
     * Jos valinta on 3 (tallenna tiedosto), kysytään tiedoston nimeä 
     * ja tarkastetaan, että tiedosto löytyy. Suoritetaan talletus.<br>
     * 
     * Ohjelman suoritus loppuu kun valinta on 0.
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
        int valinta;
        
        
        do {
            valinta = paavalikko();
            switch(valinta) {
                
                case 1:
                    System.out.println("\nAnna haettava sana (voit katkaista sanan *-merkillä):");
                    String haettu = lukija.next().toLowerCase();
                    /**
                    * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
                    */
                    hakija.printtaa(haettu);
                    odotaEnteria();
                    break;
                case 2:
                    haettu = odotaHakua();
                    /**
                    * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
                    */
                    hakija.printtaa(haettu);
                    odotaEnteria();
                    break;
                case 3:
                    System.out.println("\nAnna talletettavan tiedoston nimi:");
                    String tiedostoNimi = lukija.next();
                    File tiedosto = new File(tiedostoNimi);
                    /**
                    * Tarkistetaan onko talletettava tiedosto olemassa.
                    */
                    if (!tiedosto.exists()) {
                        System.out.println("Tiedostoa "+ tiedostoNimi + " ei löydy!");
                        break;
                    }

                    /**
                    * Pyydetään Tallentaja-oliota tallentamaan kyseinen tiedosto.
                    */
                    tallentaja.tallenna(tiedostoNimi, tiedosto);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nEi sallittu valinta!");
                    break;
            }
        } while (valinta !=0);
        
        
    }
}

