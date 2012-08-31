package SanaIndeksi;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;



/**
 * Ohjelman pääluokka.
 * Sisältää main-metodin.
 * @author heidi
 */
public class Tiralabra {
    
    private static Scanner lukija = new Scanner(System.in);
    
    /**
     * Metodi printtaa päävalikon ja lukee käyttäjän valinnan.
     * @return käyttäjän valitsema numero.
     */
    private static int paavalikko() {
        System.out.println("");
        System.out.println("***WordI***");
        System.out.println("-------------");
        System.out.println("Päävalikko:");
        System.out.println(" 1. Hae kaikista tiedostoista");
        System.out.println(" 2. Hae tietystä tiedostosta");
        System.out.println(" 3. Lisää tiedosto");
        System.out.println(" 4. Listaa tiedostot");
        //System.out.println(" 5. Hae tiedostot, jossa haettu sana");
        System.out.println(" 0. Lopeta");

        Scanner oma = new Scanner(System.in);
        
        System.out.println("\nValitse numero:");
        int valinta;
        try {
            valinta = oma.nextInt();
        }
        catch(InputMismatchException ex) {
            valinta = 9;
        }
        return valinta;
    }
    
    
    /**
     * Main-metodi.<p>
     * Luo aluksi Tallentaja- ja Hakija-oliot.
     * Näytetään käyttäjälle päävalikko ja reagoidaan valintaan.<br>
     * 
     * Jos käyttäjä valitsee 1 (hae kaikista tiedostoista), kysytään sanaan ja suoritetaan haku.
     * Hakutulosten näkyessä, kutsutaan odotaEnteria-metodia.<br>
     * 
     * Jos valinta on 2 (hae yhdestä tiedostosta), haetaan tiedosto, 
     * josta haku tehdään kutsumalla odottajan odotaTiedostoa-metodia. Sitten haetaan hakusana kutsumalla odotaHakua-metodia.
     * Suoritetaan haku ja kutsutaan odotaEnteria-metodia.<br>
     * 
     * Jos valinta on 3 (tallenna tiedosto), kysytään tiedoston nimeä 
     * ja tarkastetaan, että tiedosto löytyy. Kutsutaan tallentajan talenna-metodia.<br>
     * 
     * Jos valinta on 4 (listaa tiedostot), pyydetään tallentajaa printtaamaan tiedostojen nimet.
     * Kutsutaan odotaEnteria-metodia.
     * 
     * Jos valinta on 0 (lopeta), pyydetään muistiin-oliota tallentamaan.
     * Ohjelman suoritus loppuu.
     * @param args
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Hakija hakija;
        Tallentaja tallentaja;
        Odottaja odottaja = new Odottaja();
        
        Muistiin muisti = new Muistiin();
        //Pyydetään Muistiin-oliolta lataamaan hakijan tiedot tiedostosta.
        hakija = muisti.lataaTiedot();
        //hakija ei ole tyhjä, haetaan sen tallentaja-olio.
        if (hakija != null) {
            tallentaja = hakija.tallentaja;
        }
        else {
            /**
            * Luodaan tallentaja-olento.
            */
            tallentaja = new Tallentaja();
            //Puu puu = tallentaja.sanaPuu;

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
                    String haettu = odottaja.odotaHakua();
                    /**
                    * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
                    */
                    hakija.printtaa(haettu, null);
                    odottaja.odotaEnteria();
                    break;
                case 2:
                    String teksti = odottaja.odotaTiedostoa();
                    if (!tallentaja.onko(teksti)) {
                        System.out.println("Tiedostoa '"+teksti+"' ei löydy!");
                        break;
                    }
                    haettu = odottaja.odotaHakua();
                    /**
                    * Pyydetään Hakija-olentoa hakemaan ja printtaamaan rivit, joilla annettu sana esiintyy.
                    */
                    hakija.printtaa(haettu, teksti);
                    odottaja.odotaEnteria();
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
                    odottaja.odotaEnteria();
                    break;
                case 9:
                    System.out.println("\nEi sallittu valinta!");
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

