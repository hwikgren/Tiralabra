package SanaIndeksi;

import java.io.File;
import java.io.FileNotFoundException;
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
public class Tallentaja {
    /**
     * Toistaiseksi tiedoston rivit menevät vielä ArrayListiin.
     */
    public ArrayList<String> rivit;
    
    /**
     * Puu-olento;
     */
    public Puu sanaPuu;

    /**
     * Konstruktori luo tallentajan ja sille puun sanoja varten ja taulukon rivejä varten
     */
    public Tallentaja() {
        sanaPuu = new Puu();
        rivit = new ArrayList<String>();
    }
    
    /**
     * Metodi tallentaa annetun tiedoston rivit ArrayListiin ja sanat puuhun.<p> 
     * Rivit jaetaan sanoiksi StringTokenizerilla.
     * Ennen sanojen lisäämistä metodi kutsuu trimmaa-metodia.
     * @param tiedosto
     * @throws FileNotFoundException 
     */
    public void tallenna(File tiedosto) throws FileNotFoundException {
        Scanner syotto = new Scanner(tiedosto);
        int riviLaskin = 0;
        //Ensimmäinen rivi jätetään tyhjäksi
        rivit.add(" ");
        //Käydään teksti läpi rivi kerrallaa
        while (syotto.hasNextLine()) {
            String rivi = syotto.nextLine();
            rivit.add(++riviLaskin+". "+rivi);
            String karsittuRivi = rivi.trim();
            //jaetaan rivi sanoiksi
            StringTokenizer sanoittaja = new StringTokenizer(karsittuRivi);
            while (sanoittaja.hasMoreTokens()) {
                String sana = sanoittaja.nextToken().toLowerCase();
                String karsittuSana = trimmaa(sana);
                sanaPuu.lisaaSana(karsittuSana, riviLaskin);
            }
        }
    }
    
    /**
     * Metodi poistaa annetun sanan alusta ja lopusta mahdolliset merkit, jotka eivät ole kirjaimia.
     * <p>Tarkistaakseen onko merkki kirjain, metodi kutsuu eiKirjain-metodia.
     * @param sana
     * @return Palauttaa sanan ilman alussa tai lopussa olevia erikoismerkkejä.
     */
    private String trimmaa(String sana) {
        String karsittuSana = sana.trim();
        int alku = 0;
        while (eiKirjain(karsittuSana.charAt(0))) {
            karsittuSana = karsittuSana.substring(1);
        }
        while (eiKirjain(karsittuSana.charAt(karsittuSana.length()-1))) {
            karsittuSana = karsittuSana.substring(0, karsittuSana.length()-1);
        }
        return karsittuSana;
    }
    
    /**
     * Metodi tarkastaa onko annettu merkki kirjain vai ei.
     * @param merkki
     * @return true jos merkki ei ole kirjain.
     */
    private boolean eiKirjain(char merkki) {
        return ("abcdefghijklmnopqrstuvwxyzåäö".indexOf(merkki) ==-1);
    }
    
}
