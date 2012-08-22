package SanaIndeksi;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



/**
 * Ohjelman pääluokka.
 * Sisältää main-metodin.
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
        System.out.println(" 1. Hae kaikista tiedostoista");
        System.out.println(" 2. Hae tietystä tiedostosta");
        System.out.println(" 3. Lisää tiedosto");
        System.out.println(" 4. Listaa tiedostot");
        System.out.println(" 0. Lopeta");

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
        System.out.println("\nSanakosta voit hakea rivejä yhdellä tai useammalla sanalla.\n"
                + "Voit yhdistää sanahakuja merkeillä & (= ja) ja / (= tai).\n"
            + "Jos haet kolmea tai useampaa sanaa, ryhmitä hakusi suluilla.\n"
            + "Minkä tahansa sanoista voi katkaista *-merkillä.");
        System.out.println("Anna haetta(vat) sana(t):");
        return odota.nextLine().toLowerCase();
    }
    
    private static String odotaTiedostoa() {
        Scanner odota = new Scanner(System.in);
        System.out.println("Anna tiedoston nimi, josta haluat hakea rivejä:");
        return odota.nextLine();
    }
    
    private static Scanner lukija = new Scanner(System.in);
    
    /**
     * Main-metodi.<p>
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
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Hakija hakija;
        Tallentaja tallentaja;
        
        Muistiin muisti = new Muistiin();
        
        if (muisti.lataaTiedot()) {
            hakija = muisti.getHakija();
            tallentaja = hakija.getTallentaja();
        }
        else {
            /**
            * Luodaan tallentaja-olento.
            */
            tallentaja = new Tallentaja();
            Puu puu = tallentaja.sanaPuu;

            /**
            * Luodaan Hakija-olento.
            */
            hakija = new Hakija(tallentaja);
        }
        int valinta;
        
        
        do {
            valinta = paavalikko();
            switch(valinta) {
                
                case 1:
                    String haettu = odotaHakua();
                    /**
                    * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
                    */
                    hakija.printtaa(haettu, null);
                    odotaEnteria();
                    break;
                case 2:
                    String teksti = odotaTiedostoa();
                    if (!tallentaja.onko(teksti)) {
                        System.out.println("Tiedostoa '"+teksti+"' ei löydy!");
                        break;
                    }
                    haettu = odotaHakua();
                    /**
                    * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
                    */
                    hakija.printtaa(haettu, teksti);
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
                case 4:
                    tallentaja.tulostaTiedostot();
                    odotaEnteria();
                    break;
                case 0:
                    muisti.talletaTiedot(hakija);
                    break;
                default:
                    System.out.println("\nEi sallittu valinta!");
                    break;
            }
        } while (valinta !=0);
    
    }
}

