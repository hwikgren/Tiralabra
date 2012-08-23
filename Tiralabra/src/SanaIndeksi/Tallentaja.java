package SanaIndeksi;

import SanaIndeksi.Muotoilija;
import SanaIndeksi.Puu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Luokka hoitaa annetun tiedoston rivien ja sanojen tallentamisen.
 * @author heidi
 */
public class Tallentaja implements Serializable {
    /**
     * Tiedostojen rivit tallennetaan taulukkoon.
     */
    public String[] rivit;
    
    /**
     * Puu-olento sanojen tallentamiseen.
     */
    public Puu sanaPuu;
    /**
     * Pitää kirjaa siitä montako riviä yhteensä on tallennettu.
     */
    int riviLaskin;
    /**
     * Pitää kirjaa rivit-taulukon kokonaiskoosta.
     */
    int koko;
    /**
     * Tiedosto-olento taulukko tiedostojen tietojen tallentamiseen.
     */
    Tiedosto[] tiedostot;
    /**
     * Pitää kirjaa tiedostot-taulu kokonaiskoosta.
     */
    int maara;
    /**
     * Pitää kirjaa tallennettujen tiedosto-olioiden lukumäärästä.
     */
    int lukumaara;

    /**
     * Konstruktori luo tallentajan ja sille puun sanoja varten, taulukon rivejä varten
     * ja taulukon Tiedosto-olioita varten.
     */
    public Tallentaja() {
        sanaPuu = new Puu();
        rivit = new String[100];
        koko = 100;
        riviLaskin = 1;
        tiedostot = new Tiedosto[10];
        maara = 10;
        lukumaara = 0;
    }
    
    /**
     * Metodi tallentaa annetun tiedoston rivit taulukkoon ja sanat puuhun.<p> 
     * Jos samannimistä tiedostoa ei vielä ole tiedostot-taulukossa, 
     * luodaan Tiedosto-olento ja tallennetaan se tiedostot-tauluun.
     * Asetetaan kyseisen tiedoston rivien aloitusmääräksi 1.
     * Jos kyseessä on ensimmäinen tiedosto, lisätään rivit-tauluun tyhjä rivi indeksiin 0.
     * Tiedosto käydään läpi rivi kerrallaa.
     * Rivi tallennetaan rivit-taulukkoon.
     * Sitten rivi jaetaan sanoiksi StringTokenizerilla.
     * Ennen sanojen lisäämistä puuhun metodi kutsuu trimmaa-metodia.
     * Lopuksi kerrotaa käyttäjälle, että tiedosto on tallennettu onnistuneesti.
     * Jos senniminen tiedosto löytyi jo, kerrotaan käyttäjälle, että tiedostoa ei tallennettu.
     * @param tiedostoNimi String-muotoinen tallennettavan tiedoston nimi
     * @param tiedosto File-olento tallennettavasta tiedostosta.
     * @throws FileNotFoundException 
     */
    public void tallenna(String tiedostoNimi, File tiedosto) throws FileNotFoundException {
        if (!onko(tiedostoNimi)) {
            Tiedosto uusi = new Tiedosto(tiedostoNimi, riviLaskin);
            //jos tiedostot-taulukko on täynnä, kasvatetaan sitä.
            if (tiedostot.length == maara) {
                kasvataTiedostot();
            }
            tiedostot[lukumaara++] = uusi;

            int tiedostonRivi = 1;

            //Ensimmäinen rivi jätetään tyhjäksi
            if (rivit.length == 0) {
                rivit[0] = " ";
            }
            //Luodaan tiedostonlukija.
            Scanner syotto = new Scanner(tiedosto);
            //Luodaan sanojen muotoilija.
            Muotoilija muotoilija = new Muotoilija();

            //Käydään teksti läpi rivi kerrallaan
            while (syotto.hasNextLine()) {
                String rivi = syotto.nextLine();
                //jätetään tyhjät rivit väliin.
                if (rivi.length() == 0) {
                    continue;
                }
                //jos rivit-taulukko on täynnä, kasvatetaan sitä.
                if (riviLaskin == koko) {
                    kasvata();
                }
                //lisätään rivit-tiedostoon rivinumero (tiedoston sisäinen) ja rivin teksti
                //kohtaan joka jatkuu edellisistä dokumenteista.
                rivit[riviLaskin] = tiedostonRivi+++". "+rivi;
                rivi = rivi.trim();
                //jaetaan rivi sanoiksi
                StringTokenizer sanoittaja = new StringTokenizer(rivi);
                while (sanoittaja.hasMoreTokens()) {
                    String sana = sanoittaja.nextToken().toLowerCase();
                    String karsittuSana = muotoilija.trimmaa(sana);
                    sanaPuu.lisaaSana(karsittuSana, riviLaskin);
                }
                //kasvateaan kokonaisrivien määrää.
                riviLaskin++;
            }
            //Asetetaan tiedosto-olion loppu-muuttujaksi rivien määrä tiedoston lopussa.
            uusi.setLoppu(riviLaskin-1);

            System.out.println("\nTiedosto '"+tiedostoNimi+"' tallennettiin!");
        }
        else {
            System.out.println("\nTiedosto '"+tiedostoNimi+"' on jo olemassa.\n"
                    + "Tiedostoa ei tallennettu!");
        }
    }
    
    /**
     * Metodi printtaa kaikkien tiedostojen nimet.<p>
     * Jos tiedostoja ei ole, kerrotaan asiasta käyttäjälle.
     * Muuten printataan kunkin tiedostot-taulussa olevan olion nimi.
     */
    public void tulostaTiedostot() {
        System.out.println("");
        if (lukumaara == 0) {
            System.out.println("Ei tallennettuja tiedostostoja!");
        }
        else {
            System.out.println("Tiedostot:");
            for (int i=0; i<lukumaara; i++) {
                System.out.println(tiedostot[i].nimi);
            }
        }
        System.out.println("");
    }
    
    /**
     * Getteri
     * @param nimi String-muotoinen tiedoston nimi,
     * @return Palauttaa sen tiedoston indeksin tiedostot-taulukossa, jolla on haettu nimi (tai -1)
     */
    public int getIndeksi(String nimi) {
        for (int i=0; i<lukumaara; i++) {
            if (tiedostot[i].nimi.equals(nimi)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Metodi tarkistaa onko haetun niminen tiedosto jo tiedostot-taulussa.<p>
     * Kutsuu getIndeksi-metodia. Jos tiedoston indeksi on -1, palauttaa false.
     * @param nimi String-muotoinen tiedoston nimi.
     * @return true jos tiedosto löytyy.
     */
    public boolean onko(String nimi) {
        if (getIndeksi(nimi) >-1) {
            return true;
        }
        return false;
    }
    
    /**
     * Metodi kaksinkertaistaa rivit-taulukon koon.
     */
    private void kasvata() {
        koko = 2*koko;
        String[] uusi = new String[koko];
        for (int i=0; i<rivit.length; i++) {
            uusi[i] = rivit[i];
        }
        rivit = uusi;
    }
    
    /**
     * Metodi kaksinkertaistaa tiedostot-taulukon koon.
     */
    private void kasvataTiedostot() {
        maara = 2*maara;
        Tiedosto[] uusi = new Tiedosto[maara];
        for (int i=0; i<tiedostot.length; i++) {
            uusi[i] = tiedostot[i];
        }
        tiedostot = uusi;
    }
    
    
}
